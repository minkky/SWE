package com.example.mgjs.mgjs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Add_NoteActivity extends AppCompatActivity {

    Button addnote_saveButton, addnote_cancelButton;
    EditText addnote_contentOfNote;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnote);
        addnote_saveButton = (Button)findViewById(R.id.addnote_saveButton);
        addnote_cancelButton = (Button)findViewById(R.id.addnote_cancelButton);
        addnote_contentOfNote = (EditText)findViewById(R.id.addnote_contentOfNote);
    }

    public void addnote_save(View view){
        //디비 저장
    }
    public void addnote_cancel(View view){
        addnote_cancelButton.setText("");
        intent = new Intent(Add_NoteActivity.this, NoteActivity.class);
        startActivity(intent);
    }


}
