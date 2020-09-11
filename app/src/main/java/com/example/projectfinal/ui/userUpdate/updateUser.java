package com.example.projectfinal.ui.userUpdate;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.projectfinal.R;
import com.example.projectfinal.models.models.MainActivityViewModel;
import com.example.projectfinal.models.models.ideaType;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickCancel;
import com.vansuita.pickimage.listeners.IPickResult;

public class updateUser extends Fragment {

ImageView imgUp;
Button btnUp;
EditText oldName,newName,oldPass,newPass;
    String encodedImage = "";




    private UpdateUserViewModel mViewModel;

    public static updateUser newInstance() {
        return new updateUser();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.update_user, container, false);

        imgUp=v.findViewById(R.id.imgUpdate);
        btnUp=v.findViewById(R.id.btnUpdtInf);

        newName=v.findViewById(R.id.edupNewName);
        oldPass=v.findViewById(R.id.edupOldpass);
        newPass=v.findViewById(R.id.edupNewpass);

imgUp.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        PickImageDialog.build(new PickSetup())
                .setOnPickResult(new IPickResult() {
                    @Override
                    public void onPickResult(PickResult r) {
                        //TODO: do what you have to...
                        imgUp.setImageBitmap(r.getBitmap());

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

       btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userInfoSaved();

            }
        });









        return v;
    }


    private void userInfoSaved()
    {
        if (TextUtils.isEmpty(newName.getText().toString()))
        {
            Toast.makeText(getActivity(), "Name is mandatory.", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(oldPass.getText().toString()))
        {
            Toast.makeText(getActivity(), "password The Old.", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(newPass.getText().toString()))
        {
            Toast.makeText(getActivity(), "password The New.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            MainActivityViewModel vm = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);
            vm.update_user(newName.getText().toString(), oldPass.getText().toString(), newPass.getText().toString(), encodedImage, getActivity()).observe(getActivity(), new Observer<String>() {
                @Override
                public void onChanged(String s) {

                }
            });
        }
    }








    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(UpdateUserViewModel.class);
        // TODO: Use the ViewModel
    }

}
