package com.beekee.mykid;

import java.util.ArrayList;

/**
 * Created by Owner on 2/24/2018.
 */

public class Details {

    private String mActivityName,mLocation,mReporterName,mTime,mDate;

    public Details(String mActivityName, String mLocation, String mReporterName,String mTime,String mDate) {
        this.mActivityName = mActivityName;
        this.mLocation = mLocation;
        this.mReporterName = mReporterName;
        this.mTime = mTime;
        this.mDate = mDate;

    }

    public String getmActivityName() {
        return mActivityName;
    }

    public String getmLocation() {
        return mLocation;
    }

    public String getmReporterName() {
        return mReporterName;
    }

    public String getmTime() {
        return mTime;
    }

    public String getmDate() { return mDate; }



    public void printData(ArrayList <Details> detailList){
        for (Details ob : detailList);
        System.out.println("Activity name:" + mActivityName);
        //System.out.println("Activity name: " + mActivityName + "\nLocation: " + mLocation + "\nReporter Name: " + mReporterName);
    }



}
