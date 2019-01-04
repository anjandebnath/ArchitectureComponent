package com.anjan.architecturecomponent;

import com.anjan.architecturecomponent.entity.MovieEntity;

/**
 * Created by Anjan Debnath on 1/3/2019.
 * Copyright (c) 2019, W3 Engineers Ltd. All rights reserved.
 */
public class ChatModelObject extends ListObject {

    private MovieEntity movieEntity;

    public MovieEntity getMovieEntity() {
        return movieEntity;
    }

    public void setMovieEntity(MovieEntity movieEntity) {
        this.movieEntity = movieEntity;
    }

    @Override
    public int getType(int userId) {
        return TYPE_GENERAL_LEFT;
    }
}
