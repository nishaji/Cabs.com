package com.demo.spry.viewpagerwithanimation;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.os.Bundle;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseUser;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.parse.ParseObject;
import android.widget.AdapterView.OnItemSelectedListener;

import org.json.JSONObject;

public class SignupActivity extends Activity {
    // Declare Variables

    Button submit;
    String nametxt, emailtxt, passwordtxt,radio1,radio2,phonetxt;
    TextView tvHint;
    EditText username, email, password,phone;
    Spinner s1;
    RadioButton r1,r2;

    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private TextView btnLogin;
    private ProgressDialog progressDialog;
    private ContactsContract.CommonDataKinds.Note note;
    User user;
    /**
     * Called when the activity is first created.
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_signup);
        if (PrefUtils.getCurrentUser(SignupActivity.this) != null) {
            Intent homeIntent = new Intent(SignupActivity.this, Welcome1.class);
            startActivity(homeIntent);
            finish();
        }
        username = (EditText) findViewById(R.id.etUserName);
        email = (EditText) findViewById(R.id.etEmail);
        password = (EditText) findViewById(R.id.etPass);
        phone=(EditText)findViewById(R.id.etPhone);
        tvHint = (TextView) findViewById(R.id.text);
        r1=(RadioButton)findViewById(R.id.radio1);
        r2=(RadioButton)findViewById(R.id.radio2);
        submit = (Button) findViewById(R.id.btnsignup);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isInternetOn();
                if (r1.isChecked()) {
                    nametxt = username.getText().toString();
                    emailtxt = email.getText().toString();
                    passwordtxt = password.getText().toString();
                    phonetxt = phone.getText().toString();
                    radio1 = r1.getText().toString();
                    System.out.println(radio1);
                    // Force user to fill up the form
                    if (nametxt.equals("") && emailtxt.equals("") && passwordtxt.equals("") && phonetxt.equals("")) {
                        Toast.makeText(getApplicationContext(),
                                "Please complete the sign up form",
                                Toast.LENGTH_LONG).show();

                    } else {
                        // Save new user data into Parse.com Data Storage
                        ParseUser user = new ParseUser();
                        user.setUsername(nametxt);
                        user.setPassword(passwordtxt);
                        user.setEmail(emailtxt);
                        user.put("Phone", phonetxt);
                        user.put("User", radio1);
                        user.signUpInBackground(new SignUpCallback() {
                            public void done(ParseException e) {
                                if (e == null) {
                                    Intent intent = new Intent(SignupActivity.this, Welcome1.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    // Show a simple Toast message upon successful registration
                                    Toast.makeText(getApplicationContext(),
                                            "Successfully Signed up, please log in.",
                                            Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(),"Please  choose a valid user name Error", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                } else {
                    if (r2.isChecked()) {
                        nametxt = username.getText().toString();
                        emailtxt = email.getText().toString();
                        passwordtxt = password.getText().toString();
                        phonetxt = phone.getText().toString();
                        radio2 = r2.getText().toString();

                        // Force user to fill up the form
                        if (nametxt.equals("") && emailtxt.equals("") && passwordtxt.equals("") && phonetxt.equals("")) {
                            Toast.makeText(getApplicationContext(),
                                    "Please complete the sign up form",
                                    Toast.LENGTH_LONG).show();

                        }
                        else {
                            // Save new user data into Parse.com Data Storage
                            ParseUser user = new ParseUser();
                            user.setUsername(nametxt);
                            user.setPassword(passwordtxt);
                            user.setEmail(emailtxt);

                            user.put("Phone", phonetxt);
                            user.put("User", radio2);
                            user.signUpInBackground(new SignUpCallback() {
                                public void done(ParseException e) {
                                    if (e == null) {
                                        Intent intent = new Intent(SignupActivity.this, Welcome2.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                        // Show a simple Toast message upon successful registration
                                        Toast.makeText(SignupActivity.this, "welcome " + nametxt, Toast.LENGTH_LONG).show();

                                    } else {
                                        Toast.makeText(getApplicationContext(),
                                                "Email must be unique and email format", Toast.LENGTH_LONG)
                                                .show();
                                    }
                                }
                            });
                        }
                    }
                }
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
                progressDialog = new ProgressDialog(SignupActivity.this);
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
                                    System.out.println("hii" + user.facebookID);
                                    user.email = object.getString("email").toString();
                                    System.out.println("hii" + user.email);
                                    user.name = object.getString("name").toString();
                                    System.out.println("hii" + user.name);
                                    user.gender = object.getString("gender").toString();
                                    System.out.println("hii" + user.gender);
                                    user.birthday = object.getString("birthday").toString();
                                    System.out.println("hii" + user.birthday);


                                    PrefUtils.setCurrentUser(user, SignupActivity.this);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Toast.makeText(SignupActivity.this, "welcome " + user.name, Toast.LENGTH_LONG).show();
                                saveNote();
                                Intent intent = new Intent(SignupActivity.this, Welcome1.class);
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

    public final boolean isInternetOn() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {

            // if connected with internet

            Toast.makeText(this, " Connected to internet ", Toast.LENGTH_LONG).show();
            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {

            Toast.makeText(this, " Not Internet connection , please connect to the internet ", Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }



    @Override
    public void onBackPressed() {
        finish();
    }

}
