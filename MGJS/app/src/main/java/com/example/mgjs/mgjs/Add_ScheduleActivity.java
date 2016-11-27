package com.example.mgjs.mgjs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Add_ScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addschedule);

        String[] yearStr = getResources().getStringArray(R.array.schedule_year);
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,yearStr);
        Spinner yearSpinner = (Spinner)findViewById(R.id.schedule_spinner_year);
        yearSpinner.setAdapter(yearAdapter);

        String[] monthStr = getResources().getStringArray(R.array.schedule_month);
        ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,monthStr);
        Spinner monthSpinner = (Spinner)findViewById(R.id.schedule_spinner_month);
        monthSpinner.setAdapter(monthAdapter);

        String[] dayStr = getResources().getStringArray(R.array.schedule_day);
        ArrayAdapter<String> dayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,dayStr);
        Spinner daySpinner = (Spinner)findViewById(R.id.schedule_spinner_day);
        daySpinner.setAdapter(dayAdapter);
    }
}
