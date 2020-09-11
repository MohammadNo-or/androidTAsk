package com.example.projectfinal.databaseroom;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofit {
    private static final String BASE_URL="https://uniqueandrocode.000webhostapp.com/hiren/androidtutorial/mycart/";
    private static MyRetrofit myRetrofit;
    private Retrofit retrofit;

    private MyRetrofit(){

        retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }
    public static synchronized MyRetrofit getInstance(){
        if (myRetrofit==null){
            myRetrofit=new MyRetrofit();
        }
        return myRetrofit;
    }


}

