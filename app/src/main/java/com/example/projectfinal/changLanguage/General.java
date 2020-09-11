package com.example.projectfinal.changLanguage;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by admin on 2/10/2018.
 */

public class General {

    //same class
    //same method name
    //different parameters (Number,types)

    public static void addToSharedPreference(Context c, String key, int value) {
        SharedPreferences daftar = PreferenceManager.getDefaultSharedPreferences(c);
        SharedPreferences.Editor pen = daftar.edit();
        pen.putString(key, Integer.toString(value));

        pen.commit();
    }

    public static void addToSharedPreference(Context c, String key, boolean value) {
        SharedPreferences daftar = PreferenceManager.getDefaultSharedPreferences(c);
        SharedPreferences.Editor pen = daftar.edit();
        pen.putBoolean(key, value);

        pen.commit();
    }


    public static String readFromPreference(Context c, String key) {
        String value = "";
        SharedPreferences daftar = PreferenceManager.getDefaultSharedPreferences(c);
        value = daftar.getString(key, "");
        return value;


    }

    public static boolean readBooleanFromPreference(Context c, String key) {
        boolean value = false;
        SharedPreferences daftar = PreferenceManager.getDefaultSharedPreferences(c);
        value = daftar.getBoolean(key, false);
        return value;


    }

    public static boolean getBooleanPreferenceValue(Context c, String key) {
        boolean result;
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(c);

        result = sp.getBoolean(key, false);
        return result;
    }

    public static void overrideFontsHeavy(final Context context, final View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    overrideFontsHeavy(context, child);
                }
            } else if (v instanceof TextView) {
                ((TextView) v).setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/helvetica_lt_condensed_black.ttf"));
            }
        } catch (Exception e) {
        }
    }

    public static void overrideFonts(final Context context, final View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    overrideFonts(context, child);
                }
            } else if (v instanceof TextView) {
                ((TextView) v).setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/helvetica.ttf"));
            }
        } catch (Exception e) {
        }

    }
}
