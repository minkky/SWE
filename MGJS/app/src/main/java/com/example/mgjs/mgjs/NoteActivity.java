package com.example.mgjs.mgjs;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class NoteActivity extends ListActivity {

    ImageButton note_addButton;
    Intent intent;
    NoteDBHelper noteDBHelper;

    ListView note_listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        noteDBHelper = new NoteDBHelper(this);
        note_addButton = (ImageButton)findViewById(R.id.note_addButton);
        note_listView = getListView();
        showNote();


        note_listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
                deleteNote(position);
                return true;
            }
        });
    }

    public void onAddNoteClicked(View v){
        intent = new Intent(NoteActivity.this, Add_NoteActivity.class);
        startActivity(intent);
    }

    public void deleteNote(int note_Id){
        SQLiteDatabase noteDB = noteDBHelper.getWritableDatabase();
        noteDB.delete("note","note_id= ?", new String[] {String.valueOf(note_Id)});
        noteDB.close();

    }

    public void showNote(){
        ArrayList<HashMap<String, String>> noteList;
        noteList = getNoteList();
        if (noteList.size()!=0){
            ListAdapter noteListAdapter = new SimpleAdapter(NoteActivity.this,noteList,R.layout.note_entry,new String[] {"id","content"},new int[] {R.id.noteNumber,R.id.noteContents});
            setListAdapter(noteListAdapter);
        }else {
            Toast.makeText(this,"note is not exist!",Toast.LENGTH_SHORT).show();
        }
            }

    public ArrayList<HashMap<String,String>> getNoteList(){
        SQLiteDatabase noteDB = noteDBHelper.getReadableDatabase();
        String selectQuery = "SELECT note_id , note_content FROM note";
        ArrayList<HashMap<String,String>> noteList = new ArrayList<>();
        Cursor cursor = noteDB.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do{
                HashMap<String,String> note = new HashMap<>();
                note.put("note_id",cursor.getString(cursor.getColumnIndex("note_id")));
                note.put("note_content",cursor.getString(cursor.getColumnIndex("note_content")));
                noteList.add(note);
            }while (cursor.moveToNext());
        }
        cursor.close();
        noteDB.close();
        return noteList;
    }

}
