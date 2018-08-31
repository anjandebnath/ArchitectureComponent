package com.anjan.architecturecomponent.custom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anjan.architecturecomponent.R;

/**
 * Created by Anjan Debnath on 8/31/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public class MovieListView extends LinearLayout {

    String movieTitle, directorName;
    TextView movieNameView;
    TextView directorNameView;

    // this constructor will be used when value will be set from dynamic sources
    public MovieListView(Context context) {
        super(context);

        //set orientation and gravity
        setOrientation(LinearLayout.VERTICAL);

        // set layout of the custom view
        LayoutInflater.from(context).inflate(R.layout.view_list_movie, this, true);

        init(movieTitle, directorName);
    }

    // this constructor will be used when value will be set from xml
    public MovieListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //set orientation and gravity
        setOrientation(LinearLayout.VERTICAL);

        // set layout of the custom view
        LayoutInflater.from(context).inflate(R.layout.view_list_movie, this, true);


        // get styleable attrs
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MovieListView, 0, 0);

        try{
            movieTitle = a.getString(R.styleable.MovieListView_movieName);
            directorName = a.getString(R.styleable.MovieListView_directorName);
        }finally {
            a.recycle();
        }

        init(movieTitle, directorName);

    }

    private void init(String movieTitle, String directorName){

        movieNameView = findViewById(R.id.tvMovieTitle);
        directorNameView = findViewById(R.id.tvMovieDirectorFullName);


        movieNameView.setText(movieTitle);
        directorNameView.setText(directorName);

    }

    public void setMovieTitle(String movieTitle){
        movieNameView.setText(movieTitle);
    }

    public void setDirectorName(String directorName){
        directorNameView.setText(directorName);
    }


}
