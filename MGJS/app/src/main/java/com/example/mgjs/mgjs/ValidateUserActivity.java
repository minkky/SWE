package com.example.mgjs.mgjs;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ValidateUserActivity extends AppCompatActivity {
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validateuser);
        Button btn = (Button)findViewById(R.id.validateuser_btn);
        et = (EditText)findViewById(R.id.validateuser_pw);
        btn.setText("확인");

    }
    public void validateClicked(View view){
        if(et.getText().toString().equals(" ")){
            ;
        }
        else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("PW를 확인해주세요");
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
