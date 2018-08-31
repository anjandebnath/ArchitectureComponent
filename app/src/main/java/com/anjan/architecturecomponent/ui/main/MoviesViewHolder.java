package com.anjan.architecturecomponent.ui.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.anjan.architecturecomponent.MoviesDatabase;
import com.anjan.architecturecomponent.R;
import com.anjan.architecturecomponent.custom_view.MovieListView;
import com.anjan.architecturecomponent.entity.DirectorEntity;
import com.anjan.architecturecomponent.entity.MovieEntity;

/**
 * Created by Anjan Debnath on 8/17/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public class MoviesViewHolder extends RecyclerView.ViewHolder {

    private MovieListView movieListView;
    private Context mContext;

    MoviesViewHolder(View itemView, Context context) {
        super(itemView);
        movieListView = (MovieListView) itemView;
        mContext = context;

    }

    public MovieListView getMovieListView() {
        return movieListView;
    }

    void bindTo(MovieEntity movieEntity) {

        getMovieListView().setTag(movieEntity.id);
        getMovieListView().setMovieTitle(movieEntity.movieName);
        DirectorEntity director = MoviesDatabase.getDatabase(mContext).directorDao().findDirectorById(movieEntity.directorId);

        if (director != null) {
            getMovieListView().setDirectorName(director.fullName);
        }


    }

    /*void clear() {
        itemView.invalidate();
        titleText.invalidate();
        directorText.invalidate();
    }*/
}
