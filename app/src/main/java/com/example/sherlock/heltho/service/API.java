package com.example.sherlock.heltho.service;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface API {
    @POST ("{url}/")
    Call<Response>register_user(@Path("url") String url, String user_ID, String password);

    @POST("{url}/")
    Call<Response>verify_OTP(@Path("url") String url, String OTP);

    @POST("{url}/")
    Call<Response>request_OTP(@Path("url") String url, String user_ID);

}
