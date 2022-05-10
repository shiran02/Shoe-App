package com.cadenza.shoeapp.SellerPart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.cadenza.shoeapp.R;

public class SellerListActivity extends AppCompatActivity {


    ListView listView;

    String[] shoe_name = {"Jordhan Delta", "Vintage Sport", "Zeven Blazer", "WoodLand shoe"};

    Integer[] price = {12, 32, 23, 2};

    Integer[] imgid={
            R.drawable.p,R.drawable.w,
            R.drawable.shoe,R.drawable.shoe3,
            R.drawable.l_pic,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_list);

        listView = findViewById(R.id.listView);

        //SellerAdapter adapter=new SellerAdapter(this, shoe_name, price,imgid);
        listView=(ListView)findViewById(R.id.listView);
       // listView.setAdapter(adapter);
    }
}