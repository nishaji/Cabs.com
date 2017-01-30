package com.demo.spry.viewpagerwithanimation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by sprydev5 on 12/12/15.
 */
public class Sucess extends Fragment {
    Spinner from, to, datetime;
    View rootView;
    private PopupWindow pwindo;
    Button btnClosePopup;
    TextView txtView;
    Handler handler;
    TextView mSwitcher;

    Animation in;
    Animation out;

    int fadeCount;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.sucess, container, false);
        txtView=(TextView)rootView.findViewById(R.id.text1);
        fadeCount = 0;

        handler = new Handler();

        mSwitcher = (TextView)rootView. findViewById(R.id.textView1);
        mSwitcher.setText("You Sucessfully added Driver");

        in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(2000);

        out = new AlphaAnimation(1.0f, 0.0f);
        out.setDuration(2000);
        out.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationEnd(Animation animation) {
                fadeCount++;
                if (fadeCount == 3) {
                    mSwitcher.setText("");
                    mSwitcher.setText("Sucessful");
                } else {
                    if (fadeCount == 1) {
                        mSwitcher.setText("Please swipe left to see menues.");
                    }

                    mSwitcher.startAnimation(in);
                    handler.postDelayed(mFadeOut, 2000);
                }
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationStart(Animation arg0) {
                // TODO Auto-generated method stub

            }
        });

        //mSwitcher.startAnimation(out);
        mSwitcher.setText("You Sucessfully added Driver.");
        mSwitcher.startAnimation(in);

       /*
        mSwitcher.startAnimation(out);
        mSwitcher.setText("Text 2.");
        mSwitcher.startAnimation(in);
        */

        handler.postDelayed(mFadeOut, 5000);
        return rootView;
    }

    private Runnable mFadeOut =new Runnable(){

        @Override
        public void run() {
            //Speed up the last fade-out so that the Activity opens faster
            if (fadeCount == 2){
                out.setDuration(2000);
            }
            mSwitcher.startAnimation(out);
        }
    };


}
