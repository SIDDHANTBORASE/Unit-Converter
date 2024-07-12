package com.example.unitconvertor;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class Startup extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        ImageView logo = findViewById(R.id.logo);
        Animation hoverEffect = AnimationUtils.loadAnimation(this, R.anim.hover_effect);
        logo.startAnimation(hoverEffect);


        // Delay the launch of MainActivity by 3 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Startup.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}
