package com.example.mgjs.mgjs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class NoteActivity extends AppCompatActivity {

    ImageButton note_addButton;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        note_addButton = (ImageButton)findViewById(R.id.note_addButton);
    }

    public void note_add(View view){
        intent = new Intent(NoteActivity.this, Add_NoteActivity.class);
        startActivity(intent);
    }
}
