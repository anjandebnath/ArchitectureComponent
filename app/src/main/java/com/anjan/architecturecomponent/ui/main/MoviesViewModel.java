package com.anjan.architecturecomponent.ui.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;
import android.util.Log;


import com.anjan.architecturecomponent.MoviesDatabase;
import com.anjan.architecturecomponent.dao.MovieDao;
import com.anjan.architecturecomponent.entity.MovieEntity;


import java.util.List;

/**
 * Created by Anjan Debnath on 8/17/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */

public class MoviesViewModel extends AndroidViewModel {


    private MovieDao movieDao;
    private static final String TAG = "FETCH_DATA";
    //private LiveData<List<MovieEntity>> moviesLiveData;

    private LiveData<PagedList<MovieEntity>> moviesLiveData;

    public MoviesViewModel(@NonNull Application application) {
        super(application);
        movieDao = MoviesDatabase.getDatabase(application).movieDao();
        moviesLiveData = new LivePagedListBuilder<>(movieDao.getAllMovies(), 10).build();
    }

    public LiveData<PagedList<MovieEntity>> getMoviesList() {
        return moviesLiveData;
    }

    public void insert(MovieEntity... movies) {
        movieDao.insert(movies);
    }

    public void update(MovieEntity movie) {
        movieDao.update(movie);
    }

    public void deleteAll() {
        movieDao.deleteAll();
    }


    /*public void fetchDataFromServer(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("movies")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }*/
}