package com.demo.spry.viewpagerwithanimation;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class SuccessfulActivity extends FragmentActivity implements View.OnClickListener {
    Button btn1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful);
        btn1 = (Button) findViewById(R.id.login);
        btn1.setOnClickListener(this);

    }
    @Override
    public void onClick (View v){
        Toast.makeText(getApplicationContext(),
                "Go to view your car",
                Toast.LENGTH_LONG).show();


            }




}