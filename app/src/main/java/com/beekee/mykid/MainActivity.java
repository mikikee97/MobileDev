package com.beekee.mykid;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton addActivity;
    private InfoSharedPreference sharedPreference;
    private ArrayList<Details> detailsList;
    private Gson gson;
    private ListView detailListview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.support.v7.app.ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(ResourcesCompat.getColor(getResources(),R.color.darkOrange,null)));
        gson = new Gson();
        bar.setTitle("Activities");
        sharedPreference = new InfoSharedPreference(getApplicationContext());
        addActivity =  (FloatingActionButton) findViewById(R.id.add_activity_btn);
        detailListview = (ListView) findViewById(R.id.list);

        getDetailListFromSharedPreference();

        //getDetailListFromSharedPreference();
        //set adapter for listview and visible it
        final ListViewAdapter adapter = new ListViewAdapter(MainActivity.this, R.layout.detail_item_listview, detailsList);
        detailListview.setAdapter(adapter);


        detailListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                detailListview.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, FullDetailActivity.class);
                intent.putExtra("detailId",id);
            }
        });

    }



    public void add_clicked(View v){

        Intent intent = new Intent(this,InfoActivity.class);
        startActivity(intent);

    }

    private void getDetailListFromSharedPreference() {
        //retrieve data from shared preference
        String jsonScore = sharedPreference.getDetailList();
        Type type = new TypeToken<List<Details>>() {
        }.getType();
        detailsList = gson.fromJson(jsonScore, type);

        if (detailsList == null) {
            detailsList = new ArrayList<>();
        }
    }
}
