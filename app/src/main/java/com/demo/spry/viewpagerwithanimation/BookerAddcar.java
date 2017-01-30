package com.demo.spry.viewpagerwithanimation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class BookerAddcar extends Activity {
    private EditText et1, et2, et3, et4, et5;
    String make, model, email, registration, color, id, carType;
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20, b21;
    CheckBox c1;
    TextView t1;
    Drawable back1, back2, back3, back4, back5, back6, back7, back8, back9, back10;
    private Databasehandler mHelper;
    private SQLiteDatabase dataBase;
    private ContactsContract.CommonDataKinds.Note note;
    private boolean isUpdate;
    private Databasehandler dbHelper;
    int personID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booker_addcar);
        SharedPreferences emailid = getSharedPreferences(LoginActivity.PREFS, 0);
        email = emailid.getString("email", "email");
        System.out.println(email + "emailonaddcaractivity");
        dbHelper = new Databasehandler(this);
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        et4 = (EditText) findViewById(R.id.et4);
        b1 = (Button) findViewById(R.id.btn1);
        b2 = (Button) findViewById(R.id.btn2);
        b3 = (Button) findViewById(R.id.btn3);
        b4 = (Button) findViewById(R.id.btn4);
        b5 = (Button) findViewById(R.id.btn5);
        b6 = (Button) findViewById(R.id.btn6);
        b7 = (Button) findViewById(R.id.btn7);
        b8 = (Button) findViewById(R.id.btn8);
        b9 = (Button) findViewById(R.id.btn9);
        b10 = (Button) findViewById(R.id.btn10);
        b11 = (Button) findViewById(R.id.btn11);
        b12 = (Button) findViewById(R.id.btn12);
        b13 = (Button) findViewById(R.id.btn13);
        b14 = (Button) findViewById(R.id.btn14);
        b15 = (Button) findViewById(R.id.btn15);
        b16 = (Button) findViewById(R.id.btn16);
        b17 = (Button) findViewById(R.id.btn17);
        b18 = (Button) findViewById(R.id.btn18);
        b19 = (Button) findViewById(R.id.btn19);
        b20 = (Button) findViewById(R.id.btn20);
        b21 = (Button) findViewById(R.id.btnsubmit);
        c1 = (CheckBox) findViewById(R.id.checkBox1);
        t1 = (TextView) findViewById(R.id.text1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_18dp);
        toolbar.setTitle("Add Car");
        toolbar.setTitleTextColor(0xffffffff);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Welcome2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        b1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                b1.setVisibility(View.GONE);
                b11.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);
                b3.setVisibility(View.VISIBLE);
                b4.setVisibility(View.VISIBLE);
                b5.setVisibility(View.VISIBLE);
                b6.setVisibility(View.VISIBLE);
                b7.setVisibility(View.VISIBLE);
                b8.setVisibility(View.VISIBLE);
                b9.setVisibility(View.VISIBLE);
                b10.setVisibility(View.VISIBLE);
                b12.setVisibility(View.GONE);
                b13.setVisibility(View.GONE);
                b14.setVisibility(View.GONE);
                b15.setVisibility(View.GONE);
                b16.setVisibility(View.GONE);
                b17.setVisibility(View.GONE);
                b18.setVisibility(View.GONE);
                b19.setVisibility(View.GONE);
                b20.setVisibility(View.GONE);
                t1.setText("Black");
            }
        });

        b2.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                b1.setVisibility(View.VISIBLE);
                b3.setVisibility(View.VISIBLE);
                b4.setVisibility(View.VISIBLE);
                b5.setVisibility(View.VISIBLE);
                b6.setVisibility(View.VISIBLE);
                b7.setVisibility(View.VISIBLE);
                b8.setVisibility(View.VISIBLE);
                b9.setVisibility(View.VISIBLE);
                b10.setVisibility(View.VISIBLE);
                b2.setVisibility(View.GONE);
                b12.setVisibility(View.VISIBLE);
                b11.setVisibility(View.GONE);
                b13.setVisibility(View.GONE);
                b14.setVisibility(View.GONE);
                b15.setVisibility(View.GONE);
                b16.setVisibility(View.GONE);
                b17.setVisibility(View.GONE);
                b18.setVisibility(View.GONE);
                b19.setVisibility(View.GONE);
                b20.setVisibility(View.GONE);
                t1.setText("Blue");
            }
        });

        b3.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                b3.setVisibility(View.GONE);
                b1.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);
                b4.setVisibility(View.VISIBLE);
                b5.setVisibility(View.VISIBLE);
                b6.setVisibility(View.VISIBLE);
                b7.setVisibility(View.VISIBLE);
                b8.setVisibility(View.VISIBLE);
                b9.setVisibility(View.VISIBLE);
                b10.setVisibility(View.VISIBLE);
                b13.setVisibility(View.VISIBLE);
                b11.setVisibility(View.GONE);
                b12.setVisibility(View.GONE);
                b14.setVisibility(View.GONE);
                b15.setVisibility(View.GONE);
                b16.setVisibility(View.GONE);
                b17.setVisibility(View.GONE);
                b18.setVisibility(View.GONE);
                b19.setVisibility(View.GONE);
                b20.setVisibility(View.GONE);
                t1.setText("Brown");
            }
        });

        b4.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                b4.setVisibility(View.GONE);
                b1.setVisibility(View.VISIBLE);
                b3.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);
                b5.setVisibility(View.VISIBLE);
                b6.setVisibility(View.VISIBLE);
                b7.setVisibility(View.VISIBLE);
                b8.setVisibility(View.VISIBLE);
                b9.setVisibility(View.VISIBLE);
                b10.setVisibility(View.VISIBLE);
                b14.setVisibility(View.VISIBLE);
                b11.setVisibility(View.GONE);
                b13.setVisibility(View.GONE);
                b12.setVisibility(View.GONE);
                b15.setVisibility(View.GONE);
                b16.setVisibility(View.GONE);
                b17.setVisibility(View.GONE);
                b18.setVisibility(View.GONE);
                b19.setVisibility(View.GONE);
                b20.setVisibility(View.GONE);
                t1.setText("Green");
            }
        });
        b5.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                t1.setText("Purple");
                b5.setVisibility(View.GONE);
                b1.setVisibility(View.VISIBLE);
                b3.setVisibility(View.VISIBLE);
                b4.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);
                b6.setVisibility(View.VISIBLE);
                b7.setVisibility(View.VISIBLE);
                b8.setVisibility(View.VISIBLE);
                b9.setVisibility(View.VISIBLE);
                b10.setVisibility(View.VISIBLE);
                b15.setVisibility(View.VISIBLE);
                b11.setVisibility(View.GONE);
                b13.setVisibility(View.GONE);
                b14.setVisibility(View.GONE);
                b12.setVisibility(View.GONE);
                b16.setVisibility(View.GONE);
                b17.setVisibility(View.GONE);
                b18.setVisibility(View.GONE);
                b19.setVisibility(View.GONE);
                b20.setVisibility(View.GONE);
            }
        });

        b6.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                b6.setVisibility(View.GONE);
                b1.setVisibility(View.VISIBLE);
                b3.setVisibility(View.VISIBLE);
                b4.setVisibility(View.VISIBLE);
                b5.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);
                b7.setVisibility(View.VISIBLE);
                b8.setVisibility(View.VISIBLE);
                b9.setVisibility(View.VISIBLE);
                b10.setVisibility(View.VISIBLE);
                b16.setVisibility(View.VISIBLE);
                b11.setVisibility(View.GONE);
                b13.setVisibility(View.GONE);
                b14.setVisibility(View.GONE);
                b12.setVisibility(View.GONE);
                b15.setVisibility(View.GONE);
                b17.setVisibility(View.GONE);
                b18.setVisibility(View.GONE);
                b19.setVisibility(View.GONE);
                b20.setVisibility(View.GONE);
                t1.setText("Red");
            }
        });

        b7.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                b7.setVisibility(View.GONE);
                b1.setVisibility(View.VISIBLE);
                b3.setVisibility(View.VISIBLE);
                b4.setVisibility(View.VISIBLE);
                b5.setVisibility(View.VISIBLE);
                b6.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);
                b8.setVisibility(View.VISIBLE);
                b9.setVisibility(View.VISIBLE);
                b10.setVisibility(View.VISIBLE);
                b17.setVisibility(View.VISIBLE);
                b11.setVisibility(View.GONE);
                b13.setVisibility(View.GONE);
                b14.setVisibility(View.GONE);
                b15.setVisibility(View.GONE);
                b16.setVisibility(View.GONE);
                b12.setVisibility(View.GONE);
                b18.setVisibility(View.GONE);
                b19.setVisibility(View.GONE);
                b20.setVisibility(View.GONE);
                t1.setText("Silver");
            }
        });

        b8.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                b8.setVisibility(View.GONE);
                b1.setVisibility(View.VISIBLE);
                b3.setVisibility(View.VISIBLE);
                b4.setVisibility(View.VISIBLE);
                b5.setVisibility(View.VISIBLE);
                b6.setVisibility(View.VISIBLE);
                b7.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);
                b9.setVisibility(View.VISIBLE);
                b10.setVisibility(View.VISIBLE);
                b18.setVisibility(View.VISIBLE);
                b11.setVisibility(View.GONE);
                b13.setVisibility(View.GONE);
                b14.setVisibility(View.GONE);
                b15.setVisibility(View.GONE);
                b16.setVisibility(View.GONE);
                b17.setVisibility(View.GONE);
                b12.setVisibility(View.GONE);
                b19.setVisibility(View.GONE);
                b20.setVisibility(View.GONE);
                t1.setText("White");
            }
        });
        b9.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                b9.setVisibility(View.GONE);
                b1.setVisibility(View.VISIBLE);
                b3.setVisibility(View.VISIBLE);
                b4.setVisibility(View.VISIBLE);
                b5.setVisibility(View.VISIBLE);
                b6.setVisibility(View.VISIBLE);
                b7.setVisibility(View.VISIBLE);
                b8.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);
                b10.setVisibility(View.VISIBLE);
                b19.setVisibility(View.VISIBLE);
                b11.setVisibility(View.GONE);
                b13.setVisibility(View.GONE);
                b14.setVisibility(View.GONE);
                b15.setVisibility(View.GONE);
                b16.setVisibility(View.GONE);
                b17.setVisibility(View.GONE);
                b18.setVisibility(View.GONE);
                b12.setVisibility(View.GONE);
                b20.setVisibility(View.GONE);
                t1.setText("Yellow");
            }
        });

        b10.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                b10.setVisibility(View.GONE);
                b20.setVisibility(View.VISIBLE);
                b1.setVisibility(View.VISIBLE);
                b3.setVisibility(View.VISIBLE);
                b4.setVisibility(View.VISIBLE);
                b5.setVisibility(View.VISIBLE);
                b6.setVisibility(View.VISIBLE);
                b7.setVisibility(View.VISIBLE);
                b8.setVisibility(View.VISIBLE);
                b9.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);
                b11.setVisibility(View.GONE);
                b13.setVisibility(View.GONE);
                b14.setVisibility(View.GONE);
                b15.setVisibility(View.GONE);
                b16.setVisibility(View.GONE);
                b17.setVisibility(View.GONE);
                b18.setVisibility(View.GONE);
                b19.setVisibility(View.GONE);
                b12.setVisibility(View.GONE);
                t1.setText("Grey");
            }
        });
        b21.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if (c1.isChecked()) {
                    savecardata();
                    isInternetOn();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please indicate that you accept the Terms and Conditions", Toast.LENGTH_SHORT).show();
                }

            }


        });
    }

    private void savecardata() {
        if (dbHelper.insertCar(email.toString(),et1.getText().toString(),
                et2.getText().toString(), t1.getText().toString(), et4.getText().toString())) {
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.custom_toast,
                    (ViewGroup) findViewById(R.id.toast_layout_root));
            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();
            Intent intent = new Intent(getApplicationContext(), Welcome2.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Could not Insert Car", Toast.LENGTH_SHORT).show();
        }
        make = et1.getText().toString();
        model = et2.getText().toString();
        registration = et4.getText().toString();
        color = t1.getText().toString();
        make = make.trim();
        model = model.trim();
        color = color.trim();
        registration = registration.trim();
        if (!make.isEmpty()) if (note == null) {
            ParseObject post = new ParseObject("Cardetail");
            post.put("carid", ParseUser.getCurrentUser());
            post.put("Make", make);
            post.put("Model", model);
            post.put("Registration", registration);
            post.put("Color", color);
            post.saveEventually(new SaveCallback() {
                public void done(com.parse.ParseException e) {
                    if (e == null) {
                        Toast.makeText(BookerAddcar.this, "You SucessFully save your data on parse !", Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(BookerAddcar.this, "Error!", Toast.LENGTH_SHORT).show();
                        Log.d(getClass().getSimpleName(), "User update error: " + e);
                    }
                }
            });

        }


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

            make = et1.getText().toString();
            model = et2.getText().toString();
            registration = et4.getText().toString();
            color = t1.getText().toString();
            make = make.trim();
            model = model.trim();
            color = color.trim();
            registration = registration.trim();
            if (!make.isEmpty()) if (note == null) {
                // create new post

                ParseObject post = new ParseObject("Cardetail");
                post.put("carid", ParseUser.getCurrentUser());
                post.put("Make", make);
                post.put("Model", model);
                post.put("Registration", registration);
                post.put("Color", color);
                post.saveEventually(new SaveCallback() {
                    public void done(com.parse.ParseException e) {
                        if (e == null) {
                            Toast.makeText(BookerAddcar.this, "You SucessFully save your data on parse !", Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(BookerAddcar.this, "Error!", Toast.LENGTH_SHORT).show();
                            Log.d(getClass().getSimpleName(), "User update error: " + e);
                        }
                    }
                });
            }
            return true;
        }
        else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {

            Toast.makeText(this, " Not Internet connection , please connect to the internet ", Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),Welcome2.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}















