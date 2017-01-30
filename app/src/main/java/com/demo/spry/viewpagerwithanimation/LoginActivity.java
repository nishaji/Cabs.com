package com.demo.spry.viewpagerwithanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;
import android.widget.AdapterView.OnItemSelectedListener;

import org.json.JSONObject;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    String usernametxt, passwordtxt, usr,email;
    EditText username, password;
    Button loginbtn, signupbtn;
    public static final String PREFS_NAME = "MyPrefsFile";
    public static final String PREFS = "email";
    public static final String PREFS_NAME1 = "MyPrefsFile1";
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private TextView btnLogin;
    private ProgressDialog progressDialog;
    User user;
    private ContactsContract.CommonDataKinds.Note note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        if (PrefUtils.getCurrentUser(LoginActivity.this) != null) {
            Intent homeIntent = new Intent(LoginActivity.this, Welcome1.class);
            startActivity(homeIntent);
            finish();
        }
        loginbtn = (Button) findViewById(R.id.login);
        signupbtn = (Button) findViewById(R.id.signup);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                       isInternetOn();

            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("public_profile", "email", "user_friends", "user_birthday");
        btnLogin = (TextView) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setMessage("Loading...");
                progressDialog.show();
                loginButton.performClick();
                loginButton.setPressed(true);
                loginButton.invalidate();
                loginButton.registerCallback(callbackManager, mCallBack);
                loginButton.setPressed(false);
                loginButton.invalidate();


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
                    AccessToken.getCurrentAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject object,GraphResponse response) {

                            Log.e("response: ", response + "");
                            if (response != null) {
                                try {
                                    user = new User();
                                    user.facebookID = object.getString("id").toString();
                                    user.email = object.getString("email").toString();
                                    user.name = object.getString("name").toString();
                                    user.gender = object.getString("gender").toString();
                                    user.birthday = object.getString("birthday").toString();
                                    PrefUtils.setCurrentUser(user, LoginActivity.this);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Toast.makeText(LoginActivity.this, "welcome " + user.name, Toast.LENGTH_LONG).show();
                                saveNote();
                                Intent intent = new Intent(LoginActivity.this, Welcome2.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();

                            }
                        }

                    });

            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,email,gender, birthday");
            request.setParameters(parameters);
            request.executeAsync();
        }

        @Override
        public void onCancel() {
            progressDialog.dismiss();
        }

        @Override
        public void onError(FacebookException e) {
            progressDialog.dismiss();
        }
    };
    private void saveNote() {

        if (!user.name.isEmpty()) if (note == null) {
            ParseObject post = new ParseObject("profiledetail");
            post.put("id",user.facebookID);
            post.put("email",user.email);
            post.put("name",user.name);
            post.put("gender",user.gender);
            post.put("birthday",user.birthday);
            post.saveInBackground(new SaveCallback() {
                public void done(com.parse.ParseException e) {
                    if (e == null) {
                        // Saved successfully.
                        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                    } else {
                        // The save failed.
                        Toast.makeText(getApplicationContext(), "Failed to Save", Toast.LENGTH_SHORT).show();
                        Log.d(getClass().getSimpleName(), "User update error: " + e);
                    }
                }
            });
        }

    }


    @Override
    public void onBackPressed() {
        super.finish();
    }

    public final boolean isInternetOn() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =(ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {

            // if connected with internet

            Toast.makeText(this, " Connected to internet ", Toast.LENGTH_LONG).show();
            usernametxt = username.getText().toString();
            passwordtxt = password.getText().toString();
            if( username.getText().toString().length() == 0 )
                username.setError( "user name is required!" );
            else if(username.getText().toString().contains(" "))
                username.setError("No Spaces Allowed");
            if( password.getText().toString().length() == 0 )
                password.setError( "Passwor is required!" );
            // Send data to Parse.com for verification
            ParseUser.logInInBackground(usernametxt, passwordtxt, new LogInCallback() {
                public void done(ParseUser user, ParseException e) {

                    if (usernametxt.equals("") && passwordtxt.equals("")) {
                        Toast.makeText(getApplicationContext(),
                                "User Name and password can't be empty",
                                Toast.LENGTH_LONG).show();
                    } else {
                        if(user !=null){
                            email=user.getEmail();
                            System.out.println(email+"emailfromloginactivity");
                            usr = user.get("User").toString();
                            if (user != null && usr.equals("Booker")) {
                                Intent intent = new Intent(LoginActivity.this, Welcome2.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                SharedPreferences emailid = getSharedPreferences(LoginActivity.PREFS, 0); // 0 - for private mode
                                SharedPreferences.Editor edit = emailid.edit();
                                edit.putString("email", email);
                                edit.commit();
                                SharedPreferences settings = getSharedPreferences(LoginActivity.PREFS_NAME1, 0); // 0 - for private mode
                                SharedPreferences.Editor editor = settings.edit();
                                editor.putBoolean("hasLoggedIn1", true);
                                editor.commit();
                                Toast.makeText(getApplicationContext(), "Successfully Logged in", Toast.LENGTH_LONG).show();
                                finish();
                            } else if (user != null && usr.equals("Provider")) {
                                Intent intent = new Intent(LoginActivity.this, Welcome1.class);
                                intent.putExtra("email",email);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                SharedPreferences emailid = getSharedPreferences(LoginActivity.PREFS, 0); // 0 - for private mode
                                SharedPreferences.Editor edit = emailid.edit();
                                edit.putString("email", email);
                                edit.commit();
                                SharedPreferences settings = getSharedPreferences(LoginActivity.PREFS_NAME, 0); // 0 - for private mode
                                SharedPreferences.Editor editor = settings.edit();
                                editor.putBoolean("hasLoggedIn", true);
                                editor.commit();
                                Toast.makeText(getApplicationContext(),
                                        "Successfully Logged in",
                                        Toast.LENGTH_LONG).show();
                                finish();

                            }
                    }
                        else {
                            System.out.println("nosuchuserexistsssssss");
                            Toast.makeText(getApplicationContext(), "No such user exist, please signup", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {

            Toast.makeText(this, " Not Internet connection , please connect to the internet ", Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }


}








