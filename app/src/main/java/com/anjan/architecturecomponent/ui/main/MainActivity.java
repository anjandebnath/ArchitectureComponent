package com.anjan.architecturecomponent.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.anjan.architecturecomponent.ListObject;
import com.anjan.architecturecomponent.R;
import com.anjan.architecturecomponent.entity.DirectorEntity;
import com.anjan.architecturecomponent.entity.MovieEntity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity{


    FloatingActionButton floatingActionButton;


    RecyclerView recyclerView;
    MoviesListAdapterNew moviesListAdapterNew;
    MoviesListAdapter moviesListAdapter;
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

       /* moviesListAdapterNew = new MoviesListAdapterNew(MainActivity.this, moviesViewModel);
        moviesListAdapterNew.registerAdapterDataObserver(new AdapterDataSetObserver());*/

        moviesListAdapter = new MoviesListAdapter(MainActivity.this, moviesViewModel);
        moviesListAdapter.registerAdapterDataObserver(new AdapterDataSetObserver());

        //recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //mLayoutManager = new LinearLayoutManager(this);
        layoutManagerWithSmoothScroller = new LayoutManagerWithSmoothScroller(this);
        layoutManagerWithSmoothScroller.setStackFromEnd(true); //set the view to show the last element, the layout direction will remain the same.
        recyclerView.setLayoutManager(layoutManagerWithSmoothScroller);



        //recyclerView.setAdapter(moviesListAdapterNew);
        recyclerView.setAdapter(moviesListAdapter);



        moviesViewModel.getMoviesEntityList().observe(this, new Observer<List<MovieEntity>>() {
            @Override
            public void onChanged(@Nullable List<MovieEntity> movieEntities) {
                moviesViewModel.getMoviesList(movieEntities).observe(MainActivity.this, new Observer<PagedList<ListObject>>() {
                    @Override
                    public void onChanged(@Nullable PagedList<ListObject> listObjects) {
                        moviesListAdapter.submitList(listObjects);

                    }
                });
            }
        });



        //moviesViewModel.getMovieEntityList().observe(this, movieEntities -> moviesListAdapterNew.submitList(movieEntities));

        /*moviesViewModel.getMovieEntityList().observe(this, movieEntities -> {

            movies = movieEntities.snapshot();

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
            });

        });*/




        floatingActionButton.setOnClickListener(v -> {
            /*Intent i = new Intent(MainActivity.this, AddActivity.class);
            startActivity(i);*/

            long milliSec = System.currentTimeMillis();
            Date date = new Date(milliSec);
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            String dateFormatted = formatter.format(date);
//            String time = Long.toString(milliSec);

            String movie = "titanic";
            String director = "James Kameron";
            DirectorEntity directorEntity = new DirectorEntity(director);

            //check duplicate id can not be inserted
            int directorId = moviesViewModel.findDirector(director);
            if( directorId < 0){
                directorId = (int) moviesViewModel.insertDirector(directorEntity);
            }
            MovieEntity movieEntity = new MovieEntity(movie, directorId, dateFormatted);
            moviesViewModel.insertMovie(movieEntity);

            //recyclerView.smoothScrollToPosition(moviesListAdapterNew.getItemCount());


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
                            //Log.v("...", "Last Item Wow !");
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
            //Log.e("Observer", "onItemRangeInserted::"+ " positionStart :"+positionStart +":: itemCount : " +itemCount);
            layoutManagerWithSmoothScroller.smoothScrollToPosition(recyclerView, null, moviesListAdapter.getItemCount());
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            //Log.e("Observer", "onItemRangeMoved::"+ " fromPosition :"+fromPosition +":: toPosition"+toPosition+" :: itemCount : " +itemCount);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            //Log.e("Observer", "onItemRangeRemoved::"+ " positionStart :"+positionStart +":: itemCount : " +itemCount);
        }
    }



}
