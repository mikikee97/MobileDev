package com.beekee.mykid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import static com.beekee.mykid.MainActivity.fa;

public class FullDetailActivity extends AppCompatActivity {

    private int detailId;
    private SharedPreferences sharedPreferences;
   // private ArrayList<Details> detailsList;
    private TextView textActivityName,textLocation,textReporterName,textDate,textTime;
    private Details details;
    private String activityName,location,reporterName,date,time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_detail);
        android.support.v7.app.ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(ResourcesCompat.getColor(getResources(),R.color.darkOrange,null)));
        bar.setTitle("");
        textActivityName = (TextView) findViewById(R.id.name_text);
        textLocation = (TextView) findViewById(R.id.location_text);
        textReporterName = (TextView) findViewById(R.id.reportername_text);
        textDate = (TextView) findViewById(R.id.date_text);
        textTime = (TextView) findViewById(R.id.time_text);

        Intent intent = getIntent();
        detailId = intent.getIntExtra("position",0);

        activityName = MainActivity.detailsList.get(detailId).getmActivityName();
        location = MainActivity.detailsList.get(detailId).getmLocation();
        reporterName = MainActivity.detailsList.get(detailId).getmReporterName();
        date =  MainActivity.detailsList.get(detailId).getmDate();
        time = MainActivity.detailsList.get(detailId).getmTime();

        textActivityName.setText(activityName);
        textLocation.setText(location);
        textReporterName.setText(reporterName);
        textDate.setText(date);
        textTime.setText(time);



    }
}
