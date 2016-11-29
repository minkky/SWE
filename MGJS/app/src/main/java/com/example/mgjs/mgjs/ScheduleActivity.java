package com.example.mgjs.mgjs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class ScheduleActivity extends AppCompatActivity {

    ImageButton schedule_addButton;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        schedule_addButton = (ImageButton)findViewById(R.id.schedule_addButton);
    }

   public void onAddScheduleClicked(View v){
       intent = new Intent(ScheduleActivity.this, Add_ScheduleActivity.class);
       startActivity(intent);
    }

    void showSchedule(View v){};
    void callDetailScheduleO(View v){};

}
