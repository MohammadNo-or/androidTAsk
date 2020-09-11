package com.example.projectfinal.ui.ActivityProject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.projectfinal.R;
import com.example.projectfinal.changLanguage.General;
import com.example.projectfinal.database.FavoriteDatabase;
import com.example.projectfinal.learnActiv.A1;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;


import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectfinal.changLanguage.LocaleHelper;



public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static FavoriteDatabase favoriteDatabase;

    private AppBarConfiguration mAppBarConfiguration;
    TextView navUsername,navUserEmail;
    ImageView imgNavHeader;
    String str="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
        favoriteDatabase= Room.databaseBuilder(getApplicationContext(),FavoriteDatabase.class,"myfavdb").allowMainThreadQueries().build();






        //+++++++++++++++++++++++++++++++++++++++++++++++
                //لأخفاء الtoolBar
        // getSupportActionBar().hide();
        //+++++++++++++++++++++++++++++++++++++++++++++++

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view_admin);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_A, R.id.nav_Sh, R.id.nav_SHR,
                R.id.nav_LO, R.id.nav_Language, R.id.nav_Update, R.id.nav_Toadd_category, R.id.nav_addCard,R.id.nav_update_info,R.id.nav_all_user,R.id.nav_comunicate)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);



        NavigationView nav = (NavigationView) findViewById(R.id.nav_view_admin);
        View headerView = nav.getHeaderView(0);

        navUsername = (TextView) headerView.findViewById(R.id.txtNavName);
        navUserEmail = (TextView) headerView.findViewById(R.id.txtNavEmail);
        imgNavHeader = headerView.findViewById(R.id.imgNavHeader);








if(str!="") {
    str=getIntent().getExtras().getString("nameDr");
    navUsername.setText(str);
   // navUserEmail.setText("Hello : " + " str ");

}
else{

    //navUsername.setText("welcom : user");
     //navUserEmail.setText("Hello : " + "  ");

}


















    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }


    @Override
    public void onBackPressed() {

        AlertDialog.Builder a = new AlertDialog.Builder(this);
        a.setNeutralButton(getResources().getString(R.string.cancel), null);
        a.setCancelable(false);
        a.setIcon(R.drawable.ic_launcher_background);
        a.setNegativeButton(getResources().getString(R.string.No), null);
        a.setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });
        a.setTitle(R.string.title);
        a.setMessage(getResources().getString(R.string.msg));
        a.show();


    }


    //القائمة المنسدلة وعناصرها
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);









        return true;
    }

    //التحديد على القائمة المنسدلة
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.com_Ct) {
            Intent x=new Intent(MainActivity.this, A1.class);
            Toast.makeText(this, "communicate Now", Toast.LENGTH_SHORT).show();
            startActivity(x);
        } else if (item.getItemId() == R.id.action_settings) {
            Toast.makeText(this, "setting App", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(MainActivity.this,styleSetting.class);
            startActivity(i);


        } else if (item.getItemId() == R.id.log_out) {
            Intent i=new Intent(MainActivity.this,LoginActivity.class);
            Toast.makeText(this, "log Out from App", Toast.LENGTH_SHORT).show();
            startActivity(i);
            finish();

        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        if (id == R.id.nav_addCard) {


        } else if (id == R.id.nav_host_fragment) {

        } else if (id == R.id.nav_LO) {
            General.addToSharedPreference(MainActivity.this, "keep", false);
            finish();


            Intent i=new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
