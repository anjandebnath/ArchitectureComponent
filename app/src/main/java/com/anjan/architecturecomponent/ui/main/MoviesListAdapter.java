package com.anjan.architecturecomponent.ui.main;

import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anjan.architecturecomponent.R;
import com.anjan.architecturecomponent.custom_view.MovieListView;
import com.anjan.architecturecomponent.entity.MovieEntity;

/**
 * Created by Anjan Debnath on 8/17/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */

public class MoviesListAdapter extends PagedListAdapter<MovieEntity, MoviesViewHolder> {

    private Context context;

    public MoviesListAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    public static final DiffUtil.ItemCallback<MovieEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<MovieEntity>() {
                @Override
                public boolean areItemsTheSame(
                        @NonNull MovieEntity oldItem, @NonNull MovieEntity newItem) {
                    // User properties may have changed if reloaded from the DB, but ID is fixed
                    return oldItem.getId() == newItem.getId();
                }
                @Override
                public boolean areContentsTheSame(
                        @NonNull MovieEntity oldItem, @NonNull MovieEntity newItem) {
                    // NOTE: if you use equals, your object must properly override Object#equals()
                    // Incorrectly returning false here will result in too many animations.
                    return oldItem.equals(newItem);
                }
            };



    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //final View itemView = layoutInflater.inflate(R.avatar_media_supporting_text.item_list_movie, parent, false);

        // no need for a LayoutInflater instance— the custom view inflates itself
        MovieListView movieListView = new MovieListView(parent.getContext());
        // manually set the CustomView's size
        int height = 150;
        movieListView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                height
        ));
        return new MoviesViewHolder(movieListView, context);
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {

        final MovieEntity movie = getItem(position);
        if (movie != null) {
            holder.bindTo(movie);
        }
    }


}
