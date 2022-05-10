package com.cadenza.shoeapp.Rate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.cadenza.shoeapp.CustermerPart.Order;
import com.cadenza.shoeapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RatingActivity extends AppCompatActivity {

    Button button;
    RatingBar ratingBar;
    EditText Rate_comment,Rater_name;
    int myRating = 0;
    String  user_mg;
    String  user_name;


    FirebaseDatabase db;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        button  = findViewById(R.id.submitbtn);
        ratingBar  = findViewById(R.id.rationBar);
        Rate_comment  = findViewById(R.id.rate_comment);
        Rater_name  = findViewById(R.id.user_name);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                int rating = (int)v;
                String message = null;

                myRating = (int)ratingBar.getRating();

                switch(rating){
                    case 1:
                        message = "Sorry to hear That";
                        break;
                    case 2:
                        message = "we always accept sugetion";
                        break;
                    case 3:
                        message = "Good enough";
                        break;
                    case 4:
                        message = "Great Thank You !";
                        break;
                    case 5:
                        message = "Alwways You are the best !!";
                        break;
                }

                Toast.makeText(RatingActivity.this,message,Toast.LENGTH_SHORT).show();


            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RatingActivity.this,"Your Rating is "+String.valueOf(myRating),Toast.LENGTH_SHORT).show();

                  user_mg  = Rate_comment.getText().toString().trim();
                  user_name  = Rater_name.getText().toString().trim();

                if(TextUtils.isEmpty(user_mg)){
                    Rate_comment.setError("plz Enter comment");
                    return;
                }


                //Store the user in Firebase

                RateUser rupload = new RateUser(user_name,user_mg,myRating);
                db = FirebaseDatabase.getInstance();
                reference = db.getReference("Rates");
                reference.child(user_name).setValue(rupload).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });

                Toast.makeText(RatingActivity.this,"Thanks For Rating ",Toast.LENGTH_SHORT).show();

                Rate_comment.setText("");
                Rater_name.setText("");
            }
        });


    }
}