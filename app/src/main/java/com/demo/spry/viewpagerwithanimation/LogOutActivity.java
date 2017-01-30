package com.demo.spry.viewpagerwithanimation;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class LogOutActivity extends Activity {
    private ContactsContract.CommonDataKinds.Note note;
    private TextView btnLogout;
    private User user;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        final ParseUser currentUser = ParseUser.getCurrentUser();
        user=PrefUtils.getCurrentUser(LogOutActivity.this);
        btnLogout = (TextView) findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrefUtils.clearCurrentUser(LogOutActivity.this);
                LoginManager.getInstance().logOut();
                Intent i= new Intent(LogOutActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}




