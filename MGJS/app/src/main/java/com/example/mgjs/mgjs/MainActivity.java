package com.example.mgjs.mgjs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView schedule, phonebook, note, shortcut_id;
    Button logoutBtn;
    LoginDBHelper logindbhelper;
    SQLiteDatabase logindb;
    String loginid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shortcutmenu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        schedule = (TextView)findViewById(R.id.main_scheduleTextView);
        phonebook = (TextView)findViewById(R.id.main_phonebookTextView);
        note = (TextView)findViewById(R.id.main_noteTextView);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        logindbhelper = new LoginDBHelper(this);
        try {
            logindb = logindbhelper.getWritableDatabase();
        }catch (SQLiteException ex) {
            logindb = logindbhelper.getReadableDatabase();
        }

        Cursor cursor = logindb.rawQuery("select * from login;",null);
        while (cursor.moveToNext()) {
            loginid = cursor.getString(1);
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View shortcurview = navigationView.getHeaderView(0);
        shortcut_id = (TextView)shortcurview.findViewById(R.id.shortcut_id);
        logoutBtn = (Button)shortcurview.findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog  = new AlertDialog.Builder(MainActivity.this);
                dialog.setMessage("Do you want to logout?");
                dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ;
                    }
                });
                dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
                dialog.show();
            }
        });


        if(shortcut_id != null)
            shortcut_id.setText(loginid);

        navigationView.setNavigationItemSelectedListener(this);

    }

    public void onScheduleClicked(View view){
        Intent intent = new Intent(MainActivity.this, ScheduleActivity.class);
        startActivity(intent);
    }

    public void onPhoneBookClicked(View view){
        Intent intent = new Intent(MainActivity.this, PhoneBookActivity.class);
        startActivity(intent);
    }

    public void onNoteClicked(View view){
        Intent intent = new Intent(MainActivity.this, NoteActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void loginidClicked(View view){
        Intent intent = new Intent(MainActivity.this, ValidateUserActivity.class);
        startActivity(intent);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        if (id == R.id.shortcut_schedule) {
            intent = new Intent(MainActivity.this, ScheduleActivity.class);
        } else if (id == R.id.shortcut_phonebook) {
            intent = new Intent(MainActivity.this, PhoneBookActivity.class);
        } else if (id == R.id.shortcut_note) {
            intent = new Intent(MainActivity.this, NoteActivity.class);
        }
        startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
