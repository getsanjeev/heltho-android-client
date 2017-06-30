package com.example.sherlock.heltho.signup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.sherlock.heltho.R;
import com.example.sherlock.heltho.dashboard.userDashboard;
import com.example.sherlock.heltho.service.retrofit_service;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;


public class otpFragment extends AppCompatActivity {
    private Button submit_btn;
    private EditText OTP_et;
    private ProgressDialog dialog;
    private Button requestOTP_btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_fragment);
        OTP_et  = (EditText) findViewById(R.id.cnfm_otp);
        submit_btn = (Button) findViewById(R.id.submit);
        requestOTP_btn = (Button)findViewById(R.id.request_otp);
        SharedPreferences mSharedPreferences = getSharedPreferences("mySharedPreferences",MODE_PRIVATE);
        final String userID = mSharedPreferences.getString("userID","sanjeev");
        requestOTP_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrofit_service requestOTPService = new retrofit_service();
                Call<Response> callback =  new retrofit_service().get_service().request_OTP("/.php",userID);
                callback.enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        Toast.makeText(otpFragment.this, "Enter OTP to get verified", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {

                    }
                });
            }
        });
        dialog = new ProgressDialog(otpFragment.this);
        dialog.setMessage("Verifying OTP...");
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                sendOTP(OTP_et.getText().toString());
            }
        });
    }


    void sendOTP(String OTP){
        Call<Response> callback =  new retrofit_service().get_service().verify_OTP("url",OTP);
        callback.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Toast.makeText(otpFragment.this, "Please submit OTP", Toast.LENGTH_SHORT).show();
                if(response.message().equals("successful")){
                    Toast.makeText(otpFragment.this, "Welcome to adZippy", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(otpFragment.this,userDashboard.class));
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(otpFragment.this, "Error Sending OTP", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
