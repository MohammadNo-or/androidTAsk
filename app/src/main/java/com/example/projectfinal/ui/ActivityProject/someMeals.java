package com.example.projectfinal.ui.ActivityProject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.projectfinal.R;
import com.example.projectfinal.helper.RecycleAdapter;
import com.example.projectfinal.models.models.MainActivityViewModel;

import com.example.projectfinal.changLanguage.LocaleHelper;

public class someMeals extends AppCompatActivity {
    RecyclerView rec;
    EditText searchFilter;

RecycleAdapter recycleAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.some_meals);




        rec = findViewById(R.id.rec);
        searchFilter = findViewById(R.id.searchFilter);

        rec.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        getDataSomMeal();

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        searchFilter.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

                getDataSomMeal();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

            }
        });


        searchFilter.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {


                    getDataSomMeal();


                    return true;
                }



                return false;


            }
        });


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    public void btnSearch(View view) {

        getDataSomMeal();
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        MenuItem item=menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                recycleAdapter.getFilter().filter(newText);

                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);

    }
/////////////////////////////////////////////////////////////////////////////////////////////////
public void getDataSomMeal()
{
    MainActivityViewModel vm = ViewModelProviders.of(this).get(MainActivityViewModel.class);
    vm.someMealss(searchFilter.getText().toString(), getIntent().getExtras().getInt("idCategory"),this,rec).observe(this, new Observer<String>() {
        @Override
        public void onChanged(String s) {

        }
    });


}



/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

//////////////////////////////////////////////////////////////////////////////////////////////









}


