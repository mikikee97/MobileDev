package com.beekee.mykid;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.DialogFragment;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;


import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class InfoActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener{

    private TextView dateDisplay,timeDisplay;
    private Button pickDate,pickTime,save;
    private EditText activityNameText,locationText,reporterNameText;
    private ArrayList<Details> details;
    private InfoSharedPreference sharedPreference;
    private HashSet<String> detailset;
    private Gson gson;
    private String time,date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        android.support.v7.app.ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(ResourcesCompat.getColor(getResources(),R.color.darkOrange,null)));
        bar.setTitle("New Activity");

        dateDisplay = (TextView) findViewById(R.id.dateDisplay);
        pickDate = (Button) findViewById(R.id.pickDate);
        activityNameText = (EditText) findViewById(R.id.editActivityName);
        locationText = (EditText) findViewById(R.id.editLocation);
        timeDisplay = (TextView)findViewById(R.id.Time);
        reporterNameText = (EditText) findViewById(R.id.editReporterName);
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


        final String activityName = activityNameText.getText().toString();
        final String location = locationText.getText().toString();
        final String reporterName = reporterNameText.getText().toString();

        if (TextUtils.isEmpty(activityName)){
            activityNameText.setError("Please enter activity name");
            return;
        }

        if (TextUtils.isEmpty(reporterName)){
            reporterNameText.setError("Please enter reporter name");
            return;
        }

        if (TextUtils.isEmpty(date)){
            Toast.makeText(this,"Please select a date",Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(time)){
            time = "";
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(InfoActivity.this);
        builder.setMessage("Save this Activity?\n\nActivity : " + activityName + "\nLocation : " + location + "\nReporter Name : " + reporterName + "\nDate : " + date + "\nTime : " + time)
                .setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Details userDetail = new Details(activityName,location,reporterName,time,date);
                        details.add(userDetail);
                        saveDetailListToSharedpreference(details);
                        Intent intent = new Intent(InfoActivity.this,MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(InfoActivity.this,"New Activity Added!",Toast.LENGTH_SHORT).show();
                        finish();
                        MainActivity.fa.finish();
                    }
                })
                .setNegativeButton("Cancel",null);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

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
        date = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        dateDisplay.setText(date);

    }

    @Override
    public void onTimeSet(TimePicker view, int hour, int minute) {

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
        time = new StringBuilder().append(hour).append(':')
                .append(min ).append(" ").append(timeSet).toString();

        timeDisplay.setText(time);

    }

    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(InfoActivity.this);
        builder.setMessage("Go back without saving?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        InfoActivity.super.onBackPressed();

                    }
                })
                .setNegativeButton("Cancel",null);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
