package com.example.mgjs.mgjs;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_NoteActivity extends AppCompatActivity {

    Button addnote_saveButton, addnote_cancelButton;
    EditText addnote_contentOfNote;
    Intent intent;
    NoteDBHelper noteDBHelper;
    SQLiteDatabase noteDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnote);
        addnote_saveButton = (Button)findViewById(R.id.addnote_savebutton);
        addnote_cancelButton = (Button)findViewById(R.id.addnote_cancelbutton);
        addnote_contentOfNote = (EditText)findViewById(R.id.addnote_contentofnote);
        noteDBHelper = new NoteDBHelper(this);
        try{
            noteDB = noteDBHelper.getWritableDatabase();
        }catch (SQLiteException ex){
            noteDB = noteDBHelper.getReadableDatabase();
        }
    }

    public void saveNote(View view){
        String noteContent = addnote_contentOfNote.getText().toString();
        noteDB.execSQL("INSERT INTO note VALUES(null, '" +noteContent+"');");
        Toast.makeText(getApplicationContext(),"Add note complete.",Toast.LENGTH_SHORT).show();
        addnote_contentOfNote.setText("");
        intent = new Intent(Add_NoteActivity.this, NoteActivity.class);
        startActivity(intent);
    }

    public void cancelNote(View view) {
        Toast.makeText(getApplicationContext(),"Add note is canceled.",Toast.LENGTH_SHORT).show();
        this.finish();
    }
}
