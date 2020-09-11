package com.example.projectfinal.ui.alluser;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.projectfinal.R;
import com.example.projectfinal.models.models.MainActivityViewModel;

public class allUser extends Fragment {

    private AllUserViewModel mViewModel;
    RecyclerView recAllUser;
    TextView txtUserRec;
    ImageButton imgSearch;


    public static allUser newInstance() {
        return new allUser();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.all_user, container, false);

        recAllUser = v.findViewById(R.id.recUser);
        txtUserRec = v.findViewById(R.id.filterSearchUser);
        imgSearch = v.findViewById(R.id.imgUserSearch);

        recAllUser.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));





        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getDataUserRecycler();


            }
        });


        txtUserRec.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

                getDataUserRecycler();
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


        txtUserRec.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {


                    getDataUserRecycler();


                    return true;
                }
                return false;
            }
        });


        getDataUserRecycler();

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AllUserViewModel.class);
        // TODO: Use the ViewModel
    }

    public void getDataUserRecycler() {
        MainActivityViewModel vm = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        vm.all_Users(txtUserRec.getText().toString(), recAllUser, getActivity()).observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {

            }
        });


    }


}
