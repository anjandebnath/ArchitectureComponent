This project covers the syllabus of `Associate Android Developer Certification`

[Exam Content](https://developers.google.com/training/certification/associate-android-developer/#exam-content) is here and [x] `topics are covered here`.

### Testing and debugging
- Write and execute local JVM unit tests
- Write and execute Android UI tests
- Use the system log to output debug information
- Debug and fix issues with an app's functional behavior and usability

### User interface (UI) and app functionality
- [x] Create an Activity that displays a layout
- [x] Construct a UI with ConstraintLayout
- [x] Create a custom view class and add it to a layout
- [x] Apply content descriptions to views for accessibility
- [x] Implement a custom app theme
- [x] Display items in a RecyclerView
- [x] Bind local data to a RecyclerView list using the paging library
- Add accessibility hooks to a custom view
- Implement menu-based or drawer navigation
- Localize the app
- [x] Display notifications, toasts, and snackbar messages
- [x] Schedule a background task using JobScheduler
- [x] Efficiently run jobs in the background

### App data and files

- [x] Define data using Room entities
- [x] Access Room database with data access object (DAO)
- [x] Observe and respond to changing data using LiveData
- [x] Use a Repository to handle data operations
- Read and parse raw resources or asset files
- Create persistent preference data from user input
- Change the behavior of the app based on user preferences


## Job Scheduler example


### How it works
![](https://github.com/anjandebnath/ArchitectureComponent/blob/job_schedular/img/job1.jpg)



![](https://github.com/anjandebnath/ArchitectureComponent/blob/job_schedular/img/job2.png)

### JobService

**JobService** is an android service component with callback methods which the **JobSchedule** calls when a job needs to be run.
That means your **background job** code needs to be added to **callback methods of JobService**.

### Scheduling Job (Job Scheduler)
To schedule a job, first you need to get JobScheduler instance by calling getSystemService on the context object passing JOB_SCHEDULER_SERVICE argument to it.

### JobInfo
JobInfo.Builder has various setter methods which allow you to define your Job.


### How this example work?

From Firebase real time database fetch data periodically and store into local database.
[link1](http://www.zoftino.com/android-job-scheduler-example) and [Firebase](https://www.androidhive.info/2016/10/android-working-with-firebase-realtime-database/)