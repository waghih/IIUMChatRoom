package com.waghih.iiumchatroom;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class JoinActivity extends Activity {

    private Button btnJoin;
//    private EditText txtName;
    private EditText pass;
    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;

    //Facebook login button
    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            Profile profile = Profile.getCurrentProfile();
            nextActivity(profile);
        }
        @Override
        public void onCancel() {        }
        @Override
        public void onError(FacebookException e) {      }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);


        callbackManager = CallbackManager.Factory.create();
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {
            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                nextActivity(newProfile);
            }
        };
        accessTokenTracker.startTracking();
        profileTracker.startTracking();

//        LoginButton loginButton = (LoginButton)findViewById(R.id.login_button);
        callback = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken accessToken = loginResult.getAccessToken();
                Profile profile = Profile.getCurrentProfile();
                nextActivity(profile);
                Toast.makeText(getApplicationContext(), "Logging in...", Toast.LENGTH_SHORT).show();    }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException e) {
            }
        };
        pass = (EditText)findViewById(R.id.password);
        btnJoin = (Button)findViewById(R.id.btnJoin);
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pass.getText().toString().trim().length() > 0 && pass.getText().toString().equals("2016")) {
                    LoginManager.getInstance().logInWithReadPermissions(JoinActivity.this, Arrays.asList("public_profile","user_friends"));
                    LoginManager.getInstance().registerCallback(callbackManager,callback);
//                    LoginManager.getInstance().setReadPermissions("user_friends");
//                    btnJoin.registerCallback(callbackManager, callback);
                }else if(pass.getText().toString().trim().length() > 0 && !pass.getText().toString().equals("2016")){
                    Toast.makeText(getApplicationContext(),
                            "Invalid Pin Code", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),
                            "Enter Pin Code", Toast.LENGTH_LONG).show();
                }
            }
        });
//        loginButton.setReadPermissions("user_friends");
//        loginButton.registerCallback(callbackManager, callback);


//        btnJoin = (Button) findViewById(R.id.btnJoin);
//        txtName = (EditText) findViewById(R.id.name);
//        pass = (EditText) findViewById(R.id.password);

        // Hiding the action bar
        getActionBar().hide();

//        btnJoin.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                if (txtName.getText().toString().trim().length() > 0 && pass.getText().toString().equals("2016")) {
//
//                    String name = txtName.getText().toString().trim();
//
//                    Intent intent = new Intent(JoinActivity.this,
//                            MainActivity.class);
//                    intent.putExtra("name", name);
//
//                    startActivity(intent);
//
//                }
//                else if (txtName.getText().toString().trim().length() > 0 && !pass.getText().toString().equals("2016")){
//                    Toast.makeText(getApplicationContext(),
//                            "Invalid Pin Code", Toast.LENGTH_LONG).show();
//                }
//                else {
//                    Toast.makeText(getApplicationContext(),
//                            "Please enter your name", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        //Facebook login
        Profile profile = Profile.getCurrentProfile();
        nextActivity(profile);
    }

    @Override
    protected void onPause() {

        super.onPause();
    }

    protected void onStop() {
        super.onStop();
        //Facebook login
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
        super.onActivityResult(requestCode, responseCode, intent);
        //Facebook login
        callbackManager.onActivityResult(requestCode, responseCode, intent);

    }

    private void nextActivity(Profile profile){
        if(profile != null){
            Intent main = new Intent(JoinActivity.this, MainActivity.class);
            main.putExtra("name", profile.getFirstName());
            main.putExtra("surname", profile.getLastName());
//            main.putExtra("imageUrl", profile.getProfilePictureUri(200,200).toString());
            startActivity(main);
        }
    }
}
