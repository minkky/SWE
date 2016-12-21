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
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText id, pw;
    String loginid, loginpw;
    String savedId, savedPW;
    LoginDBHelper logindbhelper;
    SQLiteDatabase logindb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logindbhelper = new LoginDBHelper(this);
        try {
            logindb = logindbhelper.getWritableDatabase();
        }catch (SQLiteException ex) {
            logindb = logindbhelper.getReadableDatabase();
        }

        Cursor cursor = logindb.rawQuery("select * from Login;",null);
        int count = cursor.getCount();

        if(count == 0)
            logindb.execSQL("INSERT INTO Login VALUES(null,'mgjs','mgjs');");

        cursor = logindb.rawQuery("select * from Login;",null);
        if(cursor.moveToLast()) {
            loginid = cursor.getString(1);
            loginpw = cursor.getString(2);
        }

        id = (EditText)findViewById(R.id.login_id);
        pw = (EditText)findViewById(R.id.login_pw);

    }

    public String getSavedId(){
        savedId = id.getText().toString();
        return savedId;
    }

    public String getSavedPW(){
        savedPW = pw.getText().toString();
        return  savedPW;
    }

    public void onLoginBtnClicked(View view){
        if(getSavedId().equals(loginid) && getSavedPW().equals(loginpw)){
            Toast.makeText(getApplicationContext(),"로그인 성공",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("ID/PW를 확인해주세요");
            alertDialogBuilder.setPositiveButton("확인",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            ;
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

}
