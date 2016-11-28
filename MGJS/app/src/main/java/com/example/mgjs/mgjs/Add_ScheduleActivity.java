package com.example.mgjs.mgjs;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Add_ScheduleActivity extends AppCompatActivity {

    ScheduleDBHelper sDBhelper;
    SQLiteDatabase scheduledb;
    EditText scheduleContent;
    String content;
    int schedule_year, schedule_month, schedule_day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addschedule);

        sDBhelper = new ScheduleDBHelper(this);
        try {
            scheduledb = sDBhelper.getWritableDatabase();
        }catch (SQLiteException ex){
            scheduledb = sDBhelper.getReadableDatabase();
        }

        scheduleContent = (EditText)findViewById(R.id.schedule_add_content);
        scheduleContent.setText("");
        content = scheduleContent.getText().toString();


        String[] yearStr = getResources().getStringArray(R.array.schedule_year);
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,yearStr);
        final Spinner yearSpinner = (Spinner)findViewById(R.id.schedule_spinner_year);
        yearSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener(){
                    @Override
                    public void onItemSelected
                            (AdapterView<?> parent, View view, int position, long id){
                        String year= parent.getItemAtPosition(position).toString();
                        schedule_year = Integer.parseInt(year);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent){
                    }
                }
        );
        yearSpinner.setAdapter(yearAdapter);

        String[] monthStr = getResources().getStringArray(R.array.schedule_month);
        ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,monthStr);
        final Spinner monthSpinner = (Spinner)findViewById(R.id.schedule_spinner_month);
        monthSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener(){
                    @Override
                    public void onItemSelected
                            (AdapterView<?> parent, View view, int position, long id){
                        String month= parent.getItemAtPosition(position).toString();
                        schedule_month = Integer.parseInt(month);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent){
                    }
                }
        );
        monthSpinner.setAdapter(monthAdapter);

        String[] dayStr = getResources().getStringArray(R.array.schedule_day);
        ArrayAdapter<String> dayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,dayStr);
        final Spinner daySpinner = (Spinner)findViewById(R.id.schedule_spinner_day);
        daySpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener(){
                    @Override
                    public void onItemSelected
                            (AdapterView<?> parent, View view, int position, long id){
                        String day= parent.getItemAtPosition(position).toString();
                        schedule_day = Integer.parseInt(day);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent){
                    }
                }
        );
        daySpinner.setAdapter(dayAdapter);
    }

    public void saveSchedule(View v){

        scheduledb.execSQL("INSERT INTO Schedule VALUES(null,'"+content+"',"+schedule_year+","+schedule_month+","+schedule_day+");");

        scheduledb = sDBhelper.getReadableDatabase();

        Cursor cursor = scheduledb.rawQuery("SELECT * FROM Schedule", null);
        String str = "";
        while (cursor.moveToNext()){
            str=cursor.getString(1)+cursor.getInt(2)+cursor.getInt(3)+ cursor.getInt(4);
        }

        Toast.makeText(getApplicationContext(),str, Toast.LENGTH_LONG).show();//나중 삭제


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Successfully Saved!");
        alertDialogBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(Add_ScheduleActivity.this, ScheduleActivity.class);
                        startActivity(intent);
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void cancleSchedule(View v){
        Intent intent = new Intent(Add_ScheduleActivity.this, ScheduleActivity.class);
        startActivity(intent);
    }
}
