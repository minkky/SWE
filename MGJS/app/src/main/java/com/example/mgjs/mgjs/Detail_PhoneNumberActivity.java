package com.example.mgjs.mgjs;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class Detail_PhoneNumberActivity extends AppCompatActivity {
    //private GoogleApiClient client;

    PhoneBookDBHelper helper;
    SQLiteDatabase db;

    int position;

    final ArrayList<String> mDatas = new ArrayList<String>();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailphonenumber);
        helper = new PhoneBookDBHelper(this);
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM phonebook order by name asc", null);
        String str = "";

        while (cursor.moveToNext()) {
            str = "NAME :\t\t\t\t\t\t\t\t\t\t"+ cursor.getString(1) + "\n" + "PHONE : \t\t\t\t\t\t\t" + cursor.getString(2);
            mDatas.add(str);
        }

        adapter = new ArrayAdapter(getApplicationContext(), R.layout.activity_phonebook_items, mDatas);
        TextView detail = (TextView) findViewById(R.id.textView4);
        Intent intent = getIntent();
        detail.setText(intent.getStringExtra("detail"));
        position = getIntent().getExtras().getInt("position");
    }

    public void deletePhoneNumber(View v) {
        Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();

        helper = new PhoneBookDBHelper(this);
        db = helper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM phonebook order by name asc", null);

        cursor.moveToFirst();

        int i=0;
        while(i < position){
            cursor.moveToNext();
            i++;
        }

        int id = cursor.getInt(0);

        String str= "DELETE FROM phonebook WHERE _id=" +id+";";
        db.execSQL(str);

        adapter.notifyDataSetChanged();
        Intent intent = new Intent(Detail_PhoneNumberActivity.this, PhoneBookActivity.class);
        startActivity(intent);
    }

    public void before_onclick(View v) {
        Intent intent = new Intent(this, PhoneBookActivity.class);
        startActivity(intent);
    }

}
