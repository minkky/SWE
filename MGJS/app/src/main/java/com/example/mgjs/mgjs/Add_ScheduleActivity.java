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

    Intent intent;
    ScheduleDBHelper sDBhelper;
    SQLiteDatabase scheduledb;
    EditText scheduleContent;
    String content;
    int schedule_year, schedule_month, schedule_day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addschedule);
        intent = getIntent();

        sDBhelper = new ScheduleDBHelper(this);
        try {
            scheduledb = sDBhelper.getWritableDatabase();
        }catch (SQLiteException ex){
            scheduledb = sDBhelper.getReadableDatabase();
        }

        scheduleContent = (EditText)findViewById(R.id.schedule_add_content);
        scheduleContent.setText("");



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

    public void saveSchedule(View v) {
        String resultStr = "";
        String expectedStr = "";
        String sqlStr = "";

        content = scheduleContent.getText().toString();
        expectedStr = content + schedule_year + schedule_month + schedule_day;
        sqlStr = setSqlQuery(content, schedule_year, schedule_month, schedule_day);

        scheduledb.execSQL(sqlStr);
        scheduledb = sDBhelper.getReadableDatabase();
        Cursor cursor = scheduledb.rawQuery("SELECT * FROM Schedule", null);

        while (cursor.moveToNext()) {
            resultStr = cursor.getString(1) + cursor.getInt(2) + cursor.getInt(3) + cursor.getInt(4);
        }

        if (confirmInsertion(expectedStr, resultStr)) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Successfully Saved!");
            alertDialogBuilder.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            intent = new Intent();
                            intent.putExtra("data","data");
                            setResult(RESULT_OK, intent);
                            finish();

                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
        else
            Toast.makeText(getApplicationContext(),"Insertion Error! Try Again",Toast.LENGTH_SHORT).show();
    }

    public String setSqlQuery(String content, int year, int month, int day){
        String sqlStr = "";
        sqlStr = "INSERT INTO Schedule VALUES(null,'" + content + "'," + year + "," + month + "," + day + ");";

        return sqlStr;
    }

    public boolean confirmInsertion(String expectedStr, String resultStr) {
        if(expectedStr.equals(resultStr))
            return  true;
        else
            return false;
    }

    public void cancelSchedule(View v){
        this.finish();
    }
}
