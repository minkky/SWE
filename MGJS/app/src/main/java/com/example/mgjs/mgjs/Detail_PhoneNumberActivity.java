package com.example.mgjs.mgjs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;

public class Detail_PhoneNumberActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

   // PhoneBookDBHelper helper;
   // SQLiteDatabase db;

    int position;

           //= new ArrayAdapter(getApplicationContext(), R.layout.activity_phonebook_items, mDatas);

   // ListView listView = (ListView) findViewById(R.id.listview);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailphonenumber);

        /*helper = new PhoneBookDBHelper(this);
        db = helper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM phonebook", null);
        String str = "";

        while (cursor.moveToNext()) {
            str = "NAME :\t\t\t\t\t\t\t\t\t\t"+ cursor.getString(1) + "\n" + "PHONE : \t\t\t\t\t\t\t" + cursor.getString(2);
            //str = "[NAME]\t\t\t\t\t\t\t\t\t\t" + cursor.getString(1);
            //Toast.makeText(PhoneBookActivity.this, cursor.getString(2), Toast.LENGTH_SHORT).show();
            mDatas.add(str);
        }

        adapter = new ArrayAdapter(getApplicationContext(), R.layout.activity_phonebook_items, mDatas);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);*/



        TextView detail = (TextView) findViewById(R.id.textView4);
        //TextView name = (TextView) findViewById(R.id.textView4);
       // TextView phone = (TextView) findViewById(R.id.textView5);

        Intent intent = getIntent();
       // name.setText(intent.getStringExtra("name"));
       // phone.setText(intent.getStringExtra("phonenumber"));
        detail.setText(intent.getStringExtra("detail"));

       // detail.setText(intent.getStringExtra("data"));
       //  position = intent.getIntExtra("position",1);
       // int position = intent.getIntExtra("position",1);
        // position = intent.getIntExtra("position",1);
        int i = getIntent().getExtras().getInt("position");



        Toast.makeText(Detail_PhoneNumberActivity.this, ""+i, Toast.LENGTH_SHORT).show();


    }

    public void delete_onclick(View v) {

        Toast.makeText(Detail_PhoneNumberActivity.this, position, Toast.LENGTH_SHORT).show();


     //  mDatas.remove(position);

       // adapter.notifyDataSetChanged();

    }


    public void before_onclick(View v) {
        Intent intent = new Intent(this, PhoneBookActivity.class);
        startActivity(intent);
    }


}
