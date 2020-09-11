package com.example.projectfinal.ui.allmealsearch;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.projectfinal.R;
import com.example.projectfinal.learnActiv.ImageAdapter;
import com.example.projectfinal.models.models.MainActivityViewModel;

public class allMealBySearch extends Fragment {


    private AllMealBySearchViewModel mViewModel;
    private  RecyclerView recSrch;

    private  EditText etfilter;
    private  ImageButton btnFilter;
    public static allMealBySearch newInstance() {
        return new allMealBySearch();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


View v=inflater.inflate(R.layout.all_meal_search_fragment, container, false);


        recSrch = v.findViewById(R.id.recSearchFrag);
        etfilter = v.findViewById(R.id.etFilter);
        btnFilter = v.findViewById(R.id.Filter);


        recSrch.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));


        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getAllMeals();


            }
        });


//__________________________________________________________________________________

        etfilter.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

                getAllMeals();
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


        etfilter.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {


                    getAllMeals();


                    return true;
                }
                return false;
            }
        });


        getAllMeals();













        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AllMealBySearchViewModel.class);
        // TODO: Use the ViewModel
    }



    public void getAllMeals() {

        MainActivityViewModel vm = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);
        vm.all_Meals_search(etfilter.getText().toString(), recSrch, getActivity()).observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {

            }
        });

    }






}
