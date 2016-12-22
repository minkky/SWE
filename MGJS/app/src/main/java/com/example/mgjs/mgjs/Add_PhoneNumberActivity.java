package com.example.mgjs.mgjs;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Add_PhoneNumberActivity extends AppCompatActivity {
    EditText nameEdit;
    EditText phoneEdit;
    PhoneBookDBHelper helper;
    SQLiteDatabase db;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addphonenumber);
        intent = getIntent();

        helper = new PhoneBookDBHelper(this);
        try {
            db = helper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = helper.getReadableDatabase();
        }

        nameEdit = (EditText)findViewById(R.id.name);
        phoneEdit = (EditText)findViewById(R.id.phone_number);
    }

    public void savePhoneNumber(View v){
        String sql;
        String name = nameEdit.getText().toString();
        String phone_number = phoneEdit.getText().toString();

        sql = String.format("INSERT INTO phonebook VALUES(NULL, '%s', '%s');", name, phone_number);
        db.execSQL(sql);

        Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
        nameEdit.setText("");
        phoneEdit.setText("");
        db.close();

        intent = new Intent();
        intent.putExtra("data","data");
        setResult(RESULT_OK,intent);
        finish();
    }

    public void cancelPhoneNumber(View v){
        this.finish();
    }
}
