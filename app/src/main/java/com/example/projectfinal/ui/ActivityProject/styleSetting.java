package com.example.projectfinal.ui.ActivityProject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.projectfinal.R;

public class styleSetting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.style_setting);
    }

    public void styles4(View view) {

setTheme(R.style.styl2);



    }

    public void styles3(View view) {

        setTheme(R.style.style3);



    }

    public void styles2(View view) {

        setTheme(R.style.style4);


    }

    public void styles1(View view) {

        setTheme(R.style.AppTheme);




    }
}
