package com.example.projectfinal.ui.updatecategory;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectfinal.R;
import com.example.projectfinal.models.models.Category;
import com.example.projectfinal.models.models.MainActivityViewModel;
import com.example.projectfinal.models.models.ResponceCategory;
import com.example.projectfinal.models.models.ideaType;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickCancel;
import com.vansuita.pickimage.listeners.IPickResult;

import java.util.ArrayList;

public class updateCategory extends Fragment {

    private UpdateCategoryViewModel mViewModel;

TextView txtAr,txtEn,txtNew;
Button btnCatUp;
ImageView imgCategory;
    private boolean isError = false;
    private ProgressDialog loadingBar;
    String encodedImage = "";



    public static updateCategory newInstance() {
        return new updateCategory();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {



        View v=inflater.inflate(R.layout.update_category, container, false);


        txtAr= v.findViewById(R.id.txtNameCatAr);
        txtNew= v.findViewById(R.id.txtNews);
        txtEn= v.findViewById(R.id.txtNameCatEn);
        btnCatUp= v.findViewById(R.id.btnUpCat);
        imgCategory= v.findViewById(R.id.imgUpCat);
        loadingBar = new ProgressDialog(getActivity());



        imgCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PickImageDialog.build(new PickSetup())
                        .setOnPickResult(new IPickResult() {
                            @Override
                            public void onPickResult(PickResult r) {
                                //TODO: do what you have to...
                                imgCategory.setImageBitmap(r.getBitmap());

                                encodedImage = ideaType.getConvertToString(r.getBitmap());

                            }
                        })
                        .setOnPickCancel(new IPickCancel() {
                            @Override
                            public void onCancelClick() {
                                //TODO: do what you have to if user clicked cancel
                                Toast.makeText(getActivity(), "cancle", Toast.LENGTH_LONG).show();

                            }
                        }).show(getActivity().getSupportFragmentManager());


            }
        });










btnCatUp.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        MainActivityViewModel vm = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);
        vm.updateCategory(txtEn.getText().toString(), txtAr.getText().toString(), encodedImage, getActivity(),txtNew.getText().toString()).observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {

            }
        });





    }
});




        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(UpdateCategoryViewModel.class);
        // TODO: Use the ViewModel
    }









    //method convert add to spinner
    public static int convertSpinnerId(ResponceCategory rc, Spinner s) {

        ArrayList<Category> Cat = rc.getCategoryArrayList();

        for (int i = 0; i < rc.getCategoryArrayList().size(); i++) {
            if (s.getSelectedItem().equals(Cat.get(i).getCategory())) {

                return Cat.get(i).getId();
            }
        }

        return -1;
    }





//method amimation text view

    public void getAnime_EditText(TextView v) {

        v.setError("Required Field");
        isError = true;
        Animation myAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
        v.startAnimation(myAnim);


    }









}
