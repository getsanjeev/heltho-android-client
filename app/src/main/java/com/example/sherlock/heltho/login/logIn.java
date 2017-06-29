package com.example.sherlock.heltho.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sanjeev.adzippy.data.prefUtils;
import com.example.sanjeev.adzippy.data.userData;
import com.example.sanjeev.adzippy.drawer.userDashBoard;
import com.example.sanjeev.adzippy.sign_up.signUp;
import com.example.sherlock.heltho.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import retrofit2.http.Url;

/**
 * Created by sanjeev on 2/4/17.
 */

public class logIn extends AppCompatActivity {
    private LoginButton fbloginButton;
    private CallbackManager callbackManager;
    private TextView signUp_btn;
    private Button zipLogIn_btn;
    ProgressDialog progressDialog;
    userData myUserData;
    private EditText userID_et;
    private EditText userPassword_et;


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.login);
        callbackManager = CallbackManager.Factory.create();
        fbloginButton = (LoginButton) findViewById(R.id.fb_login_button);
        if (prefUtils.getCurrentUser(logIn.this) != null) {
            Intent homeIntent = new Intent(logIn.this, userDashBoard.class);
            startActivity(homeIntent);
            finish();
        }
        userID_et = (EditText) findViewById(R.id.email_edit);
        userPassword_et = (EditText) findViewById(R.id.pass_edit);
        callbackManager = CallbackManager.Factory.create();
        fbloginButton = (LoginButton) findViewById(R.id.fb_login_button);
        zipLogIn_btn = (Button) findViewById(R.id.login);
        fbloginButton.setReadPermissions("public_profile", "email","user_birthday","user_relationships","user_photos");
        signUp_btn = (TextView) findViewById(R.id.sign_up_in_login_layout);

        signUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(logIn.this, signUp.class));
            }
        });

        zipLogIn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences mSharedPreferences = getSharedPreferences("mySharedPreferences", MODE_PRIVATE);
                if (userID_et.getText().toString().equals(mSharedPreferences.getString("userID", "qwerty"))
                        && userPassword_et.getText().toString().equals(mSharedPreferences.getString("userPassword", "asdf"))) {
                    startActivity(new Intent(logIn.this, userDashBoard.class));
                }
            }
        });

        fbloginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(logIn.this);
                progressDialog.setMessage("Loading...");
                progressDialog.show();
                fbloginButton.registerCallback(callbackManager, mCallBack);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private FacebookCallback<LoginResult> mCallBack = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            progressDialog.dismiss();
            GraphRequest request = GraphRequest.newMeRequest(
                    loginResult.getAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(
                                JSONObject object,
                                GraphResponse response) {
                            //Date my_date = AccessToken.getCurrentAccessToken().getExpires();
                            response.toString();
                            try {
                                String id = object.getString("id");
                                String first_name = object.getString("first_name");
                                String last_name = object.getString("last_name");
                                String email = object.getString("email");
                                String dob = object.getString("birthday");
                                userData.first_name = first_name;
                                userData.last_name = last_name;
                                userData.facebookID = id;
                                userData.email = email;
                                userData.dob = dob;
                                String profilePicUrl = "https://graph.facebook.com/"+id+"/picture?width=200&height=200";
                                userData.profile_pic_url = profilePicUrl;

                            } catch (Exception e) {
                                e.getClass().getSimpleName();
                                e.printStackTrace();
                                Log.e("Getting","Exception");
                            }
                            Toast.makeText(logIn.this, "welcome " + myUserData.first_name, Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(logIn.this, userDashBoard.class);
                            startActivity(intent);
                            finish();
                        }

                    });

            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,email,birthday," +
                    "relationship_status,first_name,last_name");
            request.setParameters(parameters);
            request.executeAsync();
        }

        @Override
        public void onCancel() {
            progressDialog.dismiss();
            Log.e("HEY","cancelled");
        }

        @Override
        public void onError(FacebookException error) {
            progressDialog.dismiss();
            Log.e("hello","nologin");
        }
    };
}