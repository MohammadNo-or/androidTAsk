package com.example.projectfinal.learnActiv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectfinal.R;

import java.io.FileNotFoundException;
import java.io.InputStream;




public class Main3Activity extends AppCompatActivity {

    EditText etName,etPassword;
    ImageView img;
    EditText etFullName;

    TextView tvBirthDate;

    final int CAMERA_REQUEST = 1;
    final int PICK_FROM_GALLERY = 2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        setManyPermissions();

        img=findViewById(R.id.imgcard);
        etName=findViewById(R.id.etName);
        etPassword=findViewById(R.id.etPassword);
        etFullName=findViewById(R.id.etFullNAme);
        tvBirthDate=findViewById(R.id.tvBDate);

        //put initial value doe date
        final java.util.Calendar c= java.util.Calendar.getInstance();
        int year= c.get(java.util.Calendar.YEAR);
        int month= c.get(java.util.Calendar.MONTH);
        int day= c.get(java.util.Calendar.DAY_OF_MONTH);

        tvBirthDate.setText(new StringBuilder().append(month+1).append("/").append(day).append("/").append(year));
        tvBirthDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                showDialog(999);
                return false;
            }
        });









    }





















    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            int year = calendar.get(java.util.Calendar.YEAR);

            int month = calendar.get(java.util.Calendar.MONTH)+1;
            int day = calendar.get(java.util.Calendar.DAY_OF_MONTH);


            return new DatePickerDialog(this, myDateListener,year,month-1,day);
        }


        return null;
    }





    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // arg1 = year
            // arg2 = month
            // arg3 = day
            tvBirthDate.setText(arg2+1+"/" + arg3+"/"+arg1);
            tvBirthDate.requestFocus();
        }
    };
    private void setManyPermissions()
    {


        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};

        if(!hasPermissions(Main3Activity.this, PERMISSIONS)){
            ActivityCompat.requestPermissions(Main3Activity.this, PERMISSIONS, PERMISSION_ALL);



        }

    }


    public static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }















    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case CAMERA_REQUEST:
                Bundle extras = data.getExtras();
                if (extras != null) {
                    Bitmap yourImage = extras.getParcelable("data");
                    img.setImageBitmap(yourImage);



                }
                break;
            case PICK_FROM_GALLERY:

                final Uri imageUri = data.getData();
                InputStream imageStream = null;
                try {
                    imageStream = this.getContentResolver().openInputStream(imageUri);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }


                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                img.setImageBitmap(selectedImage);


                break;
        }

    }


    public void showDatePicker(View view) {
    }
}







