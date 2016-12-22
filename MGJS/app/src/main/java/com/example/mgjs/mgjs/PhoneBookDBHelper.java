package com.example.mgjs.mgjs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sejin on 2016-11-29.
 */

public class PhoneBookDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "phonebook.db";
    private static final int DATABASE_VERSION = 1;

    public PhoneBookDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE phonebook (phone_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " phone_name TEXT, phone_number TEXT);");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS phonebook");
        onCreate(db);
    }

}
