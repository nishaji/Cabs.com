package com.demo.spry.viewpagerwithanimation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseUser;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
/**
 * Created by sprydev5 on 30/12/15.
 */
public class SignOut extends Activity {
    Button logout;
    private ContactsContract.CommonDataKinds.Note note;
    public static final String SIGNOUT = "SignOut";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_out);
        ParseUser currentUser = ParseUser.getCurrentUser();
        String struser = currentUser.getUsername().toString();
        TextView txtuser = (TextView)findViewById(R.id.txtuser);
        txtuser.setText("You are logged in as " + struser);
        logout = (Button)findViewById(R.id.logout);
        logout.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                ParseUser.logOut();
                Intent i = new Intent(SignOut.this, LoginSignupActivity.class);
                startActivity(i);
                SharedPreferences settings = getSharedPreferences(LoginActivity.PREFS_NAME, 0); // 0 - for private mode
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("hasLoggedIn", false);
                editor.commit();
               // finish();

            }
        });

    }

    }
