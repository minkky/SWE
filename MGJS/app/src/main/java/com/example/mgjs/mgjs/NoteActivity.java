package com.example.mgjs.mgjs;

import android.app.ListActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class NoteActivity extends ListActivity {

    ImageButton note_addButton;
    Intent intent;
    NoteDBHelper noteDbHelper;
    SQLiteDatabase noteDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        note_addButton = (ImageButton)findViewById(R.id.note_addButton);
    }

    public void onAddNoteClicked(View v){
        intent = new Intent(NoteActivity.this, Add_NoteActivity.class);
        startActivity(intent);
    }

    public void deleteNote(){};
    public void showNote(View v){

    };

}
