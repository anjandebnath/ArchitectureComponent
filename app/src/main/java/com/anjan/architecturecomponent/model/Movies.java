package com.anjan.architecturecomponent.model;

public class Movies {

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    String movieName;
    String directorName;

    public Movies() {
    }
}
