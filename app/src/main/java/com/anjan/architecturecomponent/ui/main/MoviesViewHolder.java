package com.anjan.architecturecomponent.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.anjan.architecturecomponent.R;
import com.anjan.architecturecomponent.entity.MovieEntity;

/**
 * Created by Anjan Debnath on 8/17/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public class MoviesViewHolder extends RecyclerView.ViewHolder {

    private TextView titleText;
    private TextView directorText;

    MoviesViewHolder(View itemView) {
        super(itemView);
        titleText = (TextView) itemView.findViewById(R.id.tvMovieTitle);
        directorText = (TextView) itemView.findViewById(R.id.tvMovieDirectorFullName);

    }

    void bindTo(MovieEntity movieEntity) {
        itemView.setTag(movieEntity.id);
        titleText.setText(movieEntity.movieName);
        //directorText.setText();

    }

    void clear() {
        itemView.invalidate();
        titleText.invalidate();
        directorText.invalidate();
    }
}
