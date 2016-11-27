package com.example.mgjs.mgjs;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChangeUserActivity extends AppCompatActivity {
    TextView id, pw, checkpw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeuser);
        id = (TextView)findViewById(R.id.changeuser_id);
        pw = (TextView)findViewById(R.id.changeuser_pw);
        checkpw = (TextView)findViewById(R.id.changeuser_pwcheck);
        /*db에서 불러온 값들을 id, pw, checkpw에 각각 저장해야함.
        * id.setText(); pw.setText(); checkpw.setText();*/
    }

    public void changeuserClicked(View view){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        /*변경된 값을 불러오는지 확인하기*/
        String str_pw = pw.getText().toString();
        String str_checkpw = checkpw.getText().toString();

        if(str_pw.equals(str_checkpw)) {
            /*db에 저장하는 부분이 필요함*/
            alertDialogBuilder.setMessage("정보가 변경되었습니다!");
            alertDialogBuilder.setPositiveButton("확인",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            Intent intent = new Intent(ChangeUserActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    });
        }
        else{
            alertDialogBuilder.setMessage("PW를 확인하세요!");
            alertDialogBuilder.setPositiveButton("확인",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            ;
                        }
                    });
        }
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void nochangeuserClicked(View view){
        Intent intent = new Intent(ChangeUserActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
