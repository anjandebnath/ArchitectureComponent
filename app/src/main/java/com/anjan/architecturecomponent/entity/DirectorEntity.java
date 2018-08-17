package com.anjan.architecturecomponent.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Anjan Debnath on 8/17/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */

@Entity(tableName = "director",
         indices = {@Index(value = "full_name", unique = true)})
public class DirectorEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "dirID")
    public int id;

    @Ignore //Room will not persist that field.
    public int age;

    @ColumnInfo(name = "full_name")
    public String fullName;

    public DirectorEntity(@NonNull String fullName) {
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }





}
