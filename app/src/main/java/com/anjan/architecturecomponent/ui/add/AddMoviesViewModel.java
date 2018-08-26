package com.anjan.architecturecomponent.ui.add;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.anjan.architecturecomponent.MoviesDatabase;
import com.anjan.architecturecomponent.dao.DirectorDao;
import com.anjan.architecturecomponent.dao.MovieDao;
import com.anjan.architecturecomponent.entity.DirectorEntity;
import com.anjan.architecturecomponent.entity.MovieEntity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static android.support.constraint.Constraints.TAG;

/**
 * Created by Anjan Debnath on 8/17/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public class AddMoviesViewModel extends AndroidViewModel {

    private MovieDao movieDao;
    private DirectorDao directorDao;

    private static final String TAG = "ADD_DATA";

    public AddMoviesViewModel(@NonNull Application application) {
        super(application);

        movieDao = MoviesDatabase.getDatabase(application).movieDao();
        directorDao = MoviesDatabase.getDatabase(application).directorDao();
    }

    public  void insertToFirebaseCloud(String movieName, String directorName){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> movieDetail = new HashMap<>();
        movieDetail.put("movie_name", movieName);
        movieDetail.put("director_name", directorName);


        // Add a new document with a generated ID
        db.collection("movies")
                .add(movieDetail)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

    }

    public void insertMovie(MovieEntity... movies) {
        movieDao.insert(movies);
    }

    public long insertDirector(DirectorEntity director){
        return directorDao.insert(director);
    }

    public int findDirector(String name){
        DirectorEntity directorEntity =  null;
        directorEntity =  directorDao.findDirectorByName(name);
        if(directorEntity!= null){
            return directorEntity.getId();
        }else{
            return -1;
        }
    }


}
