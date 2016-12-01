package com.example.mgjs.mgjs;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

//import android.widget.AdapterView.

public class PhoneBookActivity extends Activity {

    PhoneBookDBHelper helper;
    SQLiteDatabase db;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonebook);

        helper = new PhoneBookDBHelper(this);
        db = helper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM phonebook", null);
        String str = "";
        final ArrayList<String> mDatas = new ArrayList<String>();

        while (cursor.moveToNext()) {
            str = "NAME :\t\t\t\t\t\t\t\t\t\t"+ cursor.getString(1) + "\n" + "PHONE : \t\t\t\t\t\t\t" + cursor.getString(2);
            //str = "[NAME]\t\t\t\t\t\t\t\t\t\t" + cursor.getString(1);
            Toast.makeText(PhoneBookActivity.this, cursor.getString(1), Toast.LENGTH_SHORT).show();
            mDatas.add(str);
        }




        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), R.layout.activity_phonebook_items, mDatas);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               // Toast.makeText(PhoneBookActivity.this, "aaa", Toast.LENGTH_SHORT).show();

               // Toast.makeText(PhoneBookActivity.this, position, Toast.LENGTH_SHORT).show();

                //Toast.makeText(getApplicationContext(), ""+ position, Toast.LENGTH_SHORT).show();

                String data = ""+position;
                int data2 = position+0;

                Toast.makeText(getApplicationContext(), ""+data, Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(), data2, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(PhoneBookActivity.this, Detail_PhoneNumberActivity.class);

                intent.putExtra("detail", mDatas.get(position));
               // intent.putExtra("phonebook", mDatas.get(position));


                intent.putExtra("position", data);


                startActivity(intent);
            }


            //Toast.makeText(this, mDatas.get(position)+"", Toast.LENGTH_LONG).show();

        });

    }
    public void add_onclick(View v) {
        Intent intent = new Intent(PhoneBookActivity.this, Add_PhoneNumberActivity.class);
        startActivity(intent);
    }


}

