package com.example.projectfinal.ui.addtocategory;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectfinal.R;
import com.example.projectfinal.models.models.MainActivityViewModel;
import com.example.projectfinal.models.models.ideaType;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickCancel;
import com.vansuita.pickimage.listeners.IPickResult;
import com.example.projectfinal.ui.ActivityProject.MainActivity;


public class addCategoryToMeal extends Fragment {


    ImageView imgCategory;
    TextView txtAr, txtEn;
    Button btnCategoryAdd;
    String encodedImage = "";
    private ProgressDialog loadingBar;

    private AddTocartViewModel mViewModel;

    public static addCategoryToMeal newInstance() {
        return new addCategoryToMeal();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.add_category_meal, container, false);

        loadingBar = new ProgressDialog(getActivity());
        imgCategory = v.findViewById(R.id.imgCategory);
        txtEn = v.findViewById(R.id.etEnCategory);
        txtAr = v.findViewById(R.id.etArCategory);
        btnCategoryAdd = v.findViewById(R.id.btnCategory);


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


        btnCategoryAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                txtAr.setError(null);
                txtEn.setError(null);
                boolean isError=false;

                if (txtAr.getText().toString().equals(""))
                {
                    ideaType.getAnime_EditText(txtAr,isError,getActivity());
                }

                if (txtEn.getText().toString().equals(""))
                {
                    ideaType.getAnime_EditText(txtEn,isError,getActivity());
                }
                if(isError)
                {
                    return;
                }

                creatCategory();


            }
        });


        return v;


    }


    private void creatCategory() {
        String nameEn = txtEn.getText().toString();
        String nameAr = txtAr.getText().toString();


        if (TextUtils.isEmpty(nameEn)) {
            Toast.makeText(getActivity(), getResources().getText(R.string.toat_anim_text), Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(nameAr)) {
            Toast.makeText(getActivity(), getResources().getText(R.string.toat_anim_text), Toast.LENGTH_SHORT).show();
        } else {

            loadingBar.setTitle(getResources().getText(R.string.loading_add_categor));
            loadingBar.setMessage(getResources().getText(R.string.loading_add_descr));
            loadingBar.setCanceledOnTouchOutside(true);
            loadingBar.show();
            MainActivityViewModel vm = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);
            vm.add_category_meal(txtEn.getText().toString(), txtAr.getText().toString(), encodedImage, getActivity()).observe(getActivity(), new Observer<String>() {
                @Override
                public void onChanged(String s) {

                    Toast.makeText(getActivity(), "welcom To Category", Toast.LENGTH_SHORT).show();

                }
            });

            startActivity(new Intent(getActivity(), MainActivity.class));


        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AddTocartViewModel.class);
        // TODO: Use the ViewModel
    }

}