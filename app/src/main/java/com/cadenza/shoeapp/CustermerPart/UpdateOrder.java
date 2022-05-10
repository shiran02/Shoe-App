package com.cadenza.shoeapp.CustermerPart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.cadenza.shoeapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UpdateOrder extends AppCompatActivity {

    DatabaseReference databaseReference;


    String[] size = { "Medium", "Large", "Max"};
    int[] number = {1,2,3,4,5,6,7};
    String[] color = {"Black","White","Red","Yellow"};

    Spinner spin_size,spin_color,spin3;
    EditText Order_qty,order_price,p_number,h_Address,user_name;
    Button cal_btn,update_btn,delete_btn;
    int New_price;
    int quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_order);

        spin_size = (Spinner) findViewById(R.id.update_size);
        spin_color = (Spinner) findViewById(R.id.update_color);


        Order_qty = (EditText) findViewById(R.id.Update_qty_shoe);
        order_price = (EditText) findViewById(R.id.update_total);
        p_number = (EditText) findViewById(R.id.Up_mob_num_field);
        h_Address = (EditText) findViewById(R.id.up_addressfield);
        user_name = (EditText) findViewById(R.id.User_name_field);

        cal_btn = (Button) findViewById(R.id.cal_btn);
        update_btn = (Button) findViewById(R.id.update_btn);
        delete_btn = (Button) findViewById(R.id.delete_btn);


        //ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,size);
        ArrayAdapter bb = new ArrayAdapter(this,android.R.layout.simple_spinner_item,size);
        ArrayAdapter cc = new ArrayAdapter(this,android.R.layout.simple_spinner_item,color);


        //aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin_size.setAdapter(bb);
        spin_color.setAdapter(cc);

        String u_name =  user_name.getText().toString().trim();


        //get values...........

        int s_quantity = getIntent().getExtras().getInt("shoe_qty");
        String s_color = getIntent().getExtras().getString("shoe_qty");
        String us_name = getIntent().getExtras().getString("user_name");
        int s_price = s_quantity*33;
        //setValues

        Order_qty.setText(""+s_quantity);
        order_price.setText(""+s_price);
        //user_name.setText(us_name);

        //----------cal button set listener-------------

        cal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //convert String----------

                String New_qty = Order_qty.getText().toString();
                String Old_price = order_price.getText().toString();

                //convert integer-------

                quantity =Integer.parseInt(New_qty);
                int O_price =Integer.parseInt(Old_price);


                 New_price = quantity*32 ;

                order_price.setText(""+New_price);

            }
        });


        // set ClickLister for Update Btn------------

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String  phone_number = p_number.getText().toString().trim();
                String  home_address = h_Address.getText().toString().trim();
                String shoe_color =  spin_color.getSelectedItem().toString();
                String shoe_size =  spin_size.getSelectedItem().toString();
                String u_name =  user_name.getText().toString().trim();



                UpdateData(phone_number,home_address,shoe_color,shoe_size,quantity,New_price,u_name);


            }
        });


        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = user_name.getText().toString();

                if(!userName.isEmpty()){

                    deleteData(userName);

                }else{
                    Toast.makeText(UpdateOrder.this,"Enter The User Name",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void deleteData(String userName) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Orders");
        databaseReference.child(userName).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Toast.makeText(UpdateOrder.this,"Succesessfully delete ",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(UpdateOrder.this,"Delete Fail ",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void UpdateData(String phone_number, String home_address, String shoe_color, String shoe_size, int quantity, int new_price,String user_name) {

        HashMap order = new HashMap();
        order.put("phone_address",phone_number);
        order.put("home_address",home_address);
        order.put("shoe_color",shoe_color);
        order.put("shoe_qty",quantity);
        order.put("shoe_size",shoe_size);
        order.put("last_total",new_price);

        databaseReference = FirebaseDatabase.getInstance().getReference("Orders");

        databaseReference.child(user_name).updateChildren(order).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    Toast.makeText(UpdateOrder.this,"Update Succsesss Fully ",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(UpdateOrder.this,"Update Fail ",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


}