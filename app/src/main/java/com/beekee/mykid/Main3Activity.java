package com.example.asus.mykid;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

public class Main3Activity extends AppCompatActivity {

    private TimePicker time_picker;
    private Button btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //set reference to widget
        time_picker = (TimePicker) findViewById(R.id.timePicker);

        //register the event
        time_picker.setIs24HourView(true);
    }//end onCreate

    public void dialogEvent(View v){
        btnFinish = (Button) findViewById(R.id.finishBtn);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder altDial = new AlertDialog.Builder(Main3Activity.this);
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
        });
    }

}//end class
