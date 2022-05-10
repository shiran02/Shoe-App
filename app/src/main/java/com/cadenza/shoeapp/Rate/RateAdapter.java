package com.cadenza.shoeapp.Rate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cadenza.shoeapp.CustermerPart.OrderListAdapter;
import com.cadenza.shoeapp.CustermerPart.UserOrders;
import com.cadenza.shoeapp.R;

import java.util.ArrayList;

public class RateAdapter extends RecyclerView.Adapter<RateAdapter.MyViewHolder>{

    Context context;
    ArrayList<Rate> list;


    public RateAdapter(Context context, ArrayList<Rate> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.rateitem,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Rate rate = list.get(position);

        String rat = rate.getUser_rate();

        holder.ruser_name.setText((rate.getUser_name()));
        holder.ruser_rate.setText((rate.getUser_rate()));
        holder.ruser_comment.setText((rate.getUser_comment()));


        //changes----------------------------------------------------------------------------------

      //  holder.ruser_rate.setText("mrs "+rate.getUser_rate());
      //  holder.ruser_comment.setText("comment : "+rate.getUser_comment());

        //------------------------------------------------------------------------------------------


        //holder.user_comment.setText("shiran ");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView ruser_name ,ruser_comment,ruser_rate;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ruser_name = (itemView).findViewById(R.id.user_namefield);
            ruser_rate = (itemView).findViewById(R.id.user_rate);
            ruser_comment = (itemView).findViewById(R.id.user_mg_field);



        }
    }

}
