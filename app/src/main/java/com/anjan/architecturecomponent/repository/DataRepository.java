package com.anjan.architecturecomponent.repository;

import android.app.Application;
import android.arch.paging.DataSource;

import com.anjan.architecturecomponent.MoviesDatabase;
import com.anjan.architecturecomponent.dao.DirectorDao;
import com.anjan.architecturecomponent.dao.MovieDao;
import com.anjan.architecturecomponent.entity.DirectorEntity;
import com.anjan.architecturecomponent.entity.MovieEntity;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static android.arch.lifecycle.ProcessLifecycleOwner.get;

/**
 * Created by Anjan Debnath on 1/8/2019.
 * Copyright (c) 2019, W3 Engineers Ltd. All rights reserved.
 */
public class DataRepository {

    private ExecutorService mIoExecutor;
    private static volatile DataRepository sInstance = null;
    MovieDao movieDao;
    DirectorDao directorDao;

    public static DataRepository getInstance(Application application) {
        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    MoviesDatabase database = MoviesDatabase.getDatabase(application);

                    sInstance = new DataRepository(database.movieDao(), database.directorDao(),
                            Executors.newSingleThreadExecutor());
                }
            }
        }
        return sInstance;
    }

    public DataRepository(MovieDao dao, DirectorDao directorDao, ExecutorService executor) {
        mIoExecutor = executor;
        movieDao = dao;
        this.directorDao = directorDao;

    }

    public DataSource.Factory<Integer, MovieEntity> getAllMovies() {
        return movieDao.getAllMovies();
    }

    public DirectorEntity findDirectorById(int id){
        DirectorEntity result = null;

        Callable<DirectorEntity> callable = () -> directorDao.findDirectorById(id);

        Future<DirectorEntity> future = mIoExecutor.submit(callable);

        try {
            result = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }
}
