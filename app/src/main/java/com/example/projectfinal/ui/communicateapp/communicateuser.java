package com.example.projectfinal.ui.communicateapp;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.projectfinal.R;

public class communicateuser extends Fragment {

    private CommunicateuserViewModel mViewModel;
ImageButton imgCall;
    public static communicateuser newInstance() {
        return new communicateuser();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.communicateuser, container, false);

imgCall=v.findViewById(R.id.btnCall);

imgCall.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent x = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+"0797427380"));
        startActivity(x);
    }
});

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CommunicateuserViewModel.class);
        // TODO: Use the ViewModel
    }

}
