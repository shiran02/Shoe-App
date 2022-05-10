package com.cadenza.shoeapp.CustermerPart;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cadenza.shoeapp.R;

import java.security.AccessController;
import java.util.ArrayList;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.MyView> {

    Context context;
    ArrayList<UserOrders> list;




    public OrderListAdapter(Context context, ArrayList<UserOrders> list) {
        this.context = context;
        this.list = list;
    }




    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return  new MyView(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
            UserOrders usermod = list.get(position);

            int price = usermod.getShoe_qty()*32;
            int qty = usermod.getShoe_qty();
            String color = usermod.getShoe_color();
            String user_name = usermod.get_username();


            holder.shoe_order_Total_price.setText(""+price);
            holder.shoe_Order_color.setText((usermod.getShoe_color()));
            holder.shoe_order_qty.setText((""+usermod.getShoe_qty()));
            holder.user_order_name.setText((usermod.get_username()));
          // holder.shoe_order_size.setText((usermod.getShoe_size()));


        holder.updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //go for data Update inter face

                //holder.shoe_order_Total_price.setText("ho");
                Intent intent = new Intent (view.getContext(), UpdateOrder.class);
                intent.putExtra("shoe_qty",qty);
                intent.putExtra("shoe_color",color);
                intent.putExtra("shoe_price",price);
                intent.putExtra("user_name",user_name);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyView extends RecyclerView.ViewHolder {

        public AccessController img;
        ImageView pic;
        TextView shoe_Order_color,shoe_order_size;
        EditText shoe_order_qty,shoe_order_Total_price,user_order_name;
        Button updatebtn,editbtn;


        public MyView(@NonNull View itemView) {
            super(itemView);




            shoe_Order_color = itemView.findViewById(R.id.shoe_color_txt);
            shoe_order_qty = itemView.findViewById(R.id.sho_cart_qty);
            user_order_name = itemView.findViewById(R.id.username_txt);


            //methana shoe size eka danne item layout eke size eka wenuwata;

            shoe_order_Total_price = itemView.findViewById(R.id.order_cart_price_txt);

            updatebtn = (Button)itemView.findViewById(R.id.updateBtn);
            editbtn = (Button)itemView.findViewById(R.id.deleteBtn);
            pic = itemView.findViewById(R.id.order_detail_pic);




        }

    }


}
