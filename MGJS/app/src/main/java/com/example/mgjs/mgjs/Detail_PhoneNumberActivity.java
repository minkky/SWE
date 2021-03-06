package com.example.mgjs.mgjs;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Detail_PhoneNumberActivity extends AppCompatActivity {
    //private GoogleApiClient client;

    PhoneBookDBHelper helper;
    SQLiteDatabase db;
    Intent intent;
    int position;

    final ArrayList<String> mDatas = new ArrayList<String>();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailphonenumber);
        helper = new PhoneBookDBHelper(this);
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM phonebook order by phone_name asc", null);
        String str = "";

        while (cursor.moveToNext()) {
            str=makeStrAddingInmData(cursor.getString(1),cursor.getString(2));
            mDatas.add(str);
        }

        adapter = new ArrayAdapter(getApplicationContext(), R.layout.activity_phonebook_items, mDatas);
        TextView detail = (TextView) findViewById(R.id.textView4);
        Intent intent = getIntent();
        detail.setText(intent.getStringExtra("detail"));
        position = getIntent().getExtras().getInt("position");

    }

    public String makeStrAddingInmData(String nameString , String phonenumberString){
        String str;

        str= "NAME :\t\t\t\t\t\t\t\t\t\t\t"+ nameString + "\n" + "PHONE : \t\t\t\t\t\t\t\t" + phonenumberString;

        return str;
    }

    public void deletePhoneNumber(View v) {
        //Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        helper = new PhoneBookDBHelper(this);
        db = helper.getWritableDatabase();

        alertDialogBuilder.setMessage("Are you sure to delete?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Cursor cursor = db.rawQuery("SELECT * FROM phonebook order by phone_name asc", null);

                        cursor.moveToFirst();
                        int i=0;
                        while(i < position){
                            cursor.moveToNext();
                            i++;
                        }

                        int id = cursor.getInt(0);
                        String str = makeDeleteSQLquery(id);
                        db.execSQL(str);
                        adapter.notifyDataSetChanged();
                        intent = new Intent();
                        intent.putExtra("data","data");
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                });


        //finish();

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

    public String makeDeleteSQLquery(int id){
        String str;
        str = "DELETE FROM phonebook WHERE phone_id=" +id+";";
        return str;
    }

    public void before_onclick(View v) {
        this.finish();
    }

}
