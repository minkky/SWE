package com.example.mgjs.mgjs;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
        note_addButton = (ImageButton)findViewById(R.id.note_addbutton);
        note_listView = getListView();
        showNote();
        note_listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
                final int setposition = position;
                android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(NoteActivity.this);
                alertDialogBuilder.setMessage("Are you sure to delete this note?");
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                deleteNote(setposition);
                                showNote();
                            }
                        });
                alertDialogBuilder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {}
                        });
                AlertDialog deleteAlertDialog = alertDialogBuilder.create();
                deleteAlertDialog.show();

                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        showNote();
    }

    public void onAddNoteClicked(View v){
        intent = new Intent(NoteActivity.this, Add_NoteActivity.class);
        startActivityForResult(intent,1);
    }

    public void deleteNote(int note_Id){
        SQLiteDatabase noteDB = noteDBHelper.getWritableDatabase();

        Cursor cursor = noteDB.rawQuery("SELECT note_id FROM note", null);
        if (ishaveContent(cursor)) {
            int i = cursor.getInt(cursor.getColumnIndex("note_id"));;
                for (int j = 0;j<note_Id;)
                {
                    if (cursor.moveToNext()){
                        i = cursor.getInt(cursor.getColumnIndex("note_id"));
                        j++;
                    }
                }
            noteDB.delete("note","note_id= ?", new String[] {String.valueOf(i)});
            android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(NoteActivity.this);
            alertDialogBuilder.setMessage("Delete Complete!");
        }
       noteDB.close();
    }

    boolean ishaveContent(Cursor cursor){
        return cursor.moveToFirst();
    }

    public void showNote(){
        ArrayList<HashMap<String, String>> noteList;
        noteList = getNoteList();
            ListAdapter noteListAdapter = new SimpleAdapter(NoteActivity.this,noteList,R.layout.note_entry,
                    new String[] {"note_content"},new int[] {R.id.notecontents});
            setListAdapter(noteListAdapter);
        if (noteList.size()==0)
            Toast.makeText(this,"note is not exist!",Toast.LENGTH_SHORT).show();
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
