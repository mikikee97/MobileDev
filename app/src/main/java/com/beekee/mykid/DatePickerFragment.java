package com.beekee.mykid;

import android.app.DatePickerDialog;
import android.app.Dialog;

import android.support.v4.app.DialogFragment;

import android.os.Bundle;

import java.util.Calendar;

/**
 * Created by Owner on 2/21/2018.
 */

public class DatePickerFragment extends DialogFragment{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(),(DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
    }
}
