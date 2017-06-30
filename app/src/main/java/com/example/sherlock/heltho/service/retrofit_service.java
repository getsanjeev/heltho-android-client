package com.example.sherlock.heltho.service;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class retrofit_service {
    private String API_BASE_URL = "http://getsanjeev.esy.es/";
    private API client;

    public API get_service() {
        Retrofit.Builder builder =
                new Retrofit.Builder().baseUrl(API_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create());
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = builder.client(httpClient.build()).build();
        client = retrofit.create(API.class);
        return client;
    }


}
