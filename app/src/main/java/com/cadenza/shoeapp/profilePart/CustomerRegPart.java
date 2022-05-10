package com.cadenza.shoeapp.profilePart;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cadenza.shoeapp.R;
import com.cadenza.shoeapp.SellerPart.LoginActivity;
import com.cadenza.shoeapp.SellerPart.MainActivity;
import com.cadenza.shoeapp.SellerPart.RegisterActivity;
import com.cadenza.shoeapp.SellerPart.SellerActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CustomerRegPart extends AppCompatActivity {

    EditText customerNamefield,customerEmailfield,customerMobNumfield,customerPasswordfield;
    Button imageBrowseBtn,Reg_btn,alreadyHaveAccBtn;
    Uri filepath;
    ImageView cus_profile;
    Bitmap bitmap;
    String t1,t2,t3,t4 = null;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    public static final String TAG = "TAG";


    //using this we can create collection ,document and store data
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login_part);

        customerNamefield = findViewById(R.id.curoner_prof_name);
        customerEmailfield = findViewById(R.id.cusemail);
        customerMobNumfield = findViewById(R.id.customer_mob_num);
        customerPasswordfield = findViewById(R.id.customer_password);


        imageBrowseBtn = findViewById(R.id.image_browse_btn);
        Reg_btn = findViewById(R.id.cuslogin_btn);
        cus_profile = findViewById(R.id.cus_imag_view);
        alreadyHaveAccBtn = findViewById(R.id.cusalreadyHaveBtn);

        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        fStore = FirebaseFirestore.getInstance();

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), SellerActivity.class));
            finish();
        }

        //-----------------Fire Base Authentication Register-----------------------------------------



        //------------------------------------------------------------------------------------------


        alreadyHaveAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CusLoginActivity.class));
            }
        });



            imageBrowseBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Dexter.withActivity(CustomerRegPart.this)
                            .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            .withListener(new PermissionListener() {
                                @Override
                                public void onPermissionGranted(PermissionGrantedResponse response) {
                                    Intent intent = new Intent(Intent.ACTION_PICK);
                                    intent.setType("image/*");
                                    startActivityForResult(intent.createChooser(intent,"Select Image File"),1);
                                }

                                @Override
                                public void onPermissionDenied(PermissionDeniedResponse response) {

                                }

                                @Override
                                public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                    token.continuePermissionRequest();
                                }
                            }).check();
                }
            });

        Reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                customerNamefield = findViewById(R.id.curoner_prof_name);
                customerEmailfield = findViewById(R.id.email);
                customerMobNumfield = findViewById(R.id.customer_mob_num);
                customerPasswordfield = findViewById(R.id.customer_password);
                */

                 t1 = customerNamefield.getText().toString().trim();
                 t2 = customerEmailfield.getText().toString().trim();
                 t3 = customerMobNumfield.getText().toString().trim();
                 t4 = customerPasswordfield.getText().toString().trim();

                if(TextUtils.isEmpty(t1)){

                Toast.makeText(getApplicationContext(),"Enter Cstomer Name",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(t2)){

                    Toast.makeText(getApplicationContext(),"Enter Email",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(t3)){

                    Toast.makeText(getApplicationContext(),"Enter Mobile Number",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(t4)){

                    Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_SHORT).show();
                    return;
                }


                uploadtoFirebase();
            }
        });

        }

    private void uploadtoFirebase() {

        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("file uploader");
        dialog.show();
        customerNamefield = findViewById(R.id.curoner_prof_name);
        customerEmailfield = findViewById(R.id.cusemail);
        customerMobNumfield = findViewById(R.id.customer_mob_num);
        customerPasswordfield = findViewById(R.id.customer_password);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference uploader = storage.getReference("Image1" + new Random().nextInt(50));

        uploader.putFile(filepath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    dialog.dismiss();
                                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                                    DatabaseReference root= db.getReference();

                                    dataHolder obj = new dataHolder(customerNamefield.getText().toString(),customerEmailfield.getText().toString(),
                                            customerMobNumfield.getText().toString(),customerPasswordfield.getText().toString(),uri.toString());

                                           //root.child(customerNamefield.getText().toString()).setValue(obj);
                                           root.child("customers").child(customerNamefield.getText().toString()).setValue(obj);

                                            customerNamefield.setText("");
                                            customerEmailfield.setText("");
                                            customerMobNumfield.setText("");
                                            customerPasswordfield.setText("");
                                            cus_profile.setImageResource(R.drawable.imageview);
                                            Toast.makeText(getApplicationContext(),"Uploaded",Toast.LENGTH_SHORT).show();

                                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                }
                            });
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                        float percent = (100*taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                        dialog.setMessage("uploaded :  " + (int)percent+ "%");
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode ==1 && resultCode == RESULT_OK){
            filepath = data.getData();

            try{
                InputStream inputStream = getContentResolver().openInputStream(filepath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                cus_profile.setImageBitmap(bitmap);

            }catch(Exception ex){

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }






}
