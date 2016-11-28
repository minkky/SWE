package com.example.mgjs.mgjs;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ValidateUserActivity extends AppCompatActivity {
    EditText et;
    LoginDBHelper logindbhelper;
    SQLiteDatabase logindb;
    String loginpw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validateuser);
        Button btn = (Button)findViewById(R.id.validateuser_btn);
        et = (EditText)findViewById(R.id.validateuser_pw);
        btn.setText("확인");

        logindbhelper = new LoginDBHelper(this);
        try {
            logindb = logindbhelper.getWritableDatabase();
        }catch (SQLiteException ex) {
            logindb = logindbhelper.getReadableDatabase();
        }

        Cursor cursor = logindb.rawQuery("select * from login;",null);
        while (cursor.moveToNext()) {
            loginpw = cursor.getString(2);
        }


    }

    public boolean matchPassword(){
        if(et.getText().toString().equals(loginpw))
            return true;
        else return false;
        }
    public void onPasswordCheckClicked(View view){
        if(matchPassword()){
            Intent intent = new Intent(ValidateUserActivity.this, ChangeUserActivity.class);
            startActivity(intent);
        }
        else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("PW를 확인해주세요");
            alertDialogBuilder.setPositiveButton("확인",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            et.setText("");
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }
}
