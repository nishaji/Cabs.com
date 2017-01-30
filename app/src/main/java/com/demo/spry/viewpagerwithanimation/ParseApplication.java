package com.demo.spry.viewpagerwithanimation;

/**
 * Created by sprydev5 on 27/11/15.
 */
import com.facebook.FacebookSdk;
import com.parse.Parse;
import com.parse.ParseACL;

import com.parse.ParseObject;
import com.parse.ParseUser;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
            try {
                PackageInfo info = getPackageManager().getPackageInfo(
                        "com.demo.spry.viewpagerwithanimation",  // replace with your unique package name
                        PackageManager.GET_SIGNATURES);
                for (Signature signature : info.signatures) {
                    MessageDigest md = MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());
                    Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                }
            } catch (PackageManager.NameNotFoundException e) {

            } catch (NoSuchAlgorithmException e) {

            }


        Parse.initialize(this, "5JR1VmXiblqXlTT6O5BvnOhrcZVRkrW29NVANxkZ", "djGzbVvGD8TuZNh90jYdqoMlstxnzitl5ZuCELou");
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }
}