package com.example.projectfinal.WebService;


import com.example.projectfinal.utilities.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public final class RestClient
{

        public static AppApi getService() {


            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(Level.BODY);


            OkHttpClient okHttpClient =new OkHttpClient().newBuilder()
                    .readTimeout(40, TimeUnit.SECONDS)
                    .connectTimeout(40, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(false)
                    .addInterceptor(httpLoggingInterceptor)
                    .build()
                    ;





            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_HOST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
            return retrofit.create(AppApi.class);

        }



}
