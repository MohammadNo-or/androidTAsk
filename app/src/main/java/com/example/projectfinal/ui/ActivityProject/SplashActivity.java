package com.example.projectfinal.ui.ActivityProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.widget.ImageView;

import com.example.projectfinal.R;

import com.example.projectfinal.changLanguage.LocaleHelper;


import static com.example.projectfinal.utilities.Constants.SPLASH_TIMER;


public class SplashActivity extends AppCompatActivity {
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        img = findViewById(R.id.imgcard);


      //  Animation a = AnimationUtils.loadAnimation(this, R.anim.roate_center);
      //  img.startAnimation(a);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {



                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);

            }
        }, SPLASH_TIMER );

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }


}
