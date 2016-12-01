package com.example.mgjs.mgjs;


import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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
                intent.putExtra("id",position);
                startActivity(intent);
        }});
    }

   public void onAddScheduleClicked(View view){
       intent = new Intent(ScheduleActivity.this, Add_ScheduleActivity.class);
       startActivity(intent);
    }

    public void showSchedule(){
        ArrayList<HashMap<String,String>> scheduleList;
        scheduleList = getScheduleList();
        if (scheduleList.size()!=0){
            ListAdapter scheduleListAdapter = new SimpleAdapter(ScheduleActivity.this,scheduleList,R.layout.schedule_entry,new String[]{"schedule_content","schedule_year","schedule_month","schedule_day"},
                    new int[] {R.id.schedule_content,R.id.schedule_year,R.id.schedule_month,R.id.schedule_day});
                   setListAdapter(scheduleListAdapter);
        }else{
            Toast.makeText(this,"schedule is not exist!",Toast.LENGTH_SHORT).show();
        }
    }

    //CREATE TABLE Schedule ( _id, schedule_content , schedule_year , schedule_month , schedule_day )
    public ArrayList<HashMap<String, String>>getScheduleList(){
        SQLiteDatabase scheduleDB = scheduleDBHelper.getReadableDatabase();
        String selectQuery = "SELECT _id, schedule_content, schedule_year,schedule_month,schedule_day FROM schedule";
        ArrayList<HashMap<String, String>> scheduleList = new ArrayList<>();
        Cursor cursor = scheduleDB.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do{
                HashMap<String,String> schedule = new HashMap<>();
                schedule.put("schedule_content",cursor.getString(cursor.getColumnIndex("schedule_content")));
                schedule.put("schedule_year",cursor.getString(cursor.getColumnIndex("schedule_year")));
                schedule.put("schedule_month",cursor.getString(cursor.getColumnIndex("schedule_month")));
                schedule.put("schedule_day",cursor.getString(cursor.getColumnIndex("schedule_day")));
                scheduleList.add(schedule);
            }while (cursor.moveToNext());
        }
        cursor.close();
        scheduleDB.close();
        return scheduleList;
    }
}