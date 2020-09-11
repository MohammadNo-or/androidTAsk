package com.example.projectfinal.ui.changLanguages;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.projectfinal.R;
import com.example.projectfinal.utilities.Constants;

import com.example.projectfinal.ui.ActivityProject.MainActivity;

import com.example.projectfinal.changLanguage.LocaleHelper;

import static com.example.projectfinal.changLanguage.General.addToSharedPreference;

public class changLanguageApp extends Fragment {

    private ChangLanguageViewModel mViewModel;

    Button enn, arr;




    public static changLanguageApp newInstance() {
        return new changLanguageApp();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.chang_language, container, false);
        enn = v.findViewById(R.id.en1);
        arr = v.findViewById(R.id.ar1);


        enn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateViews("en");
                getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
                addToSharedPreference(getActivity(), "lang", Constants.ENGLISH_LANG);
                getActivity().finish();

            }
        });


        arr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                updateViews("ar");


                getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
                addToSharedPreference(getActivity(), "lang", Constants.ARABIC_LANG);
                getActivity().finish();


            }
        });


        return v;
    }


    private void updateViews(String languageCode) {
        Context context = LocaleHelper.setLocale(getActivity(), languageCode);
        Resources resources = getActivity().getResources();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ChangLanguageViewModel.class);
        // TODO: Use the ViewModel
    }


}
