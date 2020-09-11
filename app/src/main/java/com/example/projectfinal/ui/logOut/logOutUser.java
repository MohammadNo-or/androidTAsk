package com.example.projectfinal.ui.logOut;

import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectfinal.R;

import com.example.projectfinal.ui.ActivityProject.MainActivity;
import com.example.projectfinal.ui.ActivityProject.appMain_Active;

public class logOutUser extends Fragment {

    private LogOutUserViewModel mViewModel;

    public static logOutUser newInstance() {
        return new logOutUser();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.log_out_user, container, false);

        AlertDialog.Builder a=new AlertDialog.Builder(getActivity());
        a.setTitle(getResources().getString(R.string.titleLogOut));
        a.setMessage(getResources().getString(R.string.msgLogOut));
        a.setPositiveButton(getResources().getText(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent i=new Intent(getActivity(), appMain_Active.class);
                startActivity(i);
                getActivity().finish();


            }
        });

        a.setNegativeButton(getResources().getText(R.string.No), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent i=new Intent(getActivity(), MainActivity.class);
                startActivity(i);
                getActivity().finish();

            }
        });

         a.show();






        return v;
    }





    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LogOutUserViewModel.class);
        // TODO: Use the ViewModel
    }






}
