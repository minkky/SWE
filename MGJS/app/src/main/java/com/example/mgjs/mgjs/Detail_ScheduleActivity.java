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
import android.widget.TextView;

public class Detail_ScheduleActivity extends AppCompatActivity {

    ScheduleDBHelper sDBhelper;
    SQLiteDatabase scheduledb;
    //ScheduleActivity에서 schedule_id값을 넘겨주어야 함!!

    TextView tv_year, tv_month, tv_day, tv_content;
    int schedule_id, str_year, str_month, str_day;
    String str_content;
    View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailschedule);

        sDBhelper = new ScheduleDBHelper(this);
        try {
            scheduledb = sDBhelper.getWritableDatabase();
        }catch (SQLiteException ex){
            scheduledb = sDBhelper.getReadableDatabase();
        }

        Intent intent = getIntent();
        schedule_id = intent.getExtras().getInt("id");
        tv_year = (TextView)findViewById(R.id.schedule_year);
        tv_month = (TextView)findViewById(R.id.schedule_month);
        tv_day = (TextView)findViewById(R.id.schedule_day);
        tv_content = (TextView)findViewById(R.id.schedule_content);

        showDetailSchedule();// 여기 파라미터 추가
    }

    public void showDetailSchedule(){
        Cursor cursor = scheduledb.rawQuery("SELECT * FROM Schedule where schedule_id = " + schedule_id, null);
        while (cursor.moveToNext()) {
            str_content = cursor.getString(1);
            str_year = cursor.getInt(2);
            str_month = cursor.getInt(3);
            str_day = cursor.getInt(4);
        }
        tv_content.setText(str_content);
        tv_year.setText(str_year + " / ");
        tv_month.setText(str_month + " / ");
        tv_day.setText(str_day + " / ");
    }

    public void deleteSchedule(View v){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure to delete?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        String deleteSql = "DELETE FROM Schedule WHERE schedule_id = " + schedule_id+";";
                        scheduledb.execSQL(deleteSql);
                        Cursor cursor = scheduledb.rawQuery("SELECT schedule_id FROM Schedule", null);
                        int i =1;
                        while (cursor.moveToNext()){
                            scheduledb.execSQL("UPDATE Schedule SET schedule_id="+i+";");
                            i++;
                        }
                        Intent intent = new Intent(Detail_ScheduleActivity.this, ScheduleActivity.class);
                        startActivity(intent);
                    }
                });
        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(Detail_ScheduleActivity.this, ScheduleActivity.class);
                        startActivity(intent);
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
