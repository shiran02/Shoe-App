package com.cadenza.shoeapp.SellerPart;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cadenza.shoeapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class SellerActivity extends AppCompatActivity {

    CardView cardView1,cardView2;
    Button reviewBtn;

    TextView fullName,email,phone;

    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String userId;
    String seller_Name,seller_email,seller_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);

        //--card Views________________________________________________________________

        cardView1 = findViewById(R.id.sport);
        cardView2 = findViewById(R.id.Officeshoes);
        //---btn--------------------------------------

        reviewBtn = findViewById(R.id.reviewBtn);






        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SportShoeUpload.class));
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), OfficeShoeUpload.class));

            }
        });



        //----------------------------------------------------------------------------

        fullName = findViewById(R.id.yourName);
        email = findViewById(R.id.yourEmail);
        phone = findViewById(R.id.phoneNumber);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();

        DocumentReference documentreference = fStore.collection("sellers").document(userId);

        documentreference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                //get seller detail----------------------------------------

                seller_Name = documentSnapshot.getString("uname");
                seller_email = documentSnapshot.getString("email");
                seller_phone = documentSnapshot.getString("contactnum");

                // set seller Values ------------------------------------------
                fullName.setText(documentSnapshot.getString("uname"));
                email.setText(documentSnapshot.getString("email"));
                phone.setText(documentSnapshot.getString("contactnum"));
            }
        });




        //review Button _______________________________________________________
        reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(getApplicationContext(),SellerReviewActivity.class);

                i1.putExtra("seller_Name",seller_Name);
                i1.putExtra("seller_Email",seller_email);
                i1.putExtra("seller_PhoneNumber",seller_phone);
                startActivity(i1);

               // startActivity(new Intent(getApplicationContext(), SellerReviewActivity.class));


            }
        });


    }



    public void logout(View view) {
        FirebaseAuth.getInstance().signOut(); //logout our Application
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }



}