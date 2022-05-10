package com.cadenza.shoeapp.CustermerPart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cadenza.shoeapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity3 extends AppCompatActivity {

    Button orderbtn,update_Btn;
    EditText ed_qu;
    TextView shoePrice,t1,shoeName,numberOrderTxt,addCartBtn;
    ImageView shoePic;
    int numberOrder = 1;
    private ImageView plusBtn,MinusBtn;
    ///int shoePricess = 44;

    FirebaseDatabase db;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        orderbtn = findViewById(R.id.btn_orderbtn);

        shoeName = findViewById(R.id.shoe_name);
        shoePrice = findViewById(R.id.price1);
        shoePic = findViewById(R.id.shoe_pic);
        plusBtn = findViewById(R.id.plusBtn);
        MinusBtn = findViewById(R.id.minusbtn);
        numberOrderTxt = findViewById(R.id.numberOrderTxt);
        //addCartBtn = findViewById(R.id.addCartBtn);


        String s_name = getIntent().getExtras().getString("shoe_name");
        shoeName.setText(s_name);

        int imageId = getIntent().getIntExtra("shoe_img",R.drawable.w);
        shoePic.setImageResource(imageId);


        int s_price = getIntent().getExtras().getInt("shoe_pri",1);
        shoePrice.setText("" +s_price);


        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberOrder = numberOrder +1 ;
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });

        MinusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(numberOrder>1){
                    numberOrder = numberOrder -1;
                }
                numberOrderTxt.setText(String.valueOf(numberOrder));
            }
        });


        orderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent2 = new Intent(MainActivity3.this, Order.class);
                intent2.putExtra("shoe_qty",numberOrder);
                //intent2.putExtra("shoe_Name",shoe_name);
                intent2.putExtra("shoe_pic",imageId);
                startActivity(intent2);



            }
        });


       /* addCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cart cart = new Cart(44,numberOrder,s_name,"white");

                db = FirebaseDatabase.getInstance();
                reference = db.getReference("Carts");
                reference.child(s_name).setValue(cart).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });

                Toast.makeText(MainActivity3.this,"Order Successfullt Enterd To Cart ",Toast.LENGTH_SHORT).show();

                Intent intent2 = new Intent(MainActivity3.this,Order.class);
                startActivity(intent2);
            }
        });*/






    }
}