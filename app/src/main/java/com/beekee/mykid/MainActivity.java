package com.example.asus.mykid;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnBack, btnDate, btnTime, btnFinish2;
    private EditText edtActivityNa, edtReporterNa, edtDate, edtTime;
    private int day, month, year, hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set the reference to widget
        btnFinish2 = (Button) findViewById(R.id.finishBtn2);
        btnDate = (Button) findViewById(R.id.dateBtn);
        btnTime = (Button) findViewById(R.id.timeBtn);
        edtActivityNa = (EditText)findViewById(R.id.activityNaEdit);
        edtReporterNa = (EditText) findViewById(R.id.reporterNaEdit);
        edtDate = (EditText) findViewById(R.id.dateText);
        edtTime = (EditText) findViewById(R.id.timeText);

        btnDate.setOnClickListener(this);
        btnTime.setOnClickListener(this);

        //register the event
        btnFinish2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                /*
                if((edtActivityNa.getText().toString().trim().equals("")) && (edtDate.getText().toString().trim().equals(""))
                        && (edtReporterNa.getText().toString().trim().equals(""))){
                    Toast.makeText(getApplicationContext(), "Activity Name, Activity Date and Reporter Name is empty",
                            Toast.LENGTH_SHORT).show();
                }
                if((edtActivityNa.getText().toString().trim().equals("")) && (edtDate.getText().toString().trim().equals(""))){
                    Toast.makeText(getApplicationContext(), "Activity Name and Activity Date is empty",
                            Toast.LENGTH_SHORT).show();
                }
                if((edtActivityNa.getText().toString().trim().equals("")) && (edtReporterNa.getText().toString().trim().equals(""))){
                    Toast.makeText(getApplicationContext(), "Activity Name and Reporter Name is empty",
                            Toast.LENGTH_SHORT).show();
                }
                if((edtDate.getText().toString().trim().equals("")) && (edtReporterNa.getText().toString().trim().equals(""))){
                    Toast.makeText(getApplicationContext(), "Activity Date and Reporter Name is empty",
                            Toast.LENGTH_SHORT).show();
                }
                */
                if(edtActivityNa.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(), "Activity Name is empty", Toast.LENGTH_SHORT).show();
                }
                if(edtDate.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(), "Activity Date is empty", Toast.LENGTH_SHORT).show();
                }
                if((edtReporterNa.getText().toString().trim().equals(""))){
                    Toast.makeText(getApplicationContext(), "Reporter Name is empty", Toast.LENGTH_SHORT).show();
                }
                else{
                   /* Intent i = new Intent(MainActivity.this,Main2Activity.class);
                    startActivity(i);
                    */
                    AlertDialog.Builder altDial = new AlertDialog.Builder(MainActivity.this);
                    altDial.setMessage("Do you want to add this activity?").setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });

                    AlertDialog alert = altDial.create();
                    alert.setTitle("Save Confirmation");
                    alert.show();
                }
            }
        });
    }//end onCreate

    public void dialogEvent(View v){
        btnBack = (Button) findViewById(R.id.backBtn);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder altDial = new AlertDialog.Builder(MainActivity.this);
                altDial.setMessage("Do you want to exit?").setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });

                AlertDialog alert = altDial.create();
                alert.setTitle("Back Confirmation");
                alert.show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == btnDate){
            final Calendar c = Calendar.getInstance();
            day = c.get(Calendar.DAY_OF_MONTH);
            month = c.get(Calendar.MONTH);
            year = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int yr, int monthOfYear, int dayOfMonth) {
                    edtDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + yr);
                }
            }
            ,day, month, year);
            datePickerDialog.show();
        }

        if (v == btnTime){
            final Calendar c = Calendar.getInstance();
            hour = c.get(Calendar.HOUR_OF_DAY);
            minute= c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    edtTime.setText(hourOfDay + ":" + minute);
                }
            }
            ,hour, minute, true);
            timePickerDialog.show();
        }
    }
}//end class