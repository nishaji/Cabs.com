package com.demo.spry.viewpagerwithanimation;

/**
 * Created by sprydev5 on 1/12/15.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceConfigurationError;

/**
 * Created by sprydev5 on 11/9/15.
 */


public class Databasehandler  extends SQLiteOpenHelper{

    /** Database name */
    private static String DBNAME = "cabapp.db";

    /** Version number of the database */
    private static int VERSION = 1;

    public static final String CAR = "addcar";
    public static final String CAR_COLUMN_ID = "_id";
    public static final String CAR_COLUMN_USER_ID="userid";
    public static final String CAR_COLUMN_BRAND = "brand";
    public static final String CAR_COLUMN_MODEL = "model";
    public static final String CAR_COLUMN_COLOR = "backcolor";
    public static final String CAR_COLUMN_REGISTRATION = "registration";
    public static final String driver_table_name="adddriver";
    public static final String driver_coloumn_id="_id";
    public static final String driver_user_id="userid";
    public static final String driver_fname="fname";
    public static final String driver_lname="lname";
    public static final String driver_phone="phone";
    public static final String driver_licence="licence";
    public static final String trip_table_name="addtrip";
    public static final String trip_column_id="_id";
    public static final String trip_user_id="userid";
    public static final String trip_column_route="route";
    public static final String trip_column_cars="cars";
    public static final String trip_column_model="model";
    public static final String trip_column_drivers="drivers";
    public static final String trip_column_seats="seats";
    public static final String trip_column_fare="fare";
    public static final String trip_column_date="date";
    public static final String trip_column_time="time";
    public static final String booking_table_name="booking";
    public static final String booking_column_id="_id";
    public static final String booking_column_drivername="drivername";
    public static final String booking_column_carname="carname";
    public static final String boking_column_modelname="modelname";
    public static final String booking_column_datetime="datetime";
    public static final String booking_column_rupay="rupay";
    public static final String booking_column_pickup="pickup";
    public static final String booking_column_drop="droppt";
    /** An instance variable for SQLiteDatabase */
    private SQLiteDatabase mDB;


    /** Constructor */
    public Databasehandler(Context context) {
        super(context, DBNAME, null, VERSION);
        this.mDB = getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql1 = 	"create table "+ CAR + " ( "
                + CAR_COLUMN_ID + " integer primary key autoincrement , "
                + CAR_COLUMN_USER_ID + " text ,"
                + CAR_COLUMN_BRAND + " text  , "
                + CAR_COLUMN_MODEL + "  text  , "
                + CAR_COLUMN_COLOR + "  text ,"
                +  CAR_COLUMN_REGISTRATION + " text ) " ;


       String sql2 = " create table " + trip_table_name + " ( "
               + trip_column_id +" integer primary key autoincrement ,"
               + trip_user_id  + " text ,"
               + trip_column_route +" text , "
               + trip_column_drivers + " text , "
               + trip_column_cars + " text , "
               + trip_column_model + " text , "
               + trip_column_date + " text ,"
               + trip_column_time + " text ,"
               + trip_column_fare + " text ,"
               + trip_column_seats + " text )";

        String sql3="create table " + driver_table_name + " ( "
                + driver_coloumn_id + " integer primary key autoincrement ,"
                + driver_user_id + " text ,"
                + driver_fname + " text ,"
                + driver_lname + " text ,"
                + driver_phone + " text ,"
                + driver_licence + " text )";

        String sql4=" create table " + booking_table_name + " ( "
                + booking_column_id + " integer primary key autoincrement ,"
                + booking_column_drivername + " text ,"
                + booking_column_carname + " text ,"
                + boking_column_modelname + " text ,"
                + booking_column_datetime + " text ,"
                + booking_column_rupay + " text ,"
                +  booking_column_pickup + " text ,"
                 + booking_column_drop + " text )";

        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);
        db.execSQL(sql4);
        Log.d("Spry", "sql2 " + sql2);
        Log.d("Spry", "sql3 " + sql3);
        Log.d("Spry","sql444444444444444444444444444444444444 "+sql4);
    }
    public boolean insertCar(String userid,String brand, String model, String backcolor, String registration) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CAR_COLUMN_USER_ID,userid);
        contentValues.put(CAR_COLUMN_BRAND, brand);
        contentValues.put(CAR_COLUMN_MODEL, model);
        contentValues.put(CAR_COLUMN_COLOR, backcolor);
        contentValues.put(CAR_COLUMN_REGISTRATION, registration);
        db.insert(CAR, null, contentValues);
        return true;
    }
    public boolean updateCar(Integer id, String brand, String model, String backcolor, String registration) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CAR_COLUMN_BRAND, brand);
        contentValues.put(CAR_COLUMN_MODEL,model);
        contentValues.put(CAR_COLUMN_COLOR, backcolor);
        contentValues.put(CAR_COLUMN_REGISTRATION, registration);
        db.update(CAR, contentValues, CAR_COLUMN_ID + " = ? ", new String[]{Integer.toString(id)});
        return true;
    }
    public boolean insertTrip(String userid, String route , String drivers,String cars, String model , String date , String time, String fare,String seat){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(trip_user_id,userid);
        contentValues.put(trip_column_route,route);
        contentValues.put(trip_column_drivers,drivers);
        contentValues.put(trip_column_cars,cars);
        contentValues.put(trip_column_model,model);
        contentValues.put(trip_column_date,date);
        contentValues.put(trip_column_time,time);
        contentValues.put(trip_column_fare,fare);
        contentValues.put(trip_column_seats,seat);
        db.insert(trip_table_name, null, contentValues);
        return  true;
    }
    public boolean insertDriver(String userid, String fname , String lname, String phone , String licence){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(driver_user_id,userid);
        contentValues.put(driver_fname,fname);
        contentValues.put(driver_lname,lname);
        contentValues.put(driver_phone,phone);
        contentValues.put(driver_licence,licence);
        db.insert(driver_table_name,null,contentValues);
        return  true;
    }

    public boolean insertBooking(String drivername , String carname , String modelname , String date , String time,String pickup , String droppt){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(booking_column_drivername,drivername);
        contentValues.put(booking_column_carname,carname);
        contentValues.put(boking_column_modelname,modelname);
        contentValues.put(booking_column_rupay,date);
        contentValues.put(booking_column_datetime, time);
        contentValues.put(booking_column_pickup,pickup);
        contentValues.put(booking_column_drop,droppt);
        db.insert(booking_table_name, null, contentValues);
        return true;
    }

    public Cursor getAllTrips(){
        return mDB.query(trip_table_name, new String[] { trip_column_id,  trip_column_route , trip_column_date, trip_column_time } ,
                null, null, null, null, trip_column_date + " asc ");
    }
    public Cursor getdetailTrips(){
        return mDB.query(trip_table_name, new String[] { trip_column_id,  trip_column_route ,trip_column_drivers,trip_column_cars
                        ,trip_column_model,trip_column_date,trip_column_time, trip_column_fare ,trip_column_seats} ,
                null, null, null, null,
                trip_column_date + " asc ");
    }
    public Cursor getAllCars(){
    return mDB.query(CAR, new String[] { CAR_COLUMN_ID,  CAR_COLUMN_MODEL , CAR_COLUMN_BRAND, CAR_COLUMN_COLOR ,CAR_COLUMN_REGISTRATION} ,
            null, null, null, null,
             CAR_COLUMN_BRAND + " asc ");

}
    public Cursor getAllDrivers(){
        return mDB.query(driver_table_name, new String[] { driver_coloumn_id,  driver_fname , driver_lname, driver_phone ,driver_licence} ,
                null, null, null, null,
                driver_fname + " asc ");
    }
    public Cursor getAllBookings(){
        return mDB.query(booking_table_name, new String[] { booking_column_id,  booking_column_drivername , booking_column_carname, boking_column_modelname ,
                        booking_column_rupay , booking_column_datetime ,booking_column_pickup ,booking_column_drop} ,
                null, null, null, null,
                booking_column_drivername + " asc ");
    }
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
    }
}