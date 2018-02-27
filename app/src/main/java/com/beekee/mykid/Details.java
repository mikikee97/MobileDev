package com.beekee.mykid;

import java.util.ArrayList;

/**
 * Created by Owner on 2/24/2018.
 */

public class Details {

    private String mActivityName,mLocation,mReporterName;

    public Details(String mActivityName, String mLocation, String mReporterName) {
        this.mActivityName = mActivityName;
        this.mLocation = mLocation;
        this.mReporterName = mReporterName;
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



    public void printData(ArrayList <Details> detailList){
        for (Details ob : detailList);
        System.out.println("Activity name:" + mActivityName);
        //System.out.println("Activity name: " + mActivityName + "\nLocation: " + mLocation + "\nReporter Name: " + mReporterName);
    }



}
