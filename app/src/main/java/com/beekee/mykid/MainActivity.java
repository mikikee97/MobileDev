package com.example.asus.mykid;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnNext1, btnBack;
    private EditText edtActivityNa, edtReporterNa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set the reference to widget
        btnNext1 = (Button) findViewById(R.id.nextBtn1);
        edtActivityNa = (EditText)findViewById(R.id.activityNaEdit);
        edtReporterNa = (EditText) findViewById(R.id.reporterNaEdit);

        //register the event
        btnNext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if((edtActivityNa.getText().toString().trim().equals(""))){
                    Toast.makeText(getApplicationContext(), "Activity Name is empty", Toast.LENGTH_SHORT).show();
                }
                if((edtReporterNa.getText().toString().trim().equals(""))){
                    Toast.makeText(getApplicationContext(), "Reporter Name is empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent i = new Intent(MainActivity.this,Main2Activity.class);
                    startActivity(i);
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
                alert.setTitle("Save Confirmation");
                alert.show();
            }
        });
    }

}//end class
