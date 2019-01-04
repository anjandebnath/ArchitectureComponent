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


    public MoviesListAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
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

        ListObject movieEntity = getItem(position);

        holder.bindTo(getItem(position));
    }




}
