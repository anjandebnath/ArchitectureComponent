package com.anjan.architecturecomponent.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.anjan.architecturecomponent.ChatModelObject;
import com.anjan.architecturecomponent.DateObject;
import com.anjan.architecturecomponent.DateParser;
import com.anjan.architecturecomponent.ListObject;
import com.anjan.architecturecomponent.R;
import com.anjan.architecturecomponent.entity.DirectorEntity;
import com.anjan.architecturecomponent.entity.MovieEntity;
import com.anjan.architecturecomponent.ui.add.AddActivity;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity{


    FloatingActionButton floatingActionButton;


    RecyclerView recyclerView;
    MoviesListAdapterNew moviesListAdapter;
    MoviesViewModel moviesViewModel;
    //LinearLayoutManager mLayoutManager;
    LayoutManagerWithSmoothScroller layoutManagerWithSmoothScroller;

    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    List<MovieEntity> movies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.floatingActionButton4);
        recyclerView = findViewById(R.id.recyclerview);


        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        moviesListAdapter = new MoviesListAdapterNew(MainActivity.this);
        moviesListAdapter.registerAdapterDataObserver(new AdapterDataSetObserver());

        //recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //mLayoutManager = new LinearLayoutManager(this);
        layoutManagerWithSmoothScroller = new LayoutManagerWithSmoothScroller(this);
        layoutManagerWithSmoothScroller.setStackFromEnd(true); //set the view to show the last element, the layout direction will remain the same.
        recyclerView.setLayoutManager(layoutManagerWithSmoothScroller);



        recyclerView.setAdapter(moviesListAdapter);


        moviesViewModel.getMovieEntityList().observe(this, new Observer<PagedList<MovieEntity>>() {
            @Override
            public void onChanged(@Nullable PagedList<MovieEntity> movieEntities) {

                moviesListAdapter.submitList(movieEntities);

                /*movies = movieEntities.snapshot();

                moviesViewModel.getMoviesList(movies).observe(MainActivity.this, new Observer<PagedList<ListObject>>() {
                    @Override
                    public void onChanged(@Nullable PagedList<ListObject> model) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                moviesListAdapter.submitList(model);
                            }
                        });

                    }
                });*/

            }
        });




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

            //recyclerView.smoothScrollToPosition(moviesListAdapter.getItemCount());


        });


        /*recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {


                }
            }
        });*/

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                if(dy > 0) //check for scroll down
                {
                    visibleItemCount = layoutManagerWithSmoothScroller.getChildCount();
                    totalItemCount = layoutManagerWithSmoothScroller.getItemCount();
                    pastVisiblesItems = layoutManagerWithSmoothScroller.findFirstVisibleItemPosition();

                    if (loading)
                    {
                        if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount)
                        {
                            loading = false;
                            Log.v("...", "Last Item Wow !");
                            //Do pagination.. i.e. fetch new data
                            //Toast.makeText(MainActivity.this, "Last", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
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

        // Scroll to bottom on new messages
        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            Log.e("Observer", "onItemRangeInserted::"+ " positionStart :"+positionStart +":: itemCount : " +itemCount);
            layoutManagerWithSmoothScroller.smoothScrollToPosition(recyclerView, null, moviesListAdapter.getItemCount());
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
