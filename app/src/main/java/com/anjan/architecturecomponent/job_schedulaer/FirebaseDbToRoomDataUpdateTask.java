package com.anjan.architecturecomponent.job_schedulaer;

import android.app.NotificationManager;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.anjan.architecturecomponent.MoviesDatabase;
import com.anjan.architecturecomponent.R;
import com.anjan.architecturecomponent.dao.DirectorDao;
import com.anjan.architecturecomponent.dao.MovieDao;
import com.anjan.architecturecomponent.entity.DirectorEntity;
import com.anjan.architecturecomponent.entity.MovieEntity;
import com.anjan.architecturecomponent.model.Movies;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * Created by Anjan Debnath on 8/20/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public class FirebaseDbToRoomDataUpdateTask {

    private MoviesDatabase moviesDatabase;
    private FirebaseFirestore firestoreDB;
    private TaskExecutor taskExecutor;
    private MovieDao movieDao;
    private DirectorDao directorDao;
    public static final String CHANNEL_ID = "default";
    public static final int notificationID = 101;

    public FirebaseDbToRoomDataUpdateTask(){

        firestoreDB = FirebaseFirestore.getInstance();
        taskExecutor = new TaskExecutor();
    }


    public void getMoviesFromFirebaseUpdateLocalDb(final Context context) {

        movieDao = MoviesDatabase.getDatabase(context).movieDao();
        directorDao = MoviesDatabase.getDatabase(context).directorDao();


        firestoreDB.collection("movies")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            List<Movies> moviesList = task.getResult().toObjects(Movies.class);
                            Log.d("FIREBASE", "no of coupons "+moviesList.size());

                            //Run updating local database on worker thread.
                            new TaskExecute(moviesList, context).execute();
                            //taskExecutor.execute(new RoomUpdateTask(moviesList, context));

                        } else {
                            Log.d("FIREBASE", "Error getting documents: ",
                                    task.getException());
                        }
                    }
                });
    }

    public class TaskExecute extends AsyncTask<Void, Void, Void>{
        private List<Movies> moviesList;
        private Context context;

        public TaskExecute(List<Movies> moviesList, Context ctx){
            this.moviesList = moviesList;
            context = ctx;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            insertLatestCouponsIntoLocalDb(moviesList);
            return null;
        }


        protected void onPostExecute(Void aVoid) {

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.noti)
                    .setContentTitle(context.getResources().getString(R.string.notification_title))
                    .setContentText(context.getResources().getString(R.string.notification_content))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            // notificationID allows you to update the notification later on.
            if (mNotificationManager != null) {
                mNotificationManager.notify(notificationID, mBuilder.build());
            }
        }

    }

    public class TaskExecutor implements Executor {
        @Override
        public void execute(@NonNull Runnable runnable) {
            Thread t =  new Thread(runnable);
            t.start();
        }
    }

    public class RoomUpdateTask implements Runnable{
        private List<Movies> moviesList;
        private Context context;
        public RoomUpdateTask(List<Movies> moviesList, Context ctx){
            this.moviesList = moviesList;
            context = ctx;
        }
        @Override
        public void run() {
            insertLatestCouponsIntoLocalDb(moviesList);
        }
    }

    private void insertLatestCouponsIntoLocalDb(List<Movies> moviesList){

        String directorName = null;
        String movieName = null;


        for(int i=0;i<moviesList.size(); i++){

            Movies movie = moviesList.get(i);
            directorName = movie.getDirectorName();
            movieName = movie.getMovieName();

            if(directorName!= null && movieName!= null){

                DirectorEntity directorEntity = new DirectorEntity(directorName);

                //check duplicate id can not be inserted
                int directorId = findDirector(directorName);
                if( directorId < 0){
                    directorId = (int) directorDao.insert(directorEntity);
                }
                MovieEntity movieEntity = new MovieEntity(movieName, directorId);
                movieDao.insert(movieEntity);

            }
        }






    }

    private int findDirector(String name){
        DirectorEntity directorEntity =  null;
        directorEntity =  directorDao.findDirectorByName(name);
        if(directorEntity!= null){
            return directorEntity.getId();
        }else{
            return -1;
        }
    }

    private String getTodaysDate(){
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        return sdf.format(date);
    }
}
