package com.anjan.architecturecomponent.ui.main;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anjan.architecturecomponent.ListObject;
import com.anjan.architecturecomponent.R;
import com.anjan.architecturecomponent.entity.MovieEntity;

import static com.anjan.architecturecomponent.entity.MovieEntity.DIFF_CALLBACK;

/**
 * Created by Anjan Debnath on 8/17/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */

public class MoviesListAdapterNew extends PagedListAdapter<MovieEntity, MoviesViewHolder> {
    private LayoutInflater layoutInflater;
    private Context context;
    MoviesViewModel moviesViewModel;

    public static final DiffUtil.ItemCallback<MovieEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<MovieEntity>() {
                @Override
                public boolean areItemsTheSame(
                        @NonNull MovieEntity oldItem, @NonNull MovieEntity newItem) {
                    // User properties may have changed if reloaded from the DB, but ID is fixed
                    return oldItem.id == newItem.id;
                }
                @Override
                public boolean areContentsTheSame(
                        @NonNull MovieEntity oldItem, @NonNull MovieEntity newItem) {
                    // NOTE: if you use equals, your object must properly override Object#equals()
                    // Incorrectly returning false here will result in too many animations.
                    return oldItem.equals(newItem);
                }
            };


    public MoviesListAdapterNew(Context context, MoviesViewModel moviesViewModel) {
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

        holder.bindTo(getItem(position), moviesViewModel);
    }




}
