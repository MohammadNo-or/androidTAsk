package com.example.projectfinal.ui.someMeals;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectfinal.R;

public class someShowMeals extends Fragment {

    private SomeShowMealsViewModel mViewModel;

    public static someShowMeals newInstance() {
        return new someShowMeals();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.some_show_meals, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SomeShowMealsViewModel.class);
        // TODO: Use the ViewModel
    }

}
