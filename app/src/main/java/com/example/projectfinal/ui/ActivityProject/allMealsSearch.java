package com.example.projectfinal.ui.ActivityProject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;

import com.example.projectfinal.R;
import com.example.projectfinal.database.FavoriteDatabase;

public class allMealsSearch extends AppCompatActivity {
    public static FavoriteDatabase favoriteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_meals_search);







        favoriteDatabase= Room.databaseBuilder(getApplicationContext(), FavoriteDatabase.class,"myfavdb").allowMainThreadQueries().build();





    }
}
