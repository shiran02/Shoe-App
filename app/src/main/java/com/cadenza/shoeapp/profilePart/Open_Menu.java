package com.cadenza.shoeapp.profilePart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.cadenza.shoeapp.R;
import com.cadenza.shoeapp.SellerPart.MainMenuInteface;

public class Open_Menu extends AppCompatActivity {
    private static int SPLASH_SCREEN = 5000;

    //Variables

    Animation topAnim,bottomAnim;

    ImageView image;
    TextView logo,slogoan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_menu);


        //Animation
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);


        //hooks

        image = findViewById(R.id.icon);


        image.setAnimation(topAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Open_Menu.this, MainMenuInteface.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }
}