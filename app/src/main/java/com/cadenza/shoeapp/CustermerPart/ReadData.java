package com.cadenza.shoeapp.CustermerPart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cadenza.shoeapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReadData extends AppCompatActivity {

    DatabaseReference reference;
    Button readaddbtn;
    EditText orderField;
    TextView orderNo_txt,shoecolor_txt,shoesize_txt,shoeqty_txt,shoetprice_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_data);

        readaddbtn = findViewById(R.id.search_btn);
        orderField = findViewById(R.id.enterOrderNo);


        orderNo_txt = findViewById(R.id.orderId);
        shoecolor_txt = findViewById(R.id.shoeColor);
        shoesize_txt = findViewById(R.id.shoeSize);
        shoeqty_txt = findViewById(R.id.shoeQty);
        shoetprice_txt = findViewById(R.id.totalPrice);

        readaddbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String orderNumber = orderField.getText().toString();

                if(!orderNumber.isEmpty()){
                    readData(orderNumber);
                }else{
                    Toast.makeText(ReadData.this,"Please Enter Order number",Toast.LENGTH_SHORT).show();
                }
            }


        });


    }
    private void readData(String orderNumber) {
        reference = FirebaseDatabase.getInstance().getReference("Orders");
        reference.child(orderNumber).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if(task.isSuccessful()){

                    if(task.getResult().exists()){

                        Toast.makeText(ReadData.this,"Successfully Read",Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        String orderNumber = String.valueOf(dataSnapshot.child("od_number").getValue());
                        String shoeColor = String.valueOf(dataSnapshot.child("shoe_color").getValue());
                        String shoeSize = String.valueOf(dataSnapshot.child("shoe_size").getValue());
                        String shoeQuantity = String.valueOf(dataSnapshot.child("shoe_qty").getValue());
                        String shoeTotalPrice = String.valueOf(dataSnapshot.child("last_total").getValue());

                        orderNo_txt.setText(orderNumber);
                        shoecolor_txt.setText(shoeColor);
                        shoesize_txt.setText(shoeSize);
                        shoeqty_txt.setText(shoeQuantity);
                        shoetprice_txt.setText("$ "+shoeTotalPrice);



                    }else{

                        Toast.makeText(ReadData.this,"user does not Exist",Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(ReadData.this,"Faild To Read",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}