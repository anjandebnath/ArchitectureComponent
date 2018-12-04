package com.anjan.architecturecomponent.ui.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;


import com.anjan.architecturecomponent.MoviesDatabase;
import com.anjan.architecturecomponent.dao.DirectorDao;
import com.anjan.architecturecomponent.dao.MovieDao;
import com.anjan.architecturecomponent.entity.DirectorEntity;
import com.anjan.architecturecomponent.entity.MovieEntity;

import java.util.List;

/**
 * Created by Anjan Debnath on 8/17/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */

public class MoviesViewModel extends AndroidViewModel {

    private static final int INITIAL_LOAD_KEY = 0;
    private static final int PAGE_SIZE = 20;
    private static final int PREFETCH_DISTANCE = 5;

    private MovieDao movieDao;
    private DirectorDao directorDao;
    //private LiveData<List<MovieEntity>> moviesLiveData;

    private LiveData<PagedList<MovieEntity>> moviesLiveData;

    public MoviesViewModel(@NonNull Application application) {
        super(application);
        movieDao = MoviesDatabase.getDatabase(application).movieDao();
        directorDao = MoviesDatabase.getDatabase(application).directorDao();
        //moviesLiveData = new LivePagedListBuilder<>(movieDao.getAllMovies(), 20).build();
        PagedList.Config pagedListConfig  =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(true)
                        .setPrefetchDistance(20)
                        .setPageSize(20)
                        .build();
        moviesLiveData = new LivePagedListBuilder<>(movieDao.getAllMovies(), pagedListConfig).build();

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

    public int findDirector(String name){
        DirectorEntity directorEntity =  null;
        directorEntity =  directorDao.findDirectorByName(name);
        if(directorEntity!= null){
            return directorEntity.getId();
        }else{
            return -1;
        }
    }

    public void insertMovie(MovieEntity... movies) {
        movieDao.insert(movies);
    }

    public long insertDirector(DirectorEntity director){
        return directorDao.insert(director);
    }
}