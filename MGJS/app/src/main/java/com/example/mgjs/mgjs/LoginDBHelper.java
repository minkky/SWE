package com.example.mgjs.mgjs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LoginDBHelper  extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "login.db";
    private static final int DATABASE_VERSION = 1;

    public LoginDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Login ( _id INTEGER PRIMARY KEY" + " AUTOINCREMENT, login_id TEXT, login_password TEXT);");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Login");
        onCreate(db);
    }
}