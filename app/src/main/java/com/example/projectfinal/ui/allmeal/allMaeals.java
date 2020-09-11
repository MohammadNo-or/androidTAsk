package com.example.projectfinal.ui.allmeal;

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

public class allMaeals extends Fragment {

    private ShowRecViewModel mViewModel;
    RecyclerView rec;


    public static allMaeals newInstance() {
        return new allMaeals();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.show_rec, container, false);
        rec = v.findViewById(R.id.rec);

        ViewPager viewPager = v.findViewById(R.id.viewPager);
        ImageAdapter adapter = new ImageAdapter(getActivity());
        viewPager.setAdapter(adapter);

        rec.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));


//__________________________________________________________________________________



        getAllMeals();


        return v;
    }


    public void getAllMeals() {

        MainActivityViewModel vm = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);
        vm.all_Meals( rec, getActivity()).observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {

            }
        });

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ShowRecViewModel.class);
        // TODO: Use the ViewModel
    }

}
