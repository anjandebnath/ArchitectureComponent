package com.anjan.architecturecomponent.pager;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.anjan.architecturecomponent.ListObject;

/**
 * Created by Anjan Debnath on 1/9/2019.
 * Copyright (c) 2019, W3 Engineers Ltd. All rights reserved.
 */
public class DataSourceFactory extends DataSource.Factory<Integer, ListObject> {

    private final MovieListDataSource movieListDataSource;
    private MutableLiveData<MovieListDataSource> showsDataSourceLiveData;


    public DataSourceFactory(MovieListDataSource dataSource) {
        this.movieListDataSource = dataSource;
        showsDataSourceLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource<Integer, ListObject> create() {
        showsDataSourceLiveData.postValue(movieListDataSource);
        return movieListDataSource;
    }

    public MovieListDataSource getMovieListDataSource() {
        return movieListDataSource;
    }
}
