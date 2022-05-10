package com.cadenza.shoeapp.profilePart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cadenza.shoeapp.R;
import com.cadenza.shoeapp.SellerPart.LoginActivity;
import com.cadenza.shoeapp.SellerPart.MainActivity;
import com.cadenza.shoeapp.SellerPart.RegisterActivity;
import com.cadenza.shoeapp.SellerPart.SellerActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CusLoginActivity extends AppCompatActivity {


    EditText cus_nameField,cus_passwordField;
    Button LoginBtn,creAcc,signup_screen_btn;
    TextView mCreateBtn,forgotTextLink;
    ProgressBar progressBar;
    FirebaseAuth fAuth;


    String cus_pasword,cus_email,cus_name;

    FirebaseDatabase db;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_get_data);

        cus_nameField = findViewById(R.id.Cname);
        cus_passwordField = findViewById(R.id.Cpassword);
       // cPasswordField = findViewById(R.id.Cpassword);


        LoginBtn = findViewById(R.id.cus_login_bn);
        signup_screen_btn = findViewById(R.id.forgotpassword);
        creAcc = findViewById(R.id.cus_signup_screen_btn);


        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progress_Bar);



        db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference("customers");



        creAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CustomerRegPart.class));
            }
        });


        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarion();
            }
        });

        /*LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String  email = cEmailField.getText().toString().trim();
                String  password = cPasswordField.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    cEmailField.setError("Email is Required");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    cPasswordField.setError("Pasword is Required");
                    return;
                }

                if(password.length()<6){
                    cPasswordField.setError("Password must be >= 6 characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);



                databaseReference.child("customers").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        //check if email is exist in fire base
                        if(snapshot.hasChild(email)){

                            //email is exist on firebase data base
                            //now get pasword of customer from firabase data and match it with user enter passwprd

                            final String getPassword = snapshot.child("password").getValue(String.class);

                            if(getPassword.equals(password)){
                                Toast.makeText(getApplicationContext(),"Loging Successfully",Toast.LENGTH_SHORT).show();

                                //open main Activity ---------

                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            }else{
                                Toast.makeText(getApplicationContext(),"faild Wrong Password",Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            Toast.makeText(getApplicationContext(),"faild Wrong Password",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



                //-----fire base authentication app-----------------



//
//                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>(){
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//
//                        if(task.isSuccessful()){
//                            Toast.makeText(getApplication(),"Customer Loging in Success",Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//
//                        }else{
//                            Toast.makeText(getApplication(),"Error : "+task.getException(),Toast.LENGTH_SHORT).show();
//                            progressBar.setVisibility(View.GONE);
//                        }
//                    }
//                });
            }
        });*/


    }


    private void validarion(){
        cus_name = cus_nameField.getText().toString();
        cus_pasword = cus_passwordField.getText().toString();

        if(cus_name.isEmpty()){
            cus_nameField.setError("please Fill field");
            cus_nameField.requestFocus();
        }

        if(cus_pasword.isEmpty()){
            cus_passwordField.setError("please Fill field");
            cus_passwordField.requestFocus();
            return;
        }
        
        checkFromDb();
    }
                // read and compare data
    private void checkFromDb() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("customers");
        reference.child(cus_name)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                String db_pass = snapshot.child("password").getValue(String.class);

                                if(cus_pasword.equals(db_pass)){

                                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                  //  intent.putExtra("custermer_Name",cus_name);
                                  //  intent.putExtra("custermer_Mail",cus_name+"@gmail.com");
                                    Toast.makeText(getApplicationContext(), "Login Sucess", Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                    finish();
                                }else{
                                    Toast.makeText(getApplicationContext(), "passwore incorrrect", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(getApplicationContext(), "Recorded Not found", Toast.LENGTH_SHORT).show();
                            }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}