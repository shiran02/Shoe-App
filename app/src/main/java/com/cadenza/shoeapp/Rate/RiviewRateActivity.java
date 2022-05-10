package com.cadenza.shoeapp.Rate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.cadenza.shoeapp.CustermerPart.ReadData;
import com.cadenza.shoeapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RiviewRateActivity extends AppCompatActivity {

    Button delete_Btn;
    RecyclerView recyclerView;
    DatabaseReference database;
    RateAdapter rateadapter;
    ArrayList<Rate> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riview_rate);


        recyclerView = findViewById(R.id.RateList);
        database = FirebaseDatabase.getInstance().getReference("Rates");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        list = new ArrayList<>();
        rateadapter = new RateAdapter(this,list);
        recyclerView.setAdapter(rateadapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshots : snapshot.getChildren()){
                    Rate raster = dataSnapshots.getValue(Rate.class);
                    list.add(raster);
                }
                rateadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






    }
}