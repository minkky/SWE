package com.example.mgjs.mgjs;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class PhoneBookActivity extends ListActivity {
    PhoneBookDBHelper helper;
    SQLiteDatabase db;
    Intent intent;
    ListView listView;
    ArrayList<String> mDatas = new ArrayList<String>();
   // private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonebook);

        helper = new PhoneBookDBHelper(this);
        db = helper.getWritableDatabase();

         listView = getListView();
        showPhoneBook();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PhoneBookActivity.this, Detail_PhoneNumberActivity.class);
                intent.putExtra("detail", mDatas.get(position));
                intent.putExtra("position", position);
                startActivityForResult(intent,1);
            }
        });
    }

    public String makeStrAddingInmData(String nameString , String phonenumberString){
        String str;

        str= "NAME :\t\t\t\t\t\t\t\t\t\t\t"+ nameString + "\n" + "PHONE : \t\t\t\t\t\t\t\t" + phonenumberString;

        return str;
    }

    protected void showPhoneBook(){
        mDatas.clear();
        Cursor cursor = db.rawQuery("SELECT * FROM phonebook order by phone_name asc", null);
        String str = "";

        while (cursor.moveToNext()) {
            str=makeStrAddingInmData(cursor.getString(1),cursor.getString(2));
            mDatas.add(str);
        }

        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), R.layout.activity_phonebook_items, mDatas);
        listView.setAdapter(adapter);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        showPhoneBook();
    }

    public void onAddPhoneNumberClicked(View v) {
        intent = new Intent(PhoneBookActivity.this, Add_PhoneNumberActivity.class);
        startActivityForResult(intent,1);
    }
}

