package com.example.sherlock.heltho.dashboard;

/**
 * Created by sherlock on 10/7/17.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sherlock.heltho.R;
import com.example.sherlock.heltho.signup.otpFragment;


public class emailPage extends AppCompatActivity {

    EditText mobile;
    Button next;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_input);
        mobile = (EditText)findViewById(R.id.email_edit);
        next = (Button)findViewById(R.id.submit_mob_page);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mobile.getText().toString().length()!=10) {
                    Toast.makeText(emailPage.this, "Enter valid Mobile NUmber", Toast.LENGTH_SHORT).show();
                }
                else {
                    startActivity(new Intent(emailPage.this,otpFragment.class));
                    finish();
                }
            }
        });


    }
}