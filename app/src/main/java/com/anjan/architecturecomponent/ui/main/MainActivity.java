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

import com.anjan.architecturecomponent.R;
import com.anjan.architecturecomponent.entity.MovieEntity;
import com.anjan.architecturecomponent.job_schedulaer.ScheduleJob;
import com.anjan.architecturecomponent.ui.add.AddActivity;

public class MainActivity extends AppCompatActivity{


    FloatingActionButton floatingActionButton;


    RecyclerView recyclerView;
    MoviesListAdapter moviesListAdapter;
    MoviesViewModel moviesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.floatingActionButton4);
        recyclerView = findViewById(R.id.recyclerview);


        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        moviesListAdapter = new MoviesListAdapter(MainActivity.this);


        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(moviesListAdapter);

        moviesViewModel.getMoviesList().observe(this, new Observer<PagedList<MovieEntity>>() {
            @Override
            public void onChanged(@Nullable PagedList<MovieEntity> movieEntities) {
                moviesListAdapter.submitList(movieEntities);
            }
        });

        scheduleJob();

        floatingActionButton.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, AddActivity.class);
            startActivity(i);
        });

    }

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
