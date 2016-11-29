package com.example.mgjs.mgjs;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class PhoneBookActivity extends Activity {

    PhoneBookDBHelper helper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonebook);

        helper = new PhoneBookDBHelper(this);
        db = helper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM phonebook", null);
        String str = "";
        ArrayList<String> mDatas = new ArrayList<String>();

        while (cursor.moveToNext()) {
            //str = "[NAME]\t\t\t\t\t\t\t\t\t\t"+ cursor.getString(1) + "\n" + "[PHONE]\t\t" + cursor.getString(2);
            str="[NAME]\t\t\t\t\t\t\t\t\t\t"+ cursor.getString(1);
            mDatas.add(str);
        }
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), R.layout.activity_phonebook_items, mDatas);
        ListView listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(adapter);

        db.close();

        }


    public void add_onClick(View v) {
        Intent intent = new Intent(this, Add_PhoneNumberActivity.class);
        startActivity(intent);
    }


    }

