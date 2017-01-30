package com.demo.spry.viewpagerwithanimation;

/**
 * Created by sprydev5 on 16/12/15.
 */
import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

/*
 * An extension of ParseObject that makes
 * it more convenient to access information
 * about a given Meal
 */

@ParseClassName("Meal")
public class Meal extends ParseObject {

    public Meal() {
        // A default constructor is required.
    }


    public ParseFile getPhotoFile() {
        return getParseFile("photo");
    }

    public void setPhotoFile(ParseFile file) {
        put("photo", file);
    }

}
