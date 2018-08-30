package com.anjan.architecturecomponent;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.anjan.architecturecomponent.dao.DirectorDao;
import com.anjan.architecturecomponent.dao.MovieDao;
import com.anjan.architecturecomponent.entity.DirectorEntity;
import com.anjan.architecturecomponent.entity.MovieEntity;

/**
 * Created by Anjan Debnath on 8/17/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */

@Database(entities = {MovieEntity.class, DirectorEntity.class}, version = 1, exportSchema = false)

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
                                    new PopulateDbAsync(INSTANCE).execute();
                                }
                            })
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    public void clearDb() {
        if (INSTANCE != null) {
            new PopulateDbAsync(INSTANCE).execute();
        }
    }

    public abstract MovieDao movieDao();

    public abstract DirectorDao directorDao();

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final MovieDao movieDao;
        private final DirectorDao directorDao;

        public PopulateDbAsync(MoviesDatabase instance) {
            movieDao = instance.movieDao();
            directorDao = instance.directorDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            movieDao.deleteAll();
            directorDao.deleteAll();

            DirectorEntity directorOne = new DirectorEntity("Adam McKay");
            DirectorEntity directorTwo = new DirectorEntity("Denis Villeneuve");
            DirectorEntity directorThree = new DirectorEntity("Morten Tyldum");

            MovieEntity movieOne = new MovieEntity("The Big Short", (int) directorDao.insert(directorOne));
            final int dIdTwo = (int) directorDao.insert(directorTwo);
            MovieEntity movieTwo = new MovieEntity("Arrival", dIdTwo);
            MovieEntity movieThree = new MovieEntity("Blade Runner 2049", dIdTwo);
            MovieEntity movieFour = new MovieEntity("Passengers", (int) directorDao.insert(directorThree));

            movieDao.insert(movieOne, movieTwo, movieThree, movieFour);

            return null;
        }
    }
}
