package com.cadenza.shoeapp.SellerPart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cadenza.shoeapp.CustermerPart.MyAdapter;
import com.cadenza.shoeapp.CustermerPart.MainActivity3;
import com.cadenza.shoeapp.CustermerPart.OrderList;
import com.cadenza.shoeapp.R;
import com.cadenza.shoeapp.Rate.RatingActivity;
import com.cadenza.shoeapp.Rate.RiviewRateActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    CardView cardView1, cardView2, cardView3, cardView4, cardView5, cardView6;
    ListView listView;
    ImageView orderpic;
    Button ordeListbtn;

    TextView prof_name,prof_email,prof_contactNum;

    //drawable layout ---------------------

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;

    //------------------------------------------


    Integer[] imageId = {R.drawable.p, R.drawable.l_pic, R.drawable.shoe, R.drawable.w};
    String[] shoe_name = {"Jordhan Delta", "Vintage Sport", "Zeven Blazer", "WoodLand shoe"};
    Integer[] price = {12, 32, 23, 2};
    Integer[] backgroundId = {R.drawable.bg1, R.drawable.bg2, R.drawable.bg3, R.drawable.bg4};




    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String userId;
    String customer_Name,seller_email,seller_phone;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        //ordeListbtn = findViewById(R.id.button);
        //orderpic = findViewById(R.id.orderDetail);


        // get Data from Login and register for header ---------------------------------------------

        //from login----
        //String cust_name = getIntent().getExtras().getString("cus_name");
       // String cust_mail = getIntent().getExtras().getString("custermer_Mail");




        //------------------------------------------------------------------------------------------


        //set headr detail to header ______________________________________________________________

        prof_name = findViewById(R.id.cust_profile_name);
        prof_email = findViewById(R.id.cust_profile_name);
        prof_contactNum = findViewById(R.id.cust_profile_name);

       // prof_name.setText(""+cust_name);
      //  prof_email.setText(""+cust_mail);




        //__________________________________________________________________________________________

        MyAdapter adapter = new MyAdapter(MainActivity.this, shoe_name, imageId, backgroundId, price);
        listView.setAdapter(adapter);


        // String user_name = getIntent().getExtras().getString("user_name");
        //for listview

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                intent.putExtra("shoe_name", shoe_name[position]);
                intent.putExtra("shoe_img", imageId[position]);
                intent.putExtra("shoe_pri", price[position]);
                startActivity(intent);

            }
        });

//        ordeListbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

//
//        // drawer layout instance to toggle the menu icon to open
//        // drawer and back button to close drawer
//        drawerLayout = findViewById(R.id.my_drawer_layout);
//        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
//
//        // pass the Open and Close toggle for the drawer layout listener
//        // to toggle the button
//        drawerLayout.addDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();
//
//        // to make the Navigation drawer icon always appear on the action bar
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


//        cardView1 = findViewById(R.id.cd1);
//        cardView2 = findViewById(R.id.cd2);
//        cardView3 = findViewById(R.id.cd3);
//        cardView4 = findViewById(R.id.cd4);
//        cardView5 = findViewById(R.id.cd5);
//        cardView6 = findViewById(R.id.cd6);


//        orderpic.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, OrderList.class);
//                // intent.putExtra("user_name",user_name);
//                startActivity(intent);
//            }
//        });




        //------------------navigater code--------------------------------------------------


        setUpToolbar();
     navigationView = (NavigationView) findViewById(R.id.navigation_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case  R.id.nav_cart:

                        Intent intent = new Intent(MainActivity.this, OrderList.class);
                        startActivity(intent);
                        break;


                    case  R.id.nav_rate:{

                        Intent intent2 = new Intent(MainActivity.this, RatingActivity.class);
                        startActivity(intent2);
                        break;

                    }


                    case  R.id.nav_reviewRate:{

                        Intent intent3 = new Intent(MainActivity.this, RiviewRateActivity.class);
                        startActivity(intent3);
                        break;

                    }
                    //       break;
                    case  R.id.nav_share:{

                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        String shareBody =  "http://play.google.com/store/apps/detail?id=" + getPackageName();
                        String shareSub = "Try now";
                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                        startActivity(Intent.createChooser(sharingIntent, "Share using"));

                    }
                    break;
                }
                return false;
            }
        });
    }

    public void setUpToolbar() {
        drawerLayout = findViewById(R.id.drawerLayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

    }

}