package com.cadenza.shoeapp.SellerPart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.cadenza.shoeapp.CustermerPart.AddItems;
import com.cadenza.shoeapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SportShoeUpload extends AppCompatActivity {


    Spinner spin_size,spin_color;
    Button addBtn;
    EditText hAddress,mPhone,userName,shoePrice,shoeQty;

    String[] size = { "Medium", "Large", "Max"};
    String[] color = {"Black","White","Red","Yellow"};

    FirebaseDatabase db;
    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_shoe_upload);

        spin_size = (Spinner) findViewById(R.id.spinner_size);
        spin_color = (Spinner) findViewById(R.id.spinner_color);
        addBtn = (Button) findViewById(R.id.Add_btn);

        hAddress = (EditText) findViewById(R.id.addressfield);
        mPhone = (EditText) findViewById(R.id.mob_num_field);
        userName = (EditText) findViewById(R.id.User_name_field);
        shoePrice = (EditText) findViewById(R.id.shoe_price);
        shoeQty = (EditText) findViewById(R.id.qty_shoe);



        ArrayAdapter bb = new ArrayAdapter(this,android.R.layout.simple_spinner_item,size);
        ArrayAdapter cc = new ArrayAdapter(this,android.R.layout.simple_spinner_item,color);

        spin_size.setAdapter(bb);
        spin_color.setAdapter(cc);


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String shoe_color =  spin_color.getSelectedItem().toString();
                String shoe_size =  spin_size.getSelectedItem().toString();

                String  phone_number = mPhone.getText().toString().trim();
                String  home_address = hAddress.getText().toString().trim();
                String  u_name = userName.getText().toString().trim();
                String  shoe_price = shoePrice.getText().toString().trim();
                String  shoe_qty = shoeQty.getText().toString().trim();

              if(TextUtils.isEmpty(phone_number)){
                    mPhone.setError("Phone Number is Required");
                    return;
                }

                if(TextUtils.isEmpty(home_address)){
                    hAddress.setError("Address is Required");
                    return;
                }


                //register the user in Firebase  (insert data)

                AddItems item = new AddItems(phone_number,home_address,shoe_color,shoe_size,shoe_qty,u_name,shoe_price);
                db = FirebaseDatabase.getInstance();
                reference = db.getReference("sport_shoe_item");
                reference.child(u_name).setValue(item).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });

                //Toast Mg-----------

                Toast.makeText(SportShoeUpload.this,"Item Successfullt Enterd ",Toast.LENGTH_SHORT).show();



                mPhone.setText("");
                hAddress.setText("");
                userName.setText("");
                shoePrice.setText("");
                shoeQty.setText("");


            }
        });



    }
}