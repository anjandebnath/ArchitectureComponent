package com.anjan.architecturecomponent.model;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.PropertyName;

public class Movies {

    @PropertyName("movie_name")
    public String movieName;
    @PropertyName("director_name")
    public String directorName;

    @Exclude
    public String getMovieName() {
        return movieName;
    }

    @Exclude
    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    @Exclude
    public String getDirectorName() {
        return directorName;
    }

    @Exclude
    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public Movies() {
    }

    public Movies(String movieName, String directorName){
       this.movieName = movieName;
        this.directorName = directorName;
    }
}
