package com.anjan.architecturecomponent.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import com.anjan.architecturecomponent.ListObject;

import java.io.Serializable;

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
public class MovieEntity implements Serializable {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "movID")
    public int id;

    @ColumnInfo(name = "movieName")
    public String movieName;

    @ColumnInfo(name = "directorId")
    public int directorId;

    @ColumnInfo(name = "time")
    public String time;

    public static final DiffUtil.ItemCallback<ListObject> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<ListObject>() {
                @Override
                public boolean areItemsTheSame(
                        @NonNull ListObject oldItem, @NonNull ListObject newItem) {
                    // User properties may have changed if reloaded from the DB, but ID is fixed
                    return oldItem.equals(newItem);
                }
                @Override
                public boolean areContentsTheSame(
                        @NonNull ListObject oldItem, @NonNull ListObject newItem) {
                    // NOTE: if you use equals, your object must properly override Object#equals()
                    // Incorrectly returning false here will result in too many animations.
                    return oldItem.equals(newItem);
                }
            };

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        MovieEntity movie = (MovieEntity) obj;

        return movie.id == this.id && movie.movieName == this.movieName;
    }


    public MovieEntity(@NonNull String movieName, int directorId, String time) {
        this.movieName = movieName;
        this.directorId = directorId;
        this.time = time;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }





}
