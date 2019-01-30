package com.anjan.architecturecomponent.ui.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.anjan.architecturecomponent.ChatModelObject;
import com.anjan.architecturecomponent.DateObject;
import com.anjan.architecturecomponent.ListObject;
import com.anjan.architecturecomponent.MoviesDatabase;
import com.anjan.architecturecomponent.R;
import com.anjan.architecturecomponent.entity.DirectorEntity;
import com.anjan.architecturecomponent.entity.MovieEntity;
import com.anjan.architecturecomponent.repository.DataRepository;

/**
 * Created by Anjan Debnath on 8/17/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public class MoviesViewHolder extends RecyclerView.ViewHolder {

    private TextView titleText;
    private TextView directorText;
    private Context mContext;

    MoviesViewHolder(View itemView, Context context) {
        super(itemView);


        mContext = context;
        titleText = (TextView) itemView.findViewById(R.id.tvMovieTitle);
        directorText = (TextView) itemView.findViewById(R.id.tvMovieDirectorFullName);


    }

    // for MoviesListAdapterNew
    void bindTo(MovieEntity movieEntity, MoviesViewModel moviesViewModel){

        itemView.setTag(movieEntity.id);
        titleText.setText(movieEntity.movieName);
        DirectorEntity director = moviesViewModel.findDirector(movieEntity.directorId);

        if (director != null) {
            directorText.setText(director.fullName);
        }




    }

    // for MoviesListAdapterNew
    void bindTo(ListObject listObject, MoviesViewModel moviesViewModel) {

        if(listObject instanceof ChatModelObject){
            ChatModelObject generalItem = (ChatModelObject) listObject;
            itemView.setTag(generalItem.getMovieEntity());
            titleText.setText(generalItem.getMovieEntity().movieName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    moviesViewModel.updateMovieName("Anjan Debnath", generalItem.getMovieEntity().id);
                }
            });

        }else if(listObject instanceof DateObject){
            DateObject dateObject = (DateObject) listObject;
            itemView.setTag(dateObject.getDate());
            titleText.setText(dateObject.getDate());
        }
    }

    void clear() {
        itemView.invalidate();
        titleText.invalidate();
        directorText.invalidate();
    }
}
