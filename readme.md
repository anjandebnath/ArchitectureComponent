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
- Display notifications, toasts, and snackbar messages
- Schedule a background task using JobScheduler
- Efficiently run jobs in the background

### App data and files

- [x] Define data using Room entities
- [x] Access Room database with data access object (DAO)
- [x] Observe and respond to changing data using LiveData
- [x] Use a Repository to handle data operations
- Read and parse raw resources or asset files
- Create persistent preference data from user input
- Change the behavior of the app based on user preferences

[Room](https://android.jlelse.eu/pre-populate-room-database-6920f9acc870)
[Room Tips](https://medium.com/androiddevelopers/7-pro-tips-for-room-fbadea4bfbd1)
[Room Relations](http://androidkt.com/database-relationships/)
[M:N relation](https://android.jlelse.eu/android-architecture-components-room-relationships-bf473510c14a)

[LiveData](http://jensklingenberg.de/learn-how-to-use-livedata/)


### Branch Names:

- master (**Room and Live data**)
- paging_library (**Paging library**)
- job_schedular (**Job Schedular**)
- accessibility_hook (**Custom view and accessibility**)
- nav_drawer (**Navigation Drawer**)
- user_preference (**Setting preference and menu**)
- notifivation (**Notification related**)


### Steps:

- Create a notification channel
    On Android-powered devices running Android 8.0 (API level 26) or higher, notification channels that you create in your app appear as **Categories** under **App notifications** in the device **Settings** app.
    ![image](https://github.com/anjandebnath/ArchitectureComponent/blob/feature/notifivation/app/img/channel.png)


