package com.anjan.architecturecomponent.job_schedulaer;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import static android.content.Context.JOB_SCHEDULER_SERVICE;

/**
 * Created by Anjan Debnath on 8/20/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public class ScheduleJob {

    private Context mContext;
    public static final String TAG = "Schedule Job";

    public ScheduleJob(Context context) {
        mContext = context;
    }

    @TargetApi(Build.VERSION_CODES.N)
    public void scheduleJobToTriggerNotification(){

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

            //to schedule a job first you need to get JobScheduler instance
            JobScheduler jobScheduler = (JobScheduler) mContext.getSystemService(JOB_SCHEDULER_SERVICE);

            ComponentName componentName = new ComponentName(mContext, NotifyMeJobService.class);

            long interValMillis = 17 * 60 * 1000;
            long refreshTime = 10* 60 * 1000;


            //JobInfo.Builder has various setter methods which allow you to define your Job.
            JobInfo jobInfo = new JobInfo.Builder(1, componentName)
                    .setPeriodic(interValMillis, refreshTime) // to run periodically*/
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY) // it can only start if the device is on a specific kind of network.
                    .setPersisted(true)    // task should continue to exist after the device has been rebooted.
                    .build();


            int ret = jobScheduler.schedule(jobInfo);
            if (ret == JobScheduler.RESULT_SUCCESS) {
                Log.d(TAG, "Job scheduled successfully");
            } else {
                Log.d(TAG, "Job scheduling failed");
            }
        }

        //.setMinimumLatency(60*1000)
    }
}
