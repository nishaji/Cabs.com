package com.demo.spry.viewpagerwithanimation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.text.ParseException;

/**
 * Created by sprydev5 on 27/11/15.
 */
public class PrimaryFragment extends Fragment {
    private EditText et1, et2, et3, et4, et5;
    String make, model, range, registration, color, id, carType;
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20, b21;
    CheckBox c1;
    TextView t1;
    private ContactsContract.CommonDataKinds.Note note;
    private Databasehandler dbHelper;

    @SuppressLint("SimpleDateFormat")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.primary_layout, parent, false);
        dbHelper = new Databasehandler(this.getActivity());
        et1 = (EditText)view. findViewById(R.id.et1);
        et2 = (EditText)view. findViewById(R.id.et2);
        et4 = (EditText)view. findViewById(R.id.et4);
        b1 = (Button)view. findViewById(R.id.btn1);
        b2 = (Button) view.findViewById(R.id.btn2);
        b3 = (Button) view.findViewById(R.id.btn3);
        b4 = (Button)view. findViewById(R.id.btn4);
        b5 = (Button) view.findViewById(R.id.btn5);
        b6 = (Button)view. findViewById(R.id.btn6);
        b7 = (Button)view. findViewById(R.id.btn7);
        b8 = (Button) view.findViewById(R.id.btn8);
        b9 = (Button) view.findViewById(R.id.btn9);
        b10 = (Button) view.findViewById(R.id.btn10);
        b11 = (Button)view. findViewById(R.id.btn11);
        b12 = (Button)view. findViewById(R.id.btn12);
        b13 = (Button)view. findViewById(R.id.btn13);
        b14 = (Button)view. findViewById(R.id.btn14);
        b15 = (Button)view. findViewById(R.id.btn15);
        b16 = (Button) view.findViewById(R.id.btn16);
        b17 = (Button)view. findViewById(R.id.btn17);
        b18 = (Button)view. findViewById(R.id.btn18);
        b19 = (Button)view. findViewById(R.id.btn19);
        b20 = (Button)view. findViewById(R.id.btn20);
        b21 = (Button)view. findViewById(R.id.btnsubmit);
        c1 = (CheckBox)view. findViewById(R.id.checkBox1);
        t1 = (TextView) view.findViewById(R.id.text1);
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
                    }
                    else {
                        Toast.makeText(getContext(), "Please indicate that you accept the Terms and Conditions", Toast.LENGTH_SHORT).show();
                    }

                }
            });

    return view;
        }

    private void savecardata() {
        /*if (dbHelper.insertCar(et1.getText().toString(),
                et2.getText().toString(), t1.getText().toString(), et4.getText().toString())) {
            Toast.makeText(getContext(), "Car Inserted", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getContext(), Welcome1.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } /**/
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
            post.saveInBackground(new SaveCallback() {
                public void done(com.parse.ParseException e) {
                    if (e == null) {
                        Toast.makeText(getContext(), "You SucessFully added your car!", Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(getContext(), "Error!", Toast.LENGTH_SHORT).show();
                        Log.d(getClass().getSimpleName(), "User update error: " + e);
                    }
                }
            });

        }


    }
}















