package com.beekee.mykid;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v4.app.DialogFragment;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class InfoActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener{

    private TextView dateDisplay,timeDisplay;
    private Button pickDate,pickTime,save;
    private EditText activityName,location,reporterName;
    private ArrayList<Details> details;
    private InfoSharedPreference sharedPreference;
    private HashSet<String> detailset;
    private Gson gson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        dateDisplay = (TextView) findViewById(R.id.dateDisplay);
        pickDate = (Button) findViewById(R.id.pickDate);
        activityName = (EditText) findViewById(R.id.editActivityName);
        location = (EditText) findViewById(R.id.editLocation);
        timeDisplay = (TextView)findViewById(R.id.Time);
        reporterName = (EditText) findViewById(R.id.editReporterName);
        save = (Button) findViewById(R.id.save_btn);
        gson = new Gson();
        sharedPreference = new InfoSharedPreference(getApplicationContext());
        String jsonScore = sharedPreference.getDetailList();
        Type type = new TypeToken<List<Details>>(){}.getType();
        details = gson.fromJson(jsonScore, type);

        if (details == null) {
            details = new ArrayList<>();
        }


    }

    public void pickDate_clicked(View view){
        //Calendar cal = Calendar.getInstance();
        DialogFragment datePicker = new DatePickerFragment();
        datePicker.show(getSupportFragmentManager(), "date picker");

    }

    public void pickTime_clicked(View view){
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "time picker");

    }

    public void save_btn_clicked(View view){

        Details userDetail = new Details(activityName.getText().toString(),location.getText().toString(),reporterName.getText().toString());
        //details = new ArrayList<Details>();
        details.add(userDetail);

        //userDetail.printData(details);
        //System.out.println(show.getmActivityName());
        saveDetailListToSharedpreference(details);
    }

    private void saveDetailListToSharedpreference(ArrayList<Details> detailsList) {
        //convert ArrayList object to String by Gson
        String jsonDetail = gson.toJson(detailsList);

        //save to shared preference
        sharedPreference.saveDetailList(jsonDetail);
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        dateDisplay.setText(currentDate);

    }

    @Override
    public void onTimeSet(TimePicker view, int hour, int minute) {
        /*Calendar cal = Calendar.getInstance();
        String am_pm_text = "";
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        int am_pm = cal.get(Calendar.AM_PM);

        if (am_pm == Calendar.AM)
            am_pm_text = "AM";
        else if (am_pm == Calendar.PM)
            am_pm_text = "PM";*/


        String timeSet = "";
        if (hour > 12) {
            hour -= 12;
            timeSet = "PM";
        } else if (hour == 0) {
            hour += 12;
            timeSet = "AM";
        } else if (hour == 12){
            timeSet = "PM";
        }else{
            timeSet = "AM";
        }

        String min = "";
        if (minute < 10)
            min = "0" + minute ;
        else
            min = String.valueOf(minute);

        // Append in a StringBuilder
        String aTime = new StringBuilder().append(hour).append(':')
                .append(min ).append(" ").append(timeSet).toString();
        timeDisplay.setText(aTime);

        //timeDisplay.setText(hour + ":" + minute + " " + am_pm_text);

    }
}
