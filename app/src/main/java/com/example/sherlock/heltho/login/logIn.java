package com.example.sherlock.heltho.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.example.sherlock.heltho.data.prefUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.sherlock.heltho.R;
import com.example.sherlock.heltho.dashboard.userDashboard;
import com.example.sherlock.heltho.data.userData;
import com.example.sherlock.heltho.signup.signUp;
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

/**
 * Created by sanjeev on 2/4/17.
 */

public class logIn extends AppCompatActivity {
    private LoginButton fbloginButton;
    private CallbackManager callbackManager;
    private TextView signUp_btn;
    private Button zipLogIn_btn;
    ProgressDialog progressDialog;
    private EditText userID_et;
    private EditText userPassword_et;

    String id;
    String first_name;
    String last_name;
    String email;
    String dob;
    String profilePicUrl;


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
            Intent homeIntent = new Intent(logIn.this, userDashboard.class);
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
                finish();
            }
        });

        zipLogIn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLoggedIn();
                startActivity(new Intent(logIn.this, userDashboard.class));
                finish();
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

    private void setLoggedIn(){
        SharedPreferences mSharedPreferences = getSharedPreferences("mySharedPreferences",MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean("loggedIn",true);
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
                                id = object.getString("id");
                                first_name = object.getString("first_name");
                                last_name = object.getString("last_name");
                                email = object.getString("email");
                                dob = object.getString("birthday");
                                profilePicUrl = "https://graph.facebook.com/"+id+"/picture?width=200&height=200";
                                SharedPreferences mmSharedPreferences = getSharedPreferences("mySharedPreferences",MODE_PRIVATE);
                                SharedPreferences.Editor editor = mmSharedPreferences.edit();
                                editor.putString("user_ID",id);
                                editor.putString("user_first_name",first_name);
                                editor.putString("user_last_name",last_name);
                                editor.putString("user_email",email);
                                editor.putString("user_DOB",dob);
                                editor.putString("user_profile_picture",profilePicUrl);
                                editor.apply();

                            } catch (Exception e) {
                                e.getClass().getSimpleName();
                                e.printStackTrace();
                                Log.e("Getting","Exception");
                            }
                            Toast.makeText(logIn.this, "Welcome " + first_name, Toast.LENGTH_LONG).show();
                            setLoggedIn();
                            Intent intent = new Intent(logIn.this, userDashboard.class);
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
        }

        @Override
        public void onError(FacebookException error) {
            progressDialog.dismiss();
        }
    };
}