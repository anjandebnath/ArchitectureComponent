package com.anjan.architecturecomponent.job_schedulaer;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Anjan Debnath on 8/20/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public class FirebaseDbToRoomDataUpdateTask {

    private CouponsDb couponsDb;
    private FirebaseFirestore firestoreDB;
    private TaskExecutor taskExecutor;

    public FirebaseDbToRoomDataUpdateTask(){
        firestoreDB = FirebaseFirestore.getInstance();
        taskExecutor = new TaskExecutor();
    }
    public void getCouponsFromFirebaseUpdateLocalDb(final Context ctx) {
        firestoreDB.collection("coupons")
                .whereEqualTo("createDt", getTodaysDate())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            List<Coupon> cpnList = task.getResult().toObjects(Coupon.class);
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
        private List<Coupon> cpnList;
        private Context context;
        public RoomUpdateTask(List<Coupon> cpnListIn, Context ctx){
            cpnList = cpnListIn;
            context = ctx;
        }
        @Override
        public void run() {
            insertLatestCouponsIntoLocalDb(cpnList, context);
        }
    }
    private void insertLatestCouponsIntoLocalDb(List<Coupon> cpns, Context ctx){
        couponsDb = Room.databaseBuilder(ctx,
                CouponsDb.class, "coupons db").build();

        //insert new coupons
        couponsDb.CouponsDb().insertCoupons(cpns);

        //delete expired coupons
        couponsDb.CouponsDb().deleteCoupons(getTodaysDate());
        Log.d("ROOM", "local database update complete");

        Log.d("ROOM", "number of local records " +
                couponsDb.CouponsDb().getCoupons().size());
    }
    private String getTodaysDate(){
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        return sdf.format(date);
    }
}
