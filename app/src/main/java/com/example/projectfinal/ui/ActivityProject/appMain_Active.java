package com.example.projectfinal.ui.ActivityProject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.example.projectfinal.R;
import com.example.projectfinal.changLanguage.General;
import com.example.projectfinal.models.models.Prevalent;


import io.paperdb.Paper;

public class appMain_Active extends AppCompatActivity
{
    private Button joinNowButton, loginButton;
    private ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_mainactive);


        joinNowButton = (Button) findViewById(R.id.main_join_now_btn);
        loginButton = (Button) findViewById(R.id.main_login_btn);
        loadingBar = new ProgressDialog(this);


        Paper.init(this);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (General.getBooleanPreferenceValue(appMain_Active.this, "keep")) {
                    Intent i = new Intent(appMain_Active.this, MainActivity.class);
                    startActivity(i);

                } else {
                    Intent intent = new Intent(appMain_Active.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }



               // Intent intent = new Intent(appMain_Active.this, LoginActivity.class);
               // startActivity(intent);
               // finish();
            }
        });


        joinNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(appMain_Active.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });


        String UserPhoneKey = Paper.book().read(Prevalent.UserPhoneKey);
        String UserPasswordKey = Paper.book().read(Prevalent.UserPasswordKey);

        if (UserPhoneKey != "" && UserPasswordKey != "")
        {
            if (!TextUtils.isEmpty(UserPhoneKey)  &&  !TextUtils.isEmpty(UserPasswordKey))
            {

                loadingBar.setTitle("Already Logged in");
                loadingBar.setMessage("Please wait.....");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();
            }
        }
    }




}