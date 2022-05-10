package com.cadenza.shoeapp.SellerPart;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cadenza.shoeapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText UserName,Email,Age,ContactNumber,Password;
    public static final String TAG = "TAG";
    Button mRegisterBtn;
    Button alreadyHaveAccBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    //using this we can create collection ,document and store data
    FirebaseFirestore fStore;
    String userID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        UserName = findViewById(R.id.username);
        Email = findViewById(R.id.email);
        Age = findViewById(R.id.age);
        ContactNumber = findViewById(R.id.contactnumber);
        Password = findViewById(R.id.password);


        mRegisterBtn = findViewById(R.id.login_bn);
        alreadyHaveAccBtn = findViewById(R.id.alreadyHaveBtn);

        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        fStore = FirebaseFirestore.getInstance();



        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), SellerActivity.class));
            finish();
        }


        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String  uname = UserName.getText().toString().trim();
                String  email = Email.getText().toString().trim();
                String age = Age.getText().toString().trim();
                String contactnum = ContactNumber.getText().toString().trim();
                String password = Password.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Email.setError("Email is Required");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Password.setError("Pasword is Required");
                    return;
                }

                if(password.length()<6){
                    Password.setError("Password must be >= 6 characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //register the user in Firebase

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            Toast.makeText(RegisterActivity.this,"User Created",Toast.LENGTH_SHORT).show();

                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("sellers").document(userID);
                            Map<String,Object> seller = new HashMap<>();


                            seller.put("uname",uname);
                            seller.put("email",email);
                            seller.put("age",age);
                            seller.put("contactnum",contactnum);
                            seller.put("password",password);


                            documentReference.set(seller).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG,"OonSuccess : user profile for created for " + userID);
                                }
                            });

                            startActivity(new Intent(getApplicationContext(),SellerActivity.class));

                        }else{
                            Toast.makeText(RegisterActivity.this,"Error : "+task.getException(),Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });


            }
        });

        alreadyHaveAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }
}