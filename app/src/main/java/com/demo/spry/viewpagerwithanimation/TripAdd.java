package com.demo.spry.viewpagerwithanimation;

/**
 * Created by sprydev5 on 13/1/16.
 */
public class TripAdd {
    private String driver;
    private String car;
    private String rupay;
    private String time;

    public TripAdd(String driver) {
        super();
        this.driver = driver;
        this.car = car;
        this.rupay = rupay;
        this.time=time;
    }


    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getRupay() {
        return rupay;
    }

    public void setRupay(String rupay) {
        this.rupay = rupay;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}


