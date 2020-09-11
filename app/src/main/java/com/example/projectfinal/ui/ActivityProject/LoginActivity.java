package com.example.projectfinal.ui.ActivityProject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ProgressDialog;

import com.example.projectfinal.R;
import com.example.projectfinal.models.models.MainActivityViewModel;
import com.example.projectfinal.models.models.ideaType;
import com.google.android.material.navigation.NavigationView;

import com.example.projectfinal.changLanguage.LocaleHelper;

public class LoginActivity extends AppCompatActivity {
    private TextView AdminLink, NotAdminLink;
    private String parentDbName = "users";

    Button LoginButton;
    ImageView imgLogin;
    ImageButton imgSecure;
    private ProgressDialog loadingBar;
    TextView etL, passL;
    CheckBox ch;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        imgSecure=findViewById(R.id.imgSecure);
        LoginButton = findViewById(R.id.login_btn);
        AdminLink = findViewById(R.id.admin_panel_link);
        NotAdminLink = findViewById(R.id.not_admin_panel_link);
        etL = findViewById(R.id.etL);
        passL = findViewById(R.id.passL);
        ch = findViewById(R.id.CheckR);
        imgLogin = findViewById(R.id.imageLogin);



        imgSecure .setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        passL.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;
                    case MotionEvent.ACTION_DOWN:
                        passL.setInputType(InputType.TYPE_CLASS_TEXT);

                        break;
                }
                return true;
            }
        });





        Animation a = AnimationUtils.loadAnimation(this, R.anim.move);
        imgLogin.startAnimation(a);
        Animation b = AnimationUtils.loadAnimation(this, R.anim.flip);
        etL.startAnimation(b);
        Animation c = AnimationUtils.loadAnimation(this, R.anim.flip);
        passL.startAnimation(c);

        if (passL.getInputType() == 1) {
            passL.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        } else {
            passL.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
        }


        loadingBar = new ProgressDialog(this);
        SharedPreferences daftar = PreferenceManager.getDefaultSharedPreferences(this);
        etL.setText(daftar.getString("name", ""));
        passL.setText(daftar.getString("password", ""));
        ch.setChecked(daftar.getBoolean("Remember", false));


        AdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginButton.setText("Login Admin");
                AdminLink.setVisibility(View.INVISIBLE);
                NotAdminLink.setVisibility(View.VISIBLE);
                parentDbName = "Admins";


            }
        });

        NotAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginButton.setText("Login");
                AdminLink.setVisibility(View.VISIBLE);
                NotAdminLink.setVisibility(View.INVISIBLE);
                parentDbName = "users";
            }
        });

    }
//_______________________________________________________________________________________________

    public void GoToMain(View view) {
//___________________________________________________________________________
        etL.setError(null);
        passL.setError(null);
        boolean isError = false;
        String passwordTest = passL.getText().toString();
        String UserTest = etL.getText().toString();

        if (UserTest == null) {
            ideaType.getAnime_EditText(etL, isError, this);
        }


        if (passwordTest == null || passwordTest.length() < 5) {
            ideaType.getAnime_EditText(passL, isError, this);
        }

        if (isError) {
            return;
        }


//________________________________________________________________________________________________________________


        LoginUser();


        //________________________________________________________________________________________________________________
        SharedPreferences daftar = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor pen = daftar.edit();
        pen.putBoolean("Remember", ch.isChecked());
        if (ch.isChecked() == true) {
            pen.putString("name", etL.getText().toString());
            pen.putString("password", passL.getText().toString());
            pen.putString("true", "welcom to " + etL.getText().toString());

        } else {
            pen.clear();
        }
        pen.apply();

    }


    //_______________________________________________________________________________________________

    private void LoginUser() {
        final String name = etL.getText().toString();
        String password = passL.getText().toString();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, getResources().getText(R.string.toat_anim_text), Toast.LENGTH_SHORT).show();
        } else if (password.length() < 5) {
            Toast.makeText(this, "the password must be lingth largesr than 5 ", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "the password must not embty ", Toast.LENGTH_SHORT).show();

        } else {
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage(getResources().getText(R.string.loading_add_descr));
            loadingBar.setCanceledOnTouchOutside(true);
            loadingBar.show();

            MainActivityViewModel vm = ViewModelProviders.of(this).get(MainActivityViewModel.class);
            vm.loginUser(etL.getText().toString(), passL.getText().toString()).observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {

                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    String str = etL.getText().toString();
                    i.putExtra("nameDr", str);
                    startActivity(i);
                    finish();
                }
            });


        }
    }


    //_______________________________________________________________________________________________

    public void GoToSignUp(View view) {

        Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(i);

    }


//_______________________________________________________________________________________________


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }


}
