package com.example.mgjs.mgjs;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
        intent = getIntent();

        try{
            noteDB = noteDBHelper.getWritableDatabase();
        }catch (SQLiteException ex){
            noteDB = noteDBHelper.getReadableDatabase();
        }
    }

    public void saveNote(View view){
        String noteContent = addnote_contentOfNote.getText().toString();
        if (noteContent ==null){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Please write note content!");
            alertDialogBuilder.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

        else if (confirmGetRightNoteContent(noteContent, addnote_contentOfNote.getText().toString())) {
            noteDB.execSQL("INSERT INTO note VALUES(null, '" + noteContent + "');");
            Toast.makeText(getApplicationContext(), "Add note complete.", Toast.LENGTH_SHORT).show();
            addnote_contentOfNote.setText("");
            if (isintentNotNull(intent)) {
                intent = new Intent();
                intent.putExtra("data", "data");

                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }

    public boolean isintentNotNull(Intent intent){
        return intent !=null;
    }

    public boolean confirmGetRightNoteContent(String firstString,String secondString){
        return firstString.equals(secondString);
    }
    public void cancelNote(View view) {
        Toast.makeText(getApplicationContext(),"Add note is canceled.",Toast.LENGTH_SHORT).show();
        this.finish();
    }
}