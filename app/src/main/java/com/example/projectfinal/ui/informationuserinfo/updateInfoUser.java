package com.example.projectfinal.ui.informationuserinfo;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.projectfinal.R;
import com.example.projectfinal.models.models.ideaType;
import com.google.android.material.textfield.TextInputEditText;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickCancel;
import com.vansuita.pickimage.listeners.IPickResult;

public class updateInfoUser extends Fragment {

    private UpdateInfoUserViewModel mViewModel;

    TextInputEditText InputNameInfo,InputPassInfo,InputTelephoneInfo,InputEmailInfo,InputDateInfo,InputCountryInfo,InputJenderInfo;
    Button btnUpdatInfo;
    ImageView imgInfo;
String encodedImage="";
    public static updateInfoUser newInstance() {
        return new updateInfoUser();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View v=inflater.inflate(R.layout.update_info, container, false);

        InputNameInfo=v.findViewById(R.id.etInfoName);
        InputPassInfo=v.findViewById(R.id.edPassInfo);
        InputDateInfo=v.findViewById(R.id.userDateB);
        InputTelephoneInfo=v.findViewById(R.id.edTelInfo);
        InputEmailInfo=v.findViewById(R.id.userEmail);
        imgInfo=v.findViewById(R.id.imgInfo);
        btnUpdatInfo=v.findViewById(R.id.btnUpdtInf);
        InputCountryInfo=v.findViewById(R.id.userCountry);
        InputJenderInfo=v.findViewById(R.id.userJender);

        imgInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PickImageDialog.build(new PickSetup())
                        .setOnPickResult(new IPickResult() {
                            @Override
                            public void onPickResult(PickResult r) {
                                //TODO: do what you have to...
                                imgInfo.setImageBitmap(r.getBitmap());

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






        return v;










    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(UpdateInfoUserViewModel.class);
        // TODO: Use the ViewModel
    }

}
