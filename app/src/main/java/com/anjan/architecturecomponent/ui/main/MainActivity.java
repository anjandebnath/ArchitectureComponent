package com.anjan.architecturecomponent.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.anjan.architecturecomponent.ui.add.AddActivity;
import com.anjan.architecturecomponent.R;
import com.anjan.architecturecomponent.entity.MovieEntity;

import java.util.List;

public class MainActivity extends AppCompatActivity{


    FloatingActionButton floatingActionButton;


    RecyclerView recyclerView;
    MoviesListAdapter moviesListAdapter;
    MoviesViewModel moviesViewModel;
    MainFactory mainFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.floatingActionButton4);
        recyclerView = findViewById(R.id.recyclerview);

        mainFactory = new MainFactory(this, MainActivity.this.getApplication());

        moviesViewModel = ViewModelProviders.of(this, mainFactory).get(MoviesViewModel.class);
        moviesListAdapter = new MoviesListAdapter(MainActivity.this);


        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(moviesListAdapter);


        /*moviesViewModel.getMoviesList().observe(this, new Observer<List<MovieEntity>>() {
            @Override
            public void onChanged(@Nullable List<MovieEntity> movies) {
                moviesListAdapter.setMovieList(movies);
            }
        });*/

        moviesViewModel.getMediatorMoviesList().observe(this, new Observer<List<MovieEntity>>() {
            @Override
            public void onChanged(@Nullable List<MovieEntity> movieEntities) {
                moviesListAdapter.setMovieList(movieEntities);
            }
        });

        floatingActionButton.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, AddActivity.class);
            startActivity(i);
        });

        subscribe();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void subscribe() {
        final Observer<Long> elapsedTimeObserver = new Observer<Long>() {
            @Override
            public void onChanged(@Nullable final Long aLong) {
                String newText = "Elapsed time" +aLong;
                ((TextView) findViewById(R.id.timer_textview)).setText(newText);
                Log.d("MainActivity", "Updating timer");
            }
        };

        moviesViewModel.getElapsedTime().observe(this, elapsedTimeObserver);
    }



}
