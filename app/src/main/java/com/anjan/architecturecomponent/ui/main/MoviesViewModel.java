package com.anjan.architecturecomponent.ui.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;


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
}