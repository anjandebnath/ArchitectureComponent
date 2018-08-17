package com.anjan.architecturecomponent.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Anjan Debnath on 8/17/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */

@Entity(tableName = "movie",
         foreignKeys = @ForeignKey(entity = DirectorEntity.class,
                 parentColumns = "dirID",
                 childColumns = "directorId",
                 onDelete = ForeignKey.CASCADE),
         indices = {@Index("movieName"), @Index("directorId")})
public class MovieEntity {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "movID")
    public int id;

    @ColumnInfo(name = "movieName")
    public String movieName;

    @ColumnInfo(name = "directorId")
    public int directorId;

    public MovieEntity(@NonNull String movieName, int directorId) {
        this.movieName = movieName;
        this.directorId = directorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getDirectorId() {
        return directorId;
    }

    public void setDirectorId(int directorId) {
        this.directorId = directorId;
    }


}
