package com.anjan.architecturecomponent.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.anjan.architecturecomponent.R;
import com.anjan.architecturecomponent.ui.add.AddActivity;

public class MainActivity extends AppCompatActivity{


    FloatingActionButton floatingActionButton;


    RecyclerView recyclerView;
    MoviesListAdapter moviesListAdapter;
    MoviesViewModel moviesViewModel;
    LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.floatingActionButton4);
        recyclerView = findViewById(R.id.recyclerview);
        mLayoutManager = new LinearLayoutManager(this);

        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        moviesListAdapter = new MoviesListAdapter(MainActivity.this);


        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(moviesListAdapter);

        moviesViewModel.getMoviesList().observe(this, movieEntities -> moviesListAdapter.submitList(movieEntities));



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



}
