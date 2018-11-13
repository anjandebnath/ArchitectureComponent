package com.anjan.architecturecomponent.ui.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


import com.anjan.architecturecomponent.MoviesDatabase;
import com.anjan.architecturecomponent.dao.MovieDao;
import com.anjan.architecturecomponent.entity.MovieEntity;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Anjan Debnath on 8/17/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */

public class MoviesViewModel extends AndroidViewModel {

    private MovieDao movieDao;
    private LiveData<List<MovieEntity>> moviesLiveData;
    private MutableLiveData<Long> mElapsedTime = new MutableLiveData<>();
    private MediatorLiveData<List<MovieEntity>> moviesMediatorLiveData = new MediatorLiveData<>();

    private static final int ONE_SECOND = 1000;

    private long mInitialTime;

    public MoviesViewModel(@NonNull Application application) {
        super(application);
        movieDao = MoviesDatabase.getDatabase(application).movieDao();

        setupMutableLiveData();

        setupMediatorLiveData();

    }

    /**
     * this will return all movies from db and notify LiveData object
     * @return
     */
    private LiveData<List<MovieEntity>> getMoviesLiveData(){

        moviesLiveData = movieDao.getAllMoviesByDate("02/04/1997");
        return moviesLiveData;

    }

    /**
     * MediatorLiveData
     * It will merge the LiveData object into Mediator Livedata
     */
    private void setupMediatorLiveData(){

        moviesMediatorLiveData.addSource(getMoviesLiveData(), new Observer<List<MovieEntity>>() {
            @Override
            public void onChanged(@Nullable List<MovieEntity> movieEntities) {
                moviesMediatorLiveData.setValue(movieEntities);
            }
        });
    }

    /**
     * Mutable LiveData
     * It will simply notify the updated time
     */
    private void setupMutableLiveData(){

        mInitialTime = SystemClock.elapsedRealtime();
        Timer timer = new Timer();

        // Update the elapsed time every second.
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                final long newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000;
                // setValue() cannot be called from a background thread so postValue is used.
                mElapsedTime.postValue(newValue);
            }
        }, ONE_SECOND, ONE_SECOND);

    }

    public LiveData<Long> getElapsedTime() {
        return mElapsedTime;
    }

    public LiveData<List<MovieEntity>> getMoviesList() {
        return moviesLiveData;
    }

    public LiveData<List<MovieEntity>> getMediatorMoviesList(){
        return moviesMediatorLiveData;
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