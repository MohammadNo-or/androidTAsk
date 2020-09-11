package com.example.projectfinal.models.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.projectfinal.R;

import java.io.ByteArrayOutputStream;

public  class ideaType {

    private static String convertToString;


    public static String getConvertToString(Bitmap bitmap) {

        // convert bitmap to byte
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 50, stream);
        byte imageInByte[] = stream.toByteArray();
        String encodedString = Base64.encodeToString(imageInByte, 0);

        return encodedString;
    }

    public static void setConvertToString(String convertToString) {
        ideaType.convertToString = convertToString;
    }

    public static void getAnime_EditText(TextView v, Boolean isError, final Context context) {

        v.setError(context.getResources().getText(R.string.text_requierd));
        isError = true;
        Animation myAnim = AnimationUtils.loadAnimation(context, R.anim.shake);
        v.startAnimation(myAnim);


    }





}
