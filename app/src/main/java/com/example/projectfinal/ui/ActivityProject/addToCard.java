package com.example.projectfinal.ui.ActivityProject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;

import com.example.projectfinal.R;
import com.example.projectfinal.database.FavoriteDatabase;
import com.example.projectfinal.database.FavoriteList;
import com.example.projectfinal.helper.CardRecycleAdapter;
import com.example.projectfinal.helper.FavoriteAdapter;

import java.util.ArrayList;
import java.util.List;


public class addToCard extends AppCompatActivity {
    public static FavoriteDatabase favoriteDatabase;

    CardRecycleAdapter adapterCard;
    RecyclerView recCard;
     FavoriteAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_card);

        recCard=findViewById(R.id.product_recs);


        recCard.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));




        getFavDataSearch();
    }

    public void getFavDataSearch() {
        List<FavoriteList> favoriteLists= MainActivity.favoriteDatabase.favoriteDao().getFavoriteData();

        adapter=new FavoriteAdapter(favoriteLists,this);
        recCard.setAdapter(adapter);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main,menu);

        return super.onCreateOptionsMenu(menu);
    }
}
