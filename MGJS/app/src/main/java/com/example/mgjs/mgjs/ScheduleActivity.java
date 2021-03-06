package com.example.mgjs.mgjs;


import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ScheduleActivity extends ListActivity {

    ImageButton schedule_addButton;
    Intent intent;
    ScheduleDBHelper scheduleDBHelper;
    ListView schedule_listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        schedule_addButton = (ImageButton)findViewById(R.id.schedule_addButton);
        scheduleDBHelper = new ScheduleDBHelper(this);
        schedule_listView = getListView();
        showSchedule();
        schedule_listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent,View view, int position,long id){
                intent = new Intent(ScheduleActivity.this, Detail_ScheduleActivity.class);
                SQLiteDatabase scheduledb = scheduleDBHelper.getWritableDatabase();
                Cursor cursor = scheduledb.rawQuery("SELECT schedule_id FROM Schedule ORDER BY  schedule_year, schedule_month, schedule_date", null);
                if (cursor.moveToFirst()) {
                    int i = cursor.getInt(cursor.getColumnIndex("schedule_id"));

                    for (int j = 0; j < position && cursor.moveToNext(); ) {
                        i = cursor.getInt(cursor.getColumnIndex("schedule_id"));
                        j++;
                    }
                    intent.putExtra("id",i);
                    startActivityForResult(intent,1);
                }
            }});
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        showSchedule();
    }


    public void onAddScheduleClicked(View view){
        intent = new Intent(ScheduleActivity.this, Add_ScheduleActivity.class);
        startActivityForResult(intent,1);

    }

    public void showSchedule(){
        ArrayList<HashMap<String,String>> scheduleList;
        scheduleList = getScheduleList();
        ListAdapter scheduleListAdapter = new SimpleAdapter(ScheduleActivity.this,scheduleList,R.layout.schedule_entry,new String[]{"schedule_content","schedule_year","schedule_month","schedule_date"},
               new int[] {R.id.schedule_content,R.id.schedule_year,R.id.schedule_month,R.id.schedule_day});
        setListAdapter(scheduleListAdapter);
        if (scheduleList.size()==0) {
            Toast.makeText(this, "schedule does not exist!", Toast.LENGTH_SHORT).show();
        }
    }

    //CREATE TABLE Schedule ( _id, schedule_content , schedule_year , schedule_month , schedule_date )
    public ArrayList<HashMap<String, String>>getScheduleList(){
        SQLiteDatabase scheduleDB = scheduleDBHelper.getReadableDatabase();
        String selectQuery = "SELECT schedule_id, schedule_content, schedule_year,schedule_month,schedule_date FROM Schedule ORDER BY schedule_year, schedule_month, schedule_date";
        ArrayList<HashMap<String, String>> scheduleList = new ArrayList<>();

        try {
            Cursor cursor = scheduleDB.rawQuery(selectQuery, null);

            if (cursor.moveToFirst()){
                do{
                    HashMap<String,String> schedule = new HashMap<>();
                    schedule.put("schedule_content",cursor.getString(cursor.getColumnIndex("schedule_content")));
                    schedule.put("schedule_year",cursor.getString(cursor.getColumnIndex("schedule_year")));
                    schedule.put("schedule_month",cursor.getString(cursor.getColumnIndex("schedule_month")));
                    schedule.put("schedule_date",cursor.getString(cursor.getColumnIndex("schedule_date")));
                    scheduleList.add(schedule);
                }while (cursor.moveToNext());
            }
            cursor.close();
        }catch (Exception e){
            Log.e("dberror",e.getMessage());
        }

        scheduleDB.close();
        return scheduleList;
    }
}