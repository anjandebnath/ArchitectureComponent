package com.anjan.architecturecomponent.ui.add;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.anjan.architecturecomponent.MoviesDatabase;
import com.anjan.architecturecomponent.dao.DirectorDao;
import com.anjan.architecturecomponent.dao.MovieDao;
import com.anjan.architecturecomponent.entity.DirectorEntity;
import com.anjan.architecturecomponent.entity.MovieEntity;

/**
 * Created by Anjan Debnath on 8/17/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public class AddMoviesViewModel extends AndroidViewModel {

    private MovieDao movieDao;
    private DirectorDao directorDao;

    public AddMoviesViewModel(@NonNull Application application) {
        super(application);

        movieDao = MoviesDatabase.getDatabase(application).movieDao();
        directorDao = MoviesDatabase.getDatabase(application).directorDao();
    }

    public void insertMovie(MovieEntity... movies) {
        movieDao.insertAll(movies);
    }

    public long insertDirector(DirectorEntity director){
        return directorDao.insert(director);
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


}
