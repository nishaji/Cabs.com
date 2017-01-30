package com.demo.spry.viewpagerwithanimation;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by sprydev5 on 23/11/15.
 */
public class FragmentC extends  Fragment implements View.OnClickListener {
    Button myButton,btn;

    private TextView btnLogin;
    private ProgressDialog progressDialog;
    User user;
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      //  startActivity(new Intent(getActivity(), PrimaryFragment.class));
         v = inflater.inflate(R.layout.fragment_c,container,false);


        myButton = (Button) v.findViewById(R.id.btn1);
        btn=(Button)v.findViewById(R.id.btn2);
        myButton.setOnClickListener(this);
        btn.setOnClickListener(this);
        return v;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

                case R.id.btn1:
                    // do your code
                    Intent intent = new Intent(getActivity(), SignupActivity.class);
                    getActivity().startActivity(intent);
                    break;

                case R.id.btn2:
                    // do your code
                    Intent intent1 = new Intent(getActivity(), LoginActivity.class);
                    getActivity().startActivity(intent1);
                    break;
                    default:
                    break;
            }


        }

}