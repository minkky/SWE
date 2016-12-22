package com.example.mgjs.mgjs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 최정민 on 2016-11-28.
 */

public class ScheduleDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "schedule.db";
    private static final int DATABASE_VERSION = 1;

    public ScheduleDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Schedule ( schedule_id INTEGER PRIMARY KEY" + " AUTOINCREMENT, schedule_content TEXT, schedule_year INTEGER, schedule_month INTEGER, schedule_date INTEGER);");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Schedule");
        onCreate(db);
    }
}
