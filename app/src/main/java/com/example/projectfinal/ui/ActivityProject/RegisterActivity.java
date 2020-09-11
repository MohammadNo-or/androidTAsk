package com.example.projectfinal.ui.ActivityProject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.projectfinal.R;
import com.example.projectfinal.models.models.MainActivityViewModel;
import com.example.projectfinal.models.models.ideaType;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickCancel;
import com.vansuita.pickimage.listeners.IPickResult;

import com.example.projectfinal.changLanguage.LocaleHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;


public class RegisterActivity extends AppCompatActivity {


    private Spinner mGenderSpinner;
    private EditText mBirth;
    private CircleImageView mPicture;

    Calendar myCalendar = Calendar.getInstance();

    private int mGender = 0;
    public static final int GENDER_UNKNOWN = 0;
    public static final int GENDER_MALE = 1;
    public static final int GENDER_FEMALE = 2;

    private String name, species, breed, picture, birth;
    private int id, gender;

    private Menu action;
    private Bitmap bitmap;

    TextView InputName, InputPhoneNumber, InputPassword;
    ImageView imgS;
    String encodedImage = "";
    private boolean isError = false;

    private ProgressDialog loadingBar;
    Button CreateAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);


        mGenderSpinner = findViewById(R.id.gender);
        mBirth = findViewById(R.id.birth);

        mBirth.setFocusableInTouchMode(false);
        mBirth.setFocusable(false);
        mBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(RegisterActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        setupSpinner();


        setDataFromIntentExtra();

        InputName = findViewById(R.id.etSignUp);
        InputPassword = findViewById(R.id.etPassSign);
        InputPhoneNumber = findViewById(R.id.etTelUp);
        CreateAccountButton = findViewById(R.id.btnSign);
        imgS = findViewById(R.id.imgS);
        loadingBar = new ProgressDialog(this);


        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputName.setError(null);
                InputPassword.setError(null);
                InputPhoneNumber.setError(null);
                boolean isError = false;
                String strName = InputName.getText().toString();
                String strPass = InputPassword.getText().toString();
                String strphone = InputPhoneNumber.getText().toString();

                if (TextUtils.isEmpty(strName)) {
                    ideaType.getAnime_EditText(InputName, isError, RegisterActivity.this);
                }

                if (TextUtils.isEmpty(strPass) || strPass.length() < 5) {
                    ideaType.getAnime_EditText(InputPassword, isError, RegisterActivity.this);
                }

                if (TextUtils.isEmpty(strphone) ) {
                    ideaType.getAnime_EditText(InputPhoneNumber, isError, RegisterActivity.this);
                }
                if (isError) {
                    return;
                }

                CreateAccount();

            }
        });


    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }


    private void CreateAccount() {
        String name = InputName.getText().toString();
        String phone = InputPhoneNumber.getText().toString();
        String password = InputPassword.getText().toString();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, getResources().getText(R.string.toat_anim_text), Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, getResources().getText(R.string.toat_anim_text), Toast.LENGTH_SHORT).show();
        } else if (password.length() < 5) {
            Toast.makeText(this, "must be password more than chracters 5", Toast.LENGTH_SHORT).show();
        } else if (phone.length() <= 10) {
            Toast.makeText(this, "must be telephone equals 10 numbers or more", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, getResources().getText(R.string.toat_anim_text), Toast.LENGTH_SHORT).show();
        } else {

            loadingBar.setTitle(getResources().getText(R.string.loading_signUp_acount));
            loadingBar.setCanceledOnTouchOutside(true);
            loadingBar.show();
            MainActivityViewModel vm = ViewModelProviders.of(this).get(MainActivityViewModel.class);
            vm.getSignUser(InputName.getText().toString(), InputPassword.getText().toString(), InputPhoneNumber.getText().toString(), "1", encodedImage).observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {

                }
            });

            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));


        }
    }


    public void GoLogin(View view) {

        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();
    }


    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setBirth();
        }

    };

    private void setBirth() {
        String myFormat = "dd MMMM yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        mBirth.setText(sdf.format(myCalendar.getTime()));
    }

    private void setDataFromIntentExtra() {

        if (id != 0) {

            getSupportActionBar().setTitle("Edit " + name.toString());

            InputName.setText("name");
            InputPhoneNumber.setText("phone");
            InputPassword.setText("password");
            mBirth.setText(birth);

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.skipMemoryCache(true);
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
            requestOptions.placeholder(R.drawable.logo);
            requestOptions.error(R.drawable.logo);

            Glide.with(RegisterActivity.this)
                    .load(picture)
                    .apply(requestOptions)
                    .into(mPicture);

            switch (gender) {
                case GENDER_MALE:
                    mGenderSpinner.setSelection(1);
                    break;
                case GENDER_FEMALE:
                    mGenderSpinner.setSelection(2);
                    break;
                default:
                    mGenderSpinner.setSelection(0);
                    break;
            }

        } else {
            getSupportActionBar().setTitle("Add a Acount");
        }
    }


    private void setupSpinner() {
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.array_gender_options, android.R.layout.simple_spinner_item);
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mGenderSpinner.setAdapter(genderSpinnerAdapter);

        mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.gender_male))) {
                        mGender = GENDER_MALE;
                    } else if (selection.equals(getString(R.string.gender_female))) {
                        mGender = GENDER_FEMALE;
                    } else {
                        mGender = GENDER_UNKNOWN;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGender = 0;
            }
        });
    }


    public void goCamera(View view) {
        PickImageDialog.build(new PickSetup())
                .setOnPickResult(new IPickResult() {
                    @Override
                    public void onPickResult(PickResult r) {
                        //TODO: do what you have to...
                        imgS.setImageBitmap(r.getBitmap());

                        encodedImage = ideaType.getConvertToString(r.getBitmap());

                    }
                })
                .setOnPickCancel(new IPickCancel() {
                    @Override
                    public void onCancelClick() {
                        //TODO: do what you have to if user clicked cancel
                        Toast.makeText(RegisterActivity.this, "cancle", Toast.LENGTH_LONG).show();

                    }
                }).show(getSupportFragmentManager());


    }


}


