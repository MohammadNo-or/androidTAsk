package com.example.projectfinal.ui.ActivityProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.projectfinal.R;
import com.example.projectfinal.models.models.MealsModel;


import java.util.List;

import static com.example.projectfinal.utilities.Constants.BASE_HOST;

public class detilesActivity extends AppCompatActivity {
    private List<Pets> petsList;
    int strCount=0;
    int countDet = 0;
    ImageView imgMealDet, imgGoShopping,imgLoveDet;
    TextView txtSum, txtMin, txtCount, txtNameDet, txtPriceDet, txtDesceDet;
    private static final String TAG = "GalleryActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detiles);


        imgMealDet=findViewById(R.id.imgDetailesMeal);
        imgLoveDet=findViewById(R.id.LoveDet);
        imgGoShopping = findViewById(R.id.addshoppingDetailes);
        txtSum = findViewById(R.id.btnSumDet);
        txtMin = findViewById(R.id.btnMinDet);
        txtCount = findViewById(R.id.btnCountDet);
        txtNameDet = findViewById(R.id.txtNameDetailes);
        txtPriceDet = findViewById(R.id.txtPriceDetailes);
        txtDesceDet = findViewById(R.id.txtDescrDetailes);

        countDet=Integer.parseInt(getIntent().getExtras().getString("countNum","0"));
        txtCount.setText(getIntent().getExtras().getString("countNum","0"));




txtNameDet.setText(getIntent().getExtras().getString("nameDet")+" ");
        txtPriceDet.setText(getIntent().getExtras().getString("priceDet")+" $");
        txtDesceDet.setText(getIntent().getExtras().getString("descrDet")+"");

        MealsModel u=(MealsModel)getIntent().getExtras().getSerializable("completeUser");


        Glide.with(this).load(BASE_HOST+u.getUserImage()).into(imgMealDet);






    }




///////////////////////////////////////////////////////////////////////////////////
    public void countSum(View view) {




        countDet++;
        txtCount.setText(Integer.toString(countDet));


    }


    public void countMin(View view) {

        if (countDet > 0) {
            countDet--;
        }

        txtCount.setText(Integer.toString(countDet));

    }

    public void goShoppingCard(View view) {

        Intent i = new Intent(detilesActivity.this, addToCard.class);
        startActivity(i);
    }


    public void swipLike(View view)
    {

        boolean isFavourite = false;
        if (isFavourite) {
            imgLoveDet.setBackgroundResource(R.drawable.likeon);
            isFavourite = false;
           saveState(isFavourite);

        } else {
            imgLoveDet.setBackgroundResource(R.drawable.likeof);
            isFavourite = true;
            saveState(isFavourite);

        }


    }

    private void saveState(boolean isFavourite) {
        SharedPreferences aSharedPreferences = this.getSharedPreferences(
                "Favourite", Context.MODE_PRIVATE);
        SharedPreferences.Editor aSharedPreferencesEdit = aSharedPreferences
                .edit();
        aSharedPreferencesEdit.putBoolean("State", isFavourite);
        aSharedPreferencesEdit.commit();
    }

    private boolean readState() {
        SharedPreferences aSharedPreferences = this.getSharedPreferences(
                "Favourite", Context.MODE_PRIVATE);
        return aSharedPreferences.getBoolean("State", true);
    }

}







