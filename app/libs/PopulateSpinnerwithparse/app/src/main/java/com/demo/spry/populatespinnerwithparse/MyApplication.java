package com.demo.spry.populatespinnerwithparse;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by sprydev5 on 4/12/15.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, "TTZfpIxFrcXxUr5kM5yECH9Pxf4c1tiTEN9En2tQ", "xCZy5gbwtFxzzcgDy1HMwbixwgcYA7Kn9VghDbH0");

    }
}