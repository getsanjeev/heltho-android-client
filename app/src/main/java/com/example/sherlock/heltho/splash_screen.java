package com.example.sherlock.heltho;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.sherlock.heltho.dashboard.userDashboard;
import com.example.sherlock.heltho.login.logIn;


public class splash_screen extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Thread mThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                } catch (Exception e) {
                    e.getStackTrace();
                } finally {
                    SharedPreferences mSharedPreferences = getSharedPreferences("mySharedPreferences",MODE_PRIVATE);
                    if (mSharedPreferences.contains("registered")) {
                        if (mSharedPreferences.getBoolean("loggedIn", true) == true)
                            startActivity(new Intent(splash_screen.this, userDashboard.class));
                        else startActivity(new Intent(splash_screen.this,logIn.class));
                    } else startActivity(new Intent(splash_screen.this, logIn.class));
                    finish();
                }
            }
        };
        mThread.start();
    }
}

