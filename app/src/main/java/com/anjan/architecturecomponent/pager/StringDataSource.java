package com.anjan.architecturecomponent.pager;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.anjan.architecturecomponent.ListObject;

import java.util.List;

/**
 * Created by Anjan Debnath on 1/4/2019.
 * Copyright (c) 2019, W3 Engineers Ltd. All rights reserved.
 */
public class StringDataSource extends PageKeyedDataSource<Integer, ListObject> {

    public static final int PAGE_SIZE = 20;
    private StringListProvider provider;

    public StringDataSource(StringListProvider provider) {
        this.provider = provider;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, ListObject> callback) {

        List<ListObject> result = provider.getStringList(0, params.requestedLoadSize);
        callback.onResult(result, 1, 2);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, ListObject> callback) {

        List<ListObject> result = provider.getStringList(params.key, params.requestedLoadSize);
        Integer nextIndex = null;

        if (params.key > 1) {
            nextIndex = params.key - 1;
        }
        callback.onResult(result, nextIndex);
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, ListObject> callback) {
        List<ListObject> result = provider.getStringList(params.key, params.requestedLoadSize);
        callback.onResult(result, params.key + 1);
    }
}
