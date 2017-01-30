package com.demo.spry.viewpagerwithanimation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class CurrentRydedetail extends AppCompatActivity {


    String currentryde;
    TextView current;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_rydedetail);
        Animation a = AnimationUtils.loadAnimation(this, R.anim.anim_translate);
        a.reset();
        current=(TextView)findViewById(R.id.current);
        current.clearAnimation();
        current.startAnimation(a);
        Intent intent=getIntent();
        currentryde=intent.getStringExtra("currentryde");
        System.out.println(currentryde+"cuurreennttrryyddee");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_18dp);
        toolbar.setTitle("Current Ryde");
        toolbar.setTitleTextColor(0xffffffff);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Welcome1.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


    }

}
