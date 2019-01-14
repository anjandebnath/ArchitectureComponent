package com.anjan.architecturecomponent.pager;

import android.arch.paging.PositionalDataSource;
import android.support.annotation.NonNull;

import com.anjan.architecturecomponent.ListObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Anjan Debnath on 1/4/2019.
 * Copyright (c) 2019, W3 Engineers Ltd. All rights reserved.
 */
public class MovieListDataSource extends PositionalDataSource<ListObject> {

    private List<ListObject> list;

    public MovieListDataSource(List<ListObject> list){
        this.list = list;

    }

    private int computeCount() {
        // actual count code here
        return list.size();
    }

    private List<ListObject> loadRangeInternal(int startPosition, int loadCount) {
        // actual load code here
        List<ListObject> modelList = new ArrayList<>();
        int end = Math.min(computeCount(), startPosition + loadCount);
        for (int i = startPosition; i != end; i++) {
            modelList.add(list.get(i));
        }

        return modelList;

    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<ListObject> callback) {
        // return info back to PagedList
        int totalCount = computeCount();
        int position = computeInitialLoadPosition(params, totalCount);
        int loadSize = computeInitialLoadSize(params, position, totalCount);
        callback.onResult(loadRangeInternal(position, loadSize), position, totalCount);
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<ListObject> callback) {
        // return info back to PagedList
        callback.onResult(loadRangeInternal(params.startPosition, params.loadSize));
    }
}
