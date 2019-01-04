package com.anjan.architecturecomponent.pager;

import android.arch.paging.PositionalDataSource;
import android.support.annotation.NonNull;

import com.anjan.architecturecomponent.ListObject;

import java.util.List;

/**
 * Created by Anjan Debnath on 1/4/2019.
 * Copyright (c) 2019, W3 Engineers Ltd. All rights reserved.
 */
public class MovieListDataSource extends PositionalDataSource<ListObject> {


    private List<ListObject> list;
    int size = 0;

    public MovieListDataSource(List<ListObject> list){
        this.list = list;
        size = this.list.size();
    }

   /* private int computeCount() {
        // actual count code here
    }

    private List<ListObject> loadRangeInternal(int startPosition, int loadCount) {
        // actual load code here
    }*/

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<ListObject> callback) {
        // return info back to PagedList


        callback.onResult(list.subList(params.requestedStartPosition, params.requestedLoadSize),
                params.requestedStartPosition,
                size);

       /* int totalCount = computeCount();
        int position = computeInitialLoadPosition(params, totalCount);
        int loadSize = computeInitialLoadSize(params, position, totalCount);
        callback.onResult(loadRangeInternal(position, loadSize), position, totalCount);*/
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<ListObject> callback) {
        // return info back to PagedList
       /* List<ListObject> result = provider.getStringList(0, params.requestedLoadSize);
        callback.onResult(result);*/

        callback.onResult(list.subList(
                params.startPosition,
                Math.min(params.startPosition + params.loadSize, size)));

        /*callback.onResult(loadRangeInternal(params.startPosition, params.loadSize));*/
    }
}
