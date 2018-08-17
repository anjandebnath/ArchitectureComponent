package com.anjan.architecturecomponent.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.anjan.architecturecomponent.entity.DirectorEntity;

import java.util.List;

/**
 * Created by Anjan Debnath on 8/17/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */

@Dao
public interface DirectorDao {

    @Query("SELECT * FROM director WHERE dirID = :id LIMIT 1")
    DirectorEntity findDirectorById(int id);

    @Query("SELECT * FROM director WHERE full_name = :fullName LIMIT 1")
    DirectorEntity findDirectorByName(String fullName);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(DirectorEntity director);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(DirectorEntity... directors);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(DirectorEntity director);

    @Query("DELETE FROM director")
    void deleteAll();

    @Query("SELECT * FROM director ORDER BY full_name ASC")
    LiveData<List<DirectorEntity>> getAllDirectors();
}
