package com.anjan.architecturecomponent.pager;

import android.arch.lifecycle.LiveData;
import android.arch.paging.ItemKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import com.anjan.architecturecomponent.ListObject;

import java.util.List;

/**
 * Created by Anjan Debnath on 1/10/2019.
 * Copyright (c) 2019, W3 Engineers Ltd. All rights reserved.
 */
public class MovieItemKeyedSource extends ItemKeyedDataSource<Integer, ListObject> {
    private static final String TAG = "ShowsDataSource";

    private int pageNumber = 1;
    // For Retry
    private LoadParams<Integer> params;
    private LoadCallback<ListObject> callback;



    public MovieItemKeyedSource() {

    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params,
                            @NonNull LoadInitialCallback<ListObject> callback) {
        Log.d(TAG, "Fetching first page: " + pageNumber);
    }

    private void onError(Throwable throwable) {
        Log.e(TAG, throwable.getMessage());
    }

    private void onShowsFetched(List<ListObject> shows, LoadInitialCallback<ListObject> callback) {
        pageNumber++;
        callback.onResult(shows);
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params,
                          @NonNull LoadCallback<ListObject> callback) {
        this.params = params;
        this.callback = callback;
        Log.d(TAG, "Fetching next page: " + pageNumber);
    }

    private void onMoreShowsFetched(List<ListObject> shows, LoadCallback<ListObject> callback) {

        pageNumber++;
        callback.onResult(shows);
    }

    private void onPaginationError(Throwable throwable) {
        Log.e(TAG, throwable.getMessage());
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params,
                           @NonNull LoadCallback<ListObject> callback) {
        // Do nothing, since data is loaded from our initial load itself
    }

    @NonNull
    @Override
    public Integer getKey(@NonNull ListObject item) {
        return pageNumber;
    }

    public void clear() {
        pageNumber = 1;
    }



    public void retryPagination() {
        loadAfter(params, callback);
    }
}
