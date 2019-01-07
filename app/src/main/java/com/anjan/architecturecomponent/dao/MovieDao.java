package com.anjan.architecturecomponent.dao;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListProvider;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.anjan.architecturecomponent.entity.DirectorEntity;
import com.anjan.architecturecomponent.entity.MovieEntity;

import java.util.List;

/**
 * Created by Anjan Debnath on 8/17/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie WHERE movieName = :title LIMIT 1")
    MovieEntity findMovieByTitle(String title);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(MovieEntity... directors);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(MovieEntity directors);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(MovieEntity director);

    @Query("DELETE FROM movie")
    void deleteAll();

   /* @Query("SELECT * FROM movie ORDER BY movieName ASC")
    LiveData<List<MovieEntity>> getAllMovies();*/


    @Query("SELECT * FROM movie ORDER BY time ASC")
    public abstract DataSource.Factory<Integer, MovieEntity> getAllMovies();

    /*@Query("SELECT * FROM movie ORDER BY time ASC")
    List<MovieEntity> getAllMovies();*/


}
