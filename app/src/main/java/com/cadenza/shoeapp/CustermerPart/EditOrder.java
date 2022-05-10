package com.cadenza.shoeapp.CustermerPart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cadenza.shoeapp.R;

public class EditOrder extends AppCompatActivity {

    TextView shoe_price_txt,shoe_name_txt,shoes_total_price,qty_count_txt;
    ImageView plus_Btn,minus_Btn;
    int numberOrder = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_order);

        shoes_total_price = findViewById(R.id.total_price_txt);
        shoe_name_txt = findViewById(R.id.shoe_name_txt);
        shoe_price_txt = findViewById(R.id.order_price_txt);
        qty_count_txt = findViewById(R.id.Quantity_count_txt);

        plus_Btn = findViewById(R.id.order_plus_pic);
        minus_Btn = findViewById(R.id.order_minus_pic);

        int Total_Price=getIntent().getIntExtra("total_price",0);
        int Shoe_quntity=getIntent().getIntExtra("shoe_qty",0);

        shoes_total_price.setText(" "+ Total_Price);
        qty_count_txt.setText(""+Shoe_quntity);



        plus_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberOrder = numberOrder +1 ;
                qty_count_txt.setText(String.valueOf(numberOrder));
                shoes_total_price.setText(" "+ Total_Price);
            }
        });

        minus_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(numberOrder>1){
                    numberOrder = numberOrder -1;
                }
                qty_count_txt.setText(String.valueOf(numberOrder));
            }
        });

    }
}