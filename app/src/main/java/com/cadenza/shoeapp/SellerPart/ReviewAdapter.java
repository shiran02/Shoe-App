package com.cadenza.shoeapp.SellerPart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cadenza.shoeapp.R;
import com.cadenza.shoeapp.Rate.Rate;
import com.cadenza.shoeapp.Rate.RateAdapter;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyRiviewHolder>{

    Context context;
    ArrayList<Review> list;

    public ReviewAdapter(Context context, ArrayList<Review> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyRiviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.sitem,parent,false);
        return  new MyRiviewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRiviewHolder holder, int position) {

        Review review = list.get(position);

        holder.phone_numberField.setText((review.getPhone_number()));
        holder.shoe_colorField.setText((review.getShoe_color()));
        holder.shoe_qtyField.setText((review.getShoe_qty()));
        holder.u_nameField.setText((review.getU_name()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyRiviewHolder extends RecyclerView.ViewHolder{

        TextView phone_numberField,shoe_colorField,shoe_qtyField,u_nameField;

        public MyRiviewHolder(@NonNull View itemView) {
            super(itemView);

            phone_numberField = (itemView).findViewById(R.id.phone_number_field);
            shoe_colorField = (itemView).findViewById(R.id.shoe_color_field);
            shoe_qtyField = (itemView).findViewById(R.id.shoe_qty_field);
            u_nameField = (itemView).findViewById(R.id.user_name_field);
        }
    }

}
