package com.anjan.architecturecomponent.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.anjan.architecturecomponent.R;
import com.anjan.architecturecomponent.entity.DirectorEntity;
import com.anjan.architecturecomponent.entity.MovieEntity;
import com.anjan.architecturecomponent.ui.add.AddActivity;

public class MainActivity extends AppCompatActivity{


    FloatingActionButton floatingActionButton;


    RecyclerView recyclerView;
    MoviesListAdapter moviesListAdapter;
    MoviesViewModel moviesViewModel;
    //LinearLayoutManager mLayoutManager;
    LayoutManagerWithSmoothScroller layoutManagerWithSmoothScroller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.floatingActionButton4);
        recyclerView = findViewById(R.id.recyclerview);


        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        moviesListAdapter = new MoviesListAdapter(MainActivity.this);
        moviesListAdapter.registerAdapterDataObserver(new AdapterDataSetObserver());

        //recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //mLayoutManager = new LinearLayoutManager(this);
        layoutManagerWithSmoothScroller = new LayoutManagerWithSmoothScroller(this);
        layoutManagerWithSmoothScroller.setStackFromEnd(true); //set the view to show the last element, the layout direction will remain the same.
        recyclerView.setLayoutManager(layoutManagerWithSmoothScroller);



        recyclerView.setAdapter(moviesListAdapter);

        moviesViewModel.getMoviesList().observe(this, movieEntities -> moviesListAdapter.submitList(movieEntities));



        floatingActionButton.setOnClickListener(v -> {
            /*Intent i = new Intent(MainActivity.this, AddActivity.class);
            startActivity(i);*/

            long milliSec = System.currentTimeMillis();
            String time = Long.toString(milliSec);

            String movie = "titanic";
            String director = "James Kameron";
            DirectorEntity directorEntity = new DirectorEntity(director);

            //check duplicate id can not be inserted
            int directorId = moviesViewModel.findDirector(director);
            if( directorId < 0){
                directorId = (int) moviesViewModel.insertDirector(directorEntity);
            }
            MovieEntity movieEntity = new MovieEntity(movie, directorId, time);
            moviesViewModel.insertMovie(movieEntity);

            recyclerView.smoothScrollToPosition(moviesListAdapter.getItemCount());


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


    class AdapterDataSetObserver extends RecyclerView.AdapterDataObserver {

        @Override
        public void onChanged() {
            Log.e("Observer", "onChanged");
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            Log.e("Observer", "onItemRangeInserted::"+ " positionStart :"+positionStart +":: itemCount : " +itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            Log.e("Observer", "onItemRangeMoved::"+ " fromPosition :"+fromPosition +":: toPosition"+toPosition+" :: itemCount : " +itemCount);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            Log.e("Observer", "onItemRangeRemoved::"+ " positionStart :"+positionStart +":: itemCount : " +itemCount);
        }
    }



}
