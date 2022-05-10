package com.cadenza.shoeapp.CustermerPart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.cadenza.shoeapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrderList extends AppCompatActivity {


    Button update_Btn;
    RecyclerView recyclerView;
    DatabaseReference database;
    OrderListAdapter orderladapter;
    ArrayList<UserOrders> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        //----------Get Data-----------------------------------------------

        recyclerView = findViewById(R.id.RateList);
        database = FirebaseDatabase.getInstance().getReference("Orders");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        orderladapter = new OrderListAdapter(this,list);
        recyclerView.setAdapter(orderladapter);
         update_Btn = findViewById(R.id.updateBtn);


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    UserOrders userorders = dataSnapshot.getValue(UserOrders.class);
                    list.add(userorders);
                }
                orderladapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

//    private void setOnClickListener() {
//        listener = new OrdrListAdapter.RecycleViewClickListener() {
//            @Override
//            public void onClick(View v, int position) {
//                Intent intent2 = new Intent(getApplicationContext(),UpdateOrder.class);
//                startActivity(intent2);
//            }
//        };
//    }
}