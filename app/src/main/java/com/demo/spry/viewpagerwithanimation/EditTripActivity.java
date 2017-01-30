package com.demo.spry.viewpagerwithanimation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class EditTripActivity extends AppCompatActivity {

    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9;
    String route,date,time,car,driver,fare,seat,model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_trip);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_18dp);
        toolbar.setTitle("Add Car");
        toolbar.setTitleTextColor(0xffffffff);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Welcome1.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });
        t1=(TextView)findViewById(R.id.route);
        t2=(TextView)findViewById(R.id.driver);
        t3=(TextView)findViewById(R.id.car);
        t4=(TextView)findViewById(R.id.model);
        t5=(TextView)findViewById(R.id.date);
        t6=(TextView)findViewById(R.id.time);
        t7=(TextView)findViewById(R.id.fare);
        t8=(TextView)findViewById(R.id.seat);

        Intent intent=getIntent();
        route=intent.getStringExtra("route");
        t1.setText(route);
        driver=intent.getStringExtra("driver");
        t2.setText(driver);
        car=intent.getStringExtra("car");
        t3.setText(car);
        model=intent.getStringExtra("model");
        t4.setText(model);
        date=intent.getStringExtra("date");
        t5.setText(date);
        time=intent.getStringExtra("time");
        t6.setText(driver);
        fare=intent.getStringExtra("fare");
        t7.setText(fare);
        seat=intent.getStringExtra("seat");
        t8.setText(seat);

    }

}
