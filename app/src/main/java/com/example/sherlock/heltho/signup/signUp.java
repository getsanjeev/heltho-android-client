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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sherlock.heltho.R;
import com.example.sherlock.heltho.dashboard.userDashboard;
import com.example.sherlock.heltho.service.retrofit_service;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by sanjeev on 1/4/17.
 */

public class signUp extends AppCompatActivity {

    private EditText email_mob_et;
    private EditText password_et;
    private EditText cnfm_password_et;
    private String contactNumberEmail;
    private String passwordUser;
    private String cnfmPasswordUser;
    private Button register_btn;
    private ProgressDialog dialog;
    private ImageView image_adzippy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        email_mob_et = (EditText) findViewById(R.id.email_edit);
        password_et = (EditText) findViewById(R.id.pass_edit);
        cnfm_password_et = (EditText) findViewById(R.id.cnfmpass_edit);
        register_btn = (Button) findViewById(R.id.sign_up);
        image_adzippy  = (ImageView)findViewById(R.id.image_adzippy);
        dialog = new ProgressDialog(signUp.this);
        dialog.setMessage("Loading...");
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                registerUser();
                dialog.dismiss();
                startActivity(new Intent(signUp.this,userDashboard.class));
            }
        });
    }



    public void registerUser() {
        contactNumberEmail = "";
        passwordUser = "";
        cnfmPasswordUser = "";

        if (email_mob_et.getText().toString().length() < 10) {
            Toast.makeText(this, "Invalid Number", Toast.LENGTH_SHORT).show();
            return;
        } else contactNumberEmail = email_mob_et.getText().toString();
        if (password_et.getText().toString().length() < 5) {
            Toast.makeText(this, "Password Too Short", Toast.LENGTH_SHORT).show();
            return;
        } else passwordUser = email_mob_et.getText().toString();
        if (!cnfm_password_et.getText().toString().equals(email_mob_et.getText().toString())) {
            Toast.makeText(this, "Password do not match", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!contactNumberEmail.equals("") && !passwordUser.equals("") && !cnfmPasswordUser.equals(""))
            sendCredentials(contactNumberEmail, passwordUser);
    }

    private void sendCredentials(String contactEmail, final String password) {
        final String usedID;
        int credentialTypeIsEmail = checkEmailOrMobile(contactEmail);
        if (credentialTypeIsEmail == 3) {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
        }
        usedID = contactEmail;
            Call<Response> callback =  new retrofit_service().get_service().register_user("register.php",usedID,password);
            callback.enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    dialog.dismiss();
                    Toast.makeText(signUp.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    SharedPreferences mSharedPreferences = getSharedPreferences("mySharedPreferences",MODE_PRIVATE);
                    SharedPreferences.Editor editor = mSharedPreferences.edit();
                    editor.putString("userID",usedID);
                    editor.putString("userPassword",password);
                    editor.apply();
                    startActivity(new Intent(signUp.this,otpFragment.class));
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                    Toast.makeText(signUp.this, "Error while Registration, Check Internet Settings and try Again", Toast.LENGTH_SHORT).show();
                }
            });
        }

    private int checkEmailOrMobile(String mobileOrEmail) {
        boolean phone = android.util.Patterns.PHONE.matcher(mobileOrEmail.trim()).matches();
        boolean email = android.util.Patterns.PHONE.matcher(mobileOrEmail.trim()).matches();
        if (phone && email || !phone && !email){
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
            return 3;
        }

        else {
            if (email) {
                return 0;
            } else return 1;
        }
    }
}