package com.demo.spry.viewpagerwithanimation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by sprydev5 on 8/12/15.
 */
public class Aboutus extends Fragment{

    private ContactsContract.CommonDataKinds.Note note;

    @SuppressLint("SimpleDateFormat")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.aboutus, parent, false);
        {
            return view;
        }
    }
}
