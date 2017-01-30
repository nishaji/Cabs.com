package com.demo.spry.viewpagerwithanimation;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

/**
 * Created by sprydev5 on 12/1/16.
 */
public class Cars extends ContentProvider{

    public static final String PROVIDER_NAME = "com.demo.spry.viewpagerwithanimation.cars";

    /** A uri to do operations on cust_master table. A content provider is identified by its uri */
    public static final Uri carsuri = Uri.parse("content://" + PROVIDER_NAME + "/cars" );
    public static final Uri tripsuri = Uri.parse("content://" + PROVIDER_NAME + "/trips" );
    public static final Uri driversuri = Uri.parse("content://" + PROVIDER_NAME + "/driver" );
    public static final Uri tripdetailuri = Uri.parse("content://" + PROVIDER_NAME + "/tripdetail" );
    public static final Uri bookinguri = Uri.parse("content://" + PROVIDER_NAME + "/booking" );

    /** Constants to identify the requested operation */
    private static final int CARS = 1;
    private static final int TRIP=2;
    private static final int DRIVER=3;
    private static final int TRIPDETAIL=4;
    private static final int BOOKING=5;
    private static final UriMatcher uriMatcher ;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "cars", CARS);
        uriMatcher.addURI(PROVIDER_NAME, "trips", TRIP);
        uriMatcher.addURI(PROVIDER_NAME,"driver",DRIVER);
        uriMatcher.addURI(PROVIDER_NAME,"tripdetail",TRIPDETAIL);
        uriMatcher.addURI(PROVIDER_NAME,"booking",BOOKING);
    }

    /** This content provider does the database operations by this object */
    Databasehandler mCustomerDB;

    /** A callback method which is invoked when the content provider is starting up */
    @Override
    public boolean onCreate() {
        mCustomerDB = new Databasehandler(getContext());
        return true;
    }


    @Override
    public String getType(Uri uri) {
        return null;
    }



    /** A callback method which is by the default content uri */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.d("Cars.query","uri "+uri.toString());
        if(uriMatcher.match(uri)==CARS){
            return mCustomerDB.getAllCars();
        }else if (uriMatcher.match(uri)==TRIP) {
            return mCustomerDB.getAllTrips();
        }else if(uriMatcher.match(uri)==DRIVER){
            return mCustomerDB.getAllDrivers();
        }else if(uriMatcher.match(uri)==TRIPDETAIL){
            return mCustomerDB.getdetailTrips();
        }else if (uriMatcher.match(uri)==BOOKING){
            return mCustomerDB.getAllBookings();
        }

        else {
            return null;
        }
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }


}

