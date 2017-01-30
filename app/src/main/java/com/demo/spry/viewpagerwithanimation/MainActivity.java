package com.demo.spry.viewpagerwithanimation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity{

    public static final String MyPrefs = "MyPrefs";
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sp = getSharedPreferences(MyPrefs, Context.MODE_PRIVATE);
        SharedPreferences settings = getSharedPreferences(LoginActivity.PREFS_NAME, 0);
        SharedPreferences settings1 = getSharedPreferences(LoginActivity.PREFS_NAME1, 0);
        SharedPreferences logout = getSharedPreferences(LoginActivity.PREFS_NAME, 0);
        SharedPreferences logout1 = getSharedPreferences(LoginActivity.PREFS_NAME1, 0);
        boolean hasLoggedIn = settings.getBoolean("hasLoggedIn", false);
        boolean hasLoggedIn1 = settings1.getBoolean("hasLoggedIn1", false);
        boolean hasLoggedOut = logout.getBoolean("hasLoggedIn", true);
        boolean hasLoggedOut1 = logout1.getBoolean("hasLoggedIn1", true);
        if (!sp.getBoolean("first", false)) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("first", true);
            editor.commit();
            Intent intent = new Intent(this, Firstlaunchtutorial.class);
            startActivity(intent);
        }
        else if(hasLoggedIn)
        {
            Intent intent = new Intent(MainActivity.this, Welcome1.class);
            startActivity(intent);
        }
        else if(hasLoggedIn1)
        {
            Intent intent = new Intent(MainActivity.this, Welcome2.class);
            startActivity(intent);
        }
        else if(hasLoggedOut)
        {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        else if(hasLoggedOut1) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }    else{
            Thread timerThread = new Thread() {
                public void run() {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        Intent intent = new Intent(MainActivity.this, LoginSignupActivity.class);
                        startActivity(intent);
                    }
                }
            };
            timerThread.start();
        }
        }


    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
    @Override
    public void onBackPressed() {
        finish();
    }


}