package com.demo.spry.viewpagerwithanimation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;

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

public class EditAddCarActivity extends Activity {
    private EditText et1, et2, et3, et4, et5;
    String make, model, range, registration, color, id, carType;
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20, b21;
    CheckBox c1;
    TextView t1;
    private Databasehandler mHelper;
    private SQLiteDatabase dataBase;
    private ContactsContract.CommonDataKinds.Note note;
    private boolean isUpdate;
    private Databasehandler dbHelper;
    int personID;
     String editbrand,editmodel,editcolor,editregistration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_add_car);
        dbHelper = new Databasehandler(this);
        personID = getIntent().getIntExtra(CarList.KEY_EXTRA_CONTACT_ID, 0);
        Intent i = getIntent();
        editbrand=i.getStringExtra("brand");
        editmodel=i.getStringExtra("model");
        editcolor=i.getStringExtra("color");
        editregistration=i.getStringExtra("registration");
        System.out.println(editbrand + "editttttttttttttttbrandddddddddddddd");
        et1 = (EditText) findViewById(R.id.et1);
        et1.setText(editbrand);
        et2 = (EditText) findViewById(R.id.et2);
        et2.setText(editmodel);
        et4 = (EditText) findViewById(R.id.et4);
        et4.setText(editregistration);
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
                onBackPressed();
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
                    System.out.println("Button is Clicked");
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please indicate that you accept the Terms and Conditions", Toast.LENGTH_SHORT).show();
                }

            }


        });
    }

    private void savecardata() {

            if(dbHelper.updateCar(personID, et1.getText().toString(),
                    et2.getText().toString(), t1.getText().toString(), et4.getText().toString()))
            {
                Toast.makeText(getApplicationContext(), "Person Update Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Welcome1.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(), "Person Update Failed", Toast.LENGTH_SHORT).show();
            }

    }
    @Override
    public void onBackPressed() {
        super.finish();
    }


}















