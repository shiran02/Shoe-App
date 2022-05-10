package com.cadenza.shoeapp.SellerPart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cadenza.shoeapp.R;
import com.cadenza.shoeapp.profilePart.CustomerRegPart;

public class MainMenuInteface extends AppCompatActivity {

    Button seller_btn,customer_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_inteface);

        seller_btn = findViewById(R.id.seller_as_btn);
        customer_btn = findViewById(R.id.customer_as_btn);



        seller_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

        customer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CustomerRegPart.class));
            }
        });
    }
}