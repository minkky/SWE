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

    Intent intent;
    TextView tv_date, tv_content;
    int schedule_id, str_year, str_month, str_day;
    String str_content, str_date;
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

        intent = getIntent();
        schedule_id = intent.getExtras().getInt("id");
        tv_date = (TextView)findViewById(R.id.schedule_date);
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
        str_date = " " + str_year + "  /  " + str_month + "  /  " + str_day;

        tv_content.setText(str_content);
        tv_date.setText(str_date);
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
                        scheduledb.close();

                        intent = new Intent();
                        intent.putExtra("data","data");
                        setResult(RESULT_OK,intent);
                        finish();

                    }
                });
        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
