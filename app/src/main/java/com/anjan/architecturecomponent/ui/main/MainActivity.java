package com.anjan.architecturecomponent.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.anjan.architecturecomponent.R;
import com.anjan.architecturecomponent.entity.MovieEntity;
import com.anjan.architecturecomponent.job_schedulaer.ScheduleJob;
import com.anjan.architecturecomponent.ui.add.AddActivity;

public class MainActivity extends AppCompatActivity{


    FloatingActionButton floatingActionButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.floatingActionButton4);
        floatingActionButton.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, AddActivity.class);
            startActivity(i);
        });



    }

    /*public void onClicked(View view) {
        String text = view.getId() == R.id.view1 ? "Background" : "Foreground";
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }*/


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void scheduleJob(){
        SharedPreferences preferences = PreferenceManager.
                getDefaultSharedPreferences(this);

        if(!preferences.getBoolean("firstRunComplete", false)){
            //schedule the job only once.
            new ScheduleJob(this).scheduleJobFirebaseToRoomDataUpdate();

            //update shared preference
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("firstRunComplete", true);
            editor.commit();
        }
    }



}
