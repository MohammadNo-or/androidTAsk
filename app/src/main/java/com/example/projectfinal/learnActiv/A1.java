package com.example.projectfinal.learnActiv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectfinal.R;

public class A1 extends AppCompatActivity {
    TextView textView1,t2;
    ImageView img;
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a1);

         textView1 = findViewById(R.id.desc);
         t2 = findViewById(R.id.title);
         img =(ImageView) findViewById(R.id.image);
        //textView1.setText(getIntent().getStringExtra("param"));
       // t2.setText(getIntent().getStringExtra("param1"));

btn=findViewById(R.id.btnTel);





    }

    public void open_tel(View view)
    {
        Intent x=new Intent( Intent.ACTION_DIAL,Uri.parse("tel:0797427380"));
        startActivity(x);
    }
}
