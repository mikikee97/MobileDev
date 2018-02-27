package com.beekee.mykid;

import android.content.Context;
import android.content.SharedPreferences;


import java.util.ArrayList;


/**
 * Created by Owner on 2/25/2018.
 */

public class InfoSharedPreference {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;


    private Context mContext;

    int PRIVATE_MODE= 0;

    // Sharedpref file name
    private static final String PREF_NAME = "pref";
    private static final String DETAILS = "details";

    public InfoSharedPreference(Context context){
        this.mContext = context;
        pref = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
     }

    public void saveDetailList(String detailString) {
        editor.putString(DETAILS, detailString);
        editor.commit();
    }

    public String getDetailList() {
        return pref.getString(DETAILS, "");
    }





}
