package com.cadenza.shoeapp.SellerPart;

import android.app.Activity;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.cadenza.shoeapp.R;

public class SellerAdapter extends ArrayAdapter{

    private Activity context;
    private String[] shoe_name;
    private  Integer[] imageId;
    Integer[] backgroundId;
    private  Integer[] shoesPrice;


    public SellerAdapter(Activity context, String[] shoe_name, Integer[] imageId,Integer[] backgroundId , Integer[] shoesPrice) {
        super(context, R.layout.single_item_raw,shoe_name);

        this.context = context;
        this.shoe_name = shoe_name;
        this.imageId = imageId;
        this.backgroundId = backgroundId;
        this.shoesPrice = shoesPrice;
    }

}
