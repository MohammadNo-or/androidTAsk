package com.example.projectfinal.ui.showmealvertical;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectfinal.R;

import com.example.projectfinal.models.models.MainActivityViewModel;

import com.example.projectfinal.utilities.EqualSpacingItemDecoration;
import com.example.projectfinal.utilities.Utility;


public class showCategoryVertical extends Fragment {

    private ShowMealViewModel mViewModel;
    RecyclerView rec2;

    public static showCategoryVertical newInstance() {
        return new showCategoryVertical();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.category_vertical, container, false);

        rec2 = v.findViewById(R.id.rec);
        int spacing = 20;
        EqualSpacingItemDecoration equalSpacing = new EqualSpacingItemDecoration(spacing);
        rec2.addItemDecoration(equalSpacing);


        int mNoOfColumns = Utility.calculateNoOfColumns(getActivity());

        rec2.setLayoutManager(new GridLayoutManager(getActivity(), mNoOfColumns));


        MainActivityViewModel vm = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        vm.show_Meal_category("", getActivity(), rec2).observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {

            }
        });


        rec2.getRecycledViewPool().setMaxRecycledViews(0, 0);


        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ShowMealViewModel.class);
        // TODO: Use the ViewModel
    }

}
