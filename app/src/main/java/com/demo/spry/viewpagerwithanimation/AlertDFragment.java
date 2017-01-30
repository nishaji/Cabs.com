package com.demo.spry.viewpagerwithanimation;

/**
 * Created by sprydev5 on 4/2/16.
 */

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.parse.ParseUser;

public class AlertDFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                // Set Dialog Icon
                .setIcon(R.drawable.alert)
                        // Set Dialog Title
                .setTitle("Sign Out")
                        // Set Dialog Message
                .setMessage("Are you sure ,Do you want to logout")

                        // Positive button
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ParseUser.logOut();
                        Intent i = new Intent(AlertDFragment.this.getContext(), LoginSignupActivity.class);
                        startActivity(i);
                        SharedPreferences settings = getActivity().getSharedPreferences(LoginActivity.PREFS_NAME, 0); // 0 - for private mode
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putBoolean("hasLoggedIn", false);
                        editor.commit();
                       // finish();
                    }
                })

                        // Negative Button
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,	int which) {
                        // Do something else
                    }
                }).create();
    }

}
