package com.anjan.architecturecomponent.ui.main;

import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anjan.architecturecomponent.ChatModelObject;
import com.anjan.architecturecomponent.DateObject;
import com.anjan.architecturecomponent.ListObject;
import com.anjan.architecturecomponent.R;
import com.anjan.architecturecomponent.entity.MovieEntity;

import java.util.List;

import static com.anjan.architecturecomponent.entity.MovieEntity.DIFF_CALLBACK;

/**
 * Created by Anjan Debnath on 8/17/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */

public class MoviesListAdapter extends PagedListAdapter<ListObject, MoviesViewHolder> {
    private LayoutInflater layoutInflater;
    private Context context;
    MoviesViewModel moviesViewModel;



    public MoviesListAdapter(Context context, MoviesViewModel moviesViewModel) {
        super(DIFF_CALLBACK);
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.moviesViewModel = moviesViewModel;
    }


    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = layoutInflater.inflate(R.layout.item_list_movie, parent, false);
        return new MoviesViewHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {

        ListObject listObject = getItem(position);

        if (listObject != null) {
            holder.bindTo(listObject, moviesViewModel);
        } else {

            // By debugging I found that while reading data in view model via Dao, it is returning list of size 25.
            // But only first 9 of these are non null. All other entries in the list is null
            // I am expecting the null data will refresh soon as this is a paged list.
            // But the problem is the null are never getting refreshed with valid data.

            holder.clear();  // we need to use this otherwise recyclerview will be crushed
        }
    }




}
