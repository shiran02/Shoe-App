package com.cadenza.shoeapp.CustermerPart;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cadenza.shoeapp.R;

public class MyAdapter extends ArrayAdapter {

    private Activity context;
    private String[] shoe_name;
    private  Integer[] imageId;
    Integer[] backgroundId;
    private  Integer[] shoesPrice;

    public MyAdapter(Activity context, String[] shoe_name, Integer[] imageId,Integer[] backgroundId , Integer[] shoesPrice) {
        super(context, R.layout.single_item_raw,shoe_name);
        this.context = context;
        this.shoe_name = shoe_name;
        this.imageId = imageId;
        this.backgroundId = backgroundId;
        this.shoesPrice = shoesPrice;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View itemview = inflater.inflate(R.layout.single_item_raw,null,true);

        TextView shoename =(TextView)itemview.findViewById(R.id.shoe_name_txt);
        TextView shoeprice =(TextView)itemview.findViewById(R.id.shoe_price_txt);
        ImageView shoeimage =(ImageView)itemview.findViewById(R.id.shoe_image);
        LinearLayout  back_layout = (LinearLayout)itemview.findViewById(R.id.back_groundlayout);

        shoename.setText(shoe_name[position]);
        shoeimage.setImageResource(imageId[position]);
        back_layout.setBackgroundResource(backgroundId[position]);
        shoeprice.setText(""+shoesPrice[position]);
        return itemview;

    }
}
