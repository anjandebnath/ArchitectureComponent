package com.anjan.architecturecomponent.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

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
    void insert(MovieEntity... movieEntity);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(MovieEntity movieEntity);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(MovieEntity movieEntity);

    @Query("DELETE FROM movie")
    void deleteAll();

    @Query("SELECT * FROM movie ORDER BY movieName ASC")
    LiveData<List<MovieEntity>> getAllMovies();
}
