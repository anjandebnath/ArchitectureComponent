package com.anjan.architecturecomponent.job_schedulaer;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.anjan.architecturecomponent.MoviesDatabase;
import com.anjan.architecturecomponent.model.Movies;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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

    public FirebaseDbToRoomDataUpdateTask(){
        firestoreDB = FirebaseFirestore.getInstance();
        taskExecutor = new TaskExecutor();
    }
    public void getCouponsFromFirebaseUpdateLocalDb(final Context ctx) {
        firestoreDB.collection("movies")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            List<Movies> cpnList = task.getResult().toObjects(Movies.class);
                            Log.d("FIREBASE", "no of coupons "+cpnList.size());

                            //Run updating local database on worker thread.
                            taskExecutor.execute(new RoomUpdateTask(cpnList, ctx));

                        } else {
                            Log.d("FIREBASE", "Error getting documents: ",
                                    task.getException());
                        }
                    }
                });
    }

    public class TaskExecutor implements Executor {
        @Override
        public void execute(@NonNull Runnable runnable) {
            Thread t =  new Thread(runnable);
            t.start();
        }
    }

    public class RoomUpdateTask implements Runnable{
        private List<Movies> cpnList;
        private Context context;
        public RoomUpdateTask(List<Movies> cpnListIn, Context ctx){
            cpnList = cpnListIn;
            context = ctx;
        }
        @Override
        public void run() {
            //insertLatestCouponsIntoLocalDb(cpnList, context);
        }
    }

    /*private void insertLatestCouponsIntoLocalDb(List<Movies> cpns, Context ctx){
        moviesDatabase = Room.databaseBuilder(ctx,
                CouponsDb.class, "coupons db").build();

        //insert new coupons
        moviesDatabase.CouponsDb().insertCoupons(cpns);

        //delete expired coupons
        moviesDatabase.CouponsDb().deleteCoupons(getTodaysDate());
        Log.d("ROOM", "local database update complete");

        Log.d("ROOM", "number of local records " +
                moviesDatabase.CouponsDb().getCoupons().size());
    }*/

    private String getTodaysDate(){
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        return sdf.format(date);
    }
}
