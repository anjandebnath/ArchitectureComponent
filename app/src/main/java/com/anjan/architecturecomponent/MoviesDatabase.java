package com.anjan.architecturecomponent;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.util.Log;

import com.anjan.architecturecomponent.dao.DirectorDao;
import com.anjan.architecturecomponent.dao.MovieDao;
import com.anjan.architecturecomponent.entity.DateTypeConverter;
import com.anjan.architecturecomponent.entity.DirectorEntity;
import com.anjan.architecturecomponent.entity.MovieEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * Created by Anjan Debnath on 8/17/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */

@Database(entities = {MovieEntity.class, DirectorEntity.class}, version = 1)
@TypeConverters({DateTypeConverter.class})
public abstract class MoviesDatabase extends RoomDatabase {

    private static MoviesDatabase INSTANCE;
    private static final String DB_NAME = "movies.db";

    public static MoviesDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MoviesDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MoviesDatabase.class, DB_NAME)
                            .allowMainThreadQueries() // SHOULD NOT BE USED IN PRODUCTION !!!
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    Log.d("MoviesDatabase", "populating with data...");
                                    Executors.newSingleThreadScheduledExecutor().execute(() -> fillWithDemoData(context));
                                }
                            })
                            .build();
                }
            }
        }

        return INSTANCE;
    }



    public abstract MovieDao movieDao();

    public abstract DirectorDao directorDao();


    @WorkerThread
    private static void fillWithDemoData(Context context) {

         MovieDao movieDao = getDatabase(context).movieDao();
         DirectorDao directorDao = getDatabase(context).directorDao();

        JSONArray movie = loadJsonArray(context);

        try {
            List<MovieEntity> allMovies = new ArrayList<>();

            for (int i = 0; i < movie.length(); i++) {

                JSONObject item = movie.getJSONObject(i);

                String fullName = item.getString("full_name");
                DirectorEntity directorEntity = new DirectorEntity(fullName);
                String movieName = item.getString("movieName");
                MovieEntity movieEntity = new MovieEntity(movieName, (int) directorDao.insert(directorEntity));


                movieDao.insert(movieEntity);

                allMovies.add(movieEntity);

            }

            //movieDao.insertAll(allMovies.toArray(new MovieEntity[allMovies.size()]));

        } catch (JSONException exception) {
            exception.printStackTrace();
        }
    }

    private static JSONArray loadJsonArray(Context context) {
        StringBuilder builder = new StringBuilder();
        InputStream in = context.getResources().openRawResource(R.raw.movies);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            JSONObject json = new JSONObject(builder.toString());
            return json.getJSONArray("movies");

        } catch (IOException | JSONException exception) {
            exception.printStackTrace();
        }

        return null;
    }
}
