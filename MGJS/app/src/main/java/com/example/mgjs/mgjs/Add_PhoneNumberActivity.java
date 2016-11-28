package com.example.mgjs.mgjs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Add_PhoneNumberActivity extends AppCompatActivity {

    EditText nameEdit;
    EditText ageEdit;
    //TextView result;

    DBHelper dbHelper;

    final static String dbName = "Person.db";
    final static int dbVersion = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addphonenumber);

        nameEdit = (EditText)findViewById(R.id.name);
        ageEdit = (EditText)findViewById(R.id.number);

        dbHelper = new DBHelper(this, dbName, null, dbVersion);
    }
    public void mOnClick(View v){
        SQLiteDatabase db;
        String sql;

        String name = nameEdit.getText().toString();
        String age = ageEdit.getText().toString();

        db = dbHelper.getWritableDatabase();
        sql = String.format("INSERT INTO person VALUES(NULL, '%s', '%s');", name, age);
        db.execSQL(sql);

        dbHelper.close();
    }

    class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE person (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " name TEXT, age INTEGER);");
            // result.append("\nperson 테이블 생성 완료.");
        }

        //DB 업그레이드 필요 시 호출. (version값에 따라 반응)
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS person");
            onCreate(db);
        }


    }
}
