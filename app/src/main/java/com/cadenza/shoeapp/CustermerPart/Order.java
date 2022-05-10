package com.cadenza.shoeapp.CustermerPart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cadenza.shoeapp.SellerPart.MainActivity;
import com.cadenza.shoeapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Order extends AppCompatActivity {
    TextView quantity_shoe,p_full;
    EditText mAddress,mPhone,userName;
    Button O_btn;

    String[] size = { "Medium", "Large", "Max"};
    int[] number = {1,2,3,4,5,6,7};
    String[] color = {"Black","White","Red","Yellow"};

    Spinner spin_size,spin_color,spin3;
    String divi;
    private int qty;

    FirebaseDatabase db;
    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        //shoeDbRef = FirebaseDatabase.getInstance().getReference().child("order");

        spin_size = (Spinner) findViewById(R.id.spinner_size);
        spin_color = (Spinner) findViewById(R.id.spinner_color);

        quantity_shoe = findViewById(R.id.qty_shoe);
        mPhone = findViewById(R.id.mob_num_field);
        mAddress = findViewById(R.id.addressfield);
        O_btn = findViewById(R.id.order_btn);
        p_full = findViewById(R.id.pric_full);
        userName = findViewById(R.id.User_name_field);

        //ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,size);
        ArrayAdapter bb = new ArrayAdapter(this,android.R.layout.simple_spinner_item,size);
        ArrayAdapter cc = new ArrayAdapter(this,android.R.layout.simple_spinner_item,color);


        //aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin_size.setAdapter(bb);
        spin_color.setAdapter(cc);


        //int shoePrice = 44;

        Intent i = getIntent();
        //int qty = i.getIntExtra("shoe_price",0);
        //String shoeName = getIntent().getExtras().getString("shoe_name");
        int q = getIntent().getExtras().getInt("shoe_qty");
        int pic_id = getIntent().getExtras().getInt("shoe_pic");




        int total = q*44;

        quantity_shoe.setText(""+q);
        p_full.setText("$ "+total);




        O_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get Values From user and store it -----

                String  phone_number = mPhone.getText().toString().trim();
                String  home_address = mAddress.getText().toString().trim();
                String shoe_color =  spin_color.getSelectedItem().toString();
                String shoe_size =  spin_size.getSelectedItem().toString();
                String user_name =  userName.getText().toString().trim();

                selectImage();

                int shoe_qty = q;
                int last_total = total;

                int max =12,min =100;
                int order_number = (int)(Math.random()*(max - min + 1)+min);
                String str = String.valueOf(order_number);

                if(TextUtils.isEmpty(phone_number)){
                    mPhone.setError("Phone Number is Required");
                    return;
                }

                if(TextUtils.isEmpty(home_address)){
                    mAddress.setError("Address is Required");
                    return;
                }

                //register the user in Firebase(update detail)---------------------------------------------------------

                Orders order = new Orders(phone_number,home_address,shoe_color,shoe_size,shoe_qty,last_total,user_name);
                db = FirebaseDatabase.getInstance();
                reference = db.getReference("Orders");
                reference.child(user_name).setValue(order).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
                //push()  method use to every oder genarate unique id unique key
                //if we dont use it data wille overrided

                //shoeDbRef.push().setValue(order);
                Toast.makeText(Order.this,"Order Successfullt Enterd ",Toast.LENGTH_SHORT).show();


                //Goto Edit Order Actovity-------------


            Intent intent22 = new Intent(Order.this, MainActivity.class);
//                intent.putExtra("total_price",total);
                intent22.putExtra("user_name",user_name);
             startActivity(intent22);

            }
        });







    }

    private void selectImage() {


    }
}