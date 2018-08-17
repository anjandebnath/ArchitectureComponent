package com.anjan.architecturecomponent.ui.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anjan.architecturecomponent.MoviesDatabase;
import com.anjan.architecturecomponent.R;
import com.anjan.architecturecomponent.entity.DirectorEntity;
import com.anjan.architecturecomponent.entity.MovieEntity;

import java.util.List;

/**
 * Created by Anjan Debnath on 8/17/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.MoviesViewHolder> {
    private LayoutInflater layoutInflater;
    private List<MovieEntity> movieList;
    private Context context;

    public MoviesListAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setMovieList(List<MovieEntity> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = layoutInflater.inflate(R.layout.item_list_movie, parent, false);
        return new MoviesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {
        if (movieList == null) {
            return;
        }

        final MovieEntity movie = movieList.get(position);
        if (movie != null) {
            holder.titleText.setText(movie.movieName);

            final DirectorEntity director = MoviesDatabase.getDatabase(context).directorDao().findDirectorById(movie.directorId);
            final String directorFullName;
            if (director != null) {
                holder.directorText.setText(director.fullName);
                directorFullName = director.fullName;
            } else {
                directorFullName = "";
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (movieList == null) {
            return 0;
        } else {
            return movieList.size();
        }
    }

    static class MoviesViewHolder extends RecyclerView.ViewHolder {
        private TextView titleText;
        private TextView directorText;

        public MoviesViewHolder(View itemView) {
            super(itemView);

            titleText = itemView.findViewById(R.id.tvMovieTitle);
            directorText = itemView.findViewById(R.id.tvMovieDirectorFullName);
        }
    }
}
