package com.example.projectfinal.ui.addmail;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.projectfinal.R;
import com.example.projectfinal.WebService.RestClient;
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


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMailFragment extends Fragment {

    private AddViewModel homeViewModel;
    String encodedImage = "";
    Button btn1;
    TextView ed1, ed2, ed3;
    Spinner sps;

    ImageView img;
    Button b1;
    ArrayAdapter<String> a;
    String x[];
    ResponceCategory data;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.addmail, container, false);

        b1 = v.findViewById(R.id.btn1);
        ed1 = v.findViewById(R.id.ed1);
        ed2 = v.findViewById(R.id.ed2);
        ed3 = v.findViewById(R.id.ed3);
        img = v.findViewById(R.id.imgM);
        sps= v.findViewById(R.id.spAdd);


        Call<ResponceCategory> call = RestClient.getService().Sign_Up_Category("");
        call.enqueue(new Callback<ResponceCategory>() {
            @Override
            public void onResponse(Call<ResponceCategory> call, Response<ResponceCategory> response) {

                data = response.body();
                Toast.makeText(getActivity(), "aaaa", Toast.LENGTH_SHORT).show();
                if (data != null) {
                    ArrayList<Category> arr = data.getCategoryArrayList();
                    if (arr.size() > 0) {
                        x = new String[arr.size()];
                        for (int i = 0; i < arr.size(); i++) {
                            x[i] = arr.get(i).getCategory();
                        }
                        a = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, x);
                        sps.setAdapter(a);
                    } else {
                        Toast.makeText(getActivity(), "555", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponceCategory> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                PickImageDialog.build(new PickSetup())
                        .setOnPickResult(new IPickResult() {
                            @Override
                            public void onPickResult(PickResult r) {
                                //TODO: do what you have to...
                                img.setImageBitmap(r.getBitmap());

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





        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivityViewModel vm = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);
                vm.AddMeals(ed1.getText().toString(), ed2.getText().toString(), ed3.getText().toString(), encodedImage, convertSpinnerId(data, sps)).observe(getActivity(), new Observer<String>() {
                    @Override
                    public void onChanged(String s) {

                    }
                });
            }
        });
        return v;
    }


    public static int convertSpinnerId(ResponceCategory rc, Spinner s) {

        ArrayList<Category> Cat = rc.getCategoryArrayList();

        for (int i = 0; i < rc.getCategoryArrayList().size(); i++) {
            if (s.getSelectedItem().equals(Cat.get(i).getCategory())) {

                return Cat.get(i).getId();
            }
        }

        return -1;
    }

}
