package com.anjan.architecturecomponent.job_schedulaer;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;

import static android.content.Context.JOB_SCHEDULER_SERVICE;

/**
 * Created by Anjan Debnath on 8/20/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public class ScheduleJob {

    private Context mContext;

    public ScheduleJob(Context context) {
        mContext = context;
    }

    public void scheduleJobFirebaseToRoomDataUpdate(){

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

            JobScheduler jobScheduler = (JobScheduler) mContext
                    .getSystemService(JOB_SCHEDULER_SERVICE);
            ComponentName componentName = new ComponentName(mContext, DbUpdateJobService.class);

            long interValMillis = 1 * 60 * 1000;
            JobInfo jobInfo = new JobInfo.Builder(1, componentName)
                    .setPeriodic(interValMillis) // to run periodically
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY) // it can only start if the device is on a specific kind of network.
                    .setPersisted(true)    // task should continue to exist after the device has been rebooted.
                    .build();

            jobScheduler.schedule(jobInfo);
        }


    }
}
