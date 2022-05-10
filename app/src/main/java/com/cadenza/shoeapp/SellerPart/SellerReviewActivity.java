package com.cadenza.shoeapp.SellerPart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.cadenza.shoeapp.R;
import com.cadenza.shoeapp.Rate.Rate;
import com.cadenza.shoeapp.Rate.RateAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SellerReviewActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    DatabaseReference database;
    ReviewAdapter reviewadapter;
    ArrayList<Review> list;

    String s_Name,s_email,s_phone;
    TextView fullName,email,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_review);


        fullName = findViewById(R.id.yourName);
        email = findViewById(R.id.yourEmail);
        phone = findViewById(R.id.phoneNumber);
        recyclerView = findViewById(R.id.shoeRiview);


        //getSeller and set detail ___________________________________

        s_Name = getIntent().getStringExtra("seller_Name");
        s_email = getIntent().getStringExtra("seller_Email");
        s_phone = getIntent().getStringExtra("seller_PhoneNumber");

        fullName.setText(s_Name);
        email.setText(s_email);
        phone.setText(s_phone);



        //----------------------Read data base---------------------------------------

        database = FirebaseDatabase.getInstance().getReference("sport_shoe_item");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //----
        list = new ArrayList<>();
        reviewadapter = new ReviewAdapter(this,list);
        recyclerView.setAdapter(reviewadapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshots : snapshot.getChildren()){
                    Review ordersss = dataSnapshots.getValue(Review.class);
                    list.add(ordersss);
                }
                reviewadapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}