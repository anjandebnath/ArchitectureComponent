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


### Branch Names:

- master (**Room and Live data**)
- paging_library (**Paging library**)
- job_schedular (**Job Schedular**)
- accessibility_hook (**Custom view and accessibility**)
- nav_drawer (**Navigation Drawer**)
- user_preference (**Setting preference and menu**)


### Espresso

Espresso is a testing framework for Android to make it easy to write reliable user interface tests.

**Espresso has basically three components:**

- ViewMatchers - allows to find view in the current view hierarchy

- ViewActions - allows to perform actions on the views

- ViewAssertions - allows to assert state of a view

**Base Espresso Test**

        onView(ViewMatcher)       
         .perform(ViewAction)     
           .check(ViewAssertion); 

**Steps involved in Espresso test**

![Steps](https://github.com/anjandebnath/ArchitectureComponent/blob/master/app/img/Espresso.jpeg)

    onView(withId(R.id.my_view))            // withId(R.id.my_view) is a ViewMatcher     // Arrange
            .perform(click())               // click() is a ViewAction                   // Action
            .check(matches(isDisplayed())); // matches(isDisplayed()) is a ViewAssertion // Assertion
            
            

![View Matcher](https://github.com/anjandebnath/ArchitectureComponent/blob/ui_test/app/img/matcher.png)  

![View Action](https://github.com/anjandebnath/ArchitectureComponent/blob/ui_test/app/img/action.png)     

![View Assertion](https://github.com/anjandebnath/ArchitectureComponent/blob/ui_test/app/img/assertion.png)     
            
 [Useful link](https://medium.com/mindorks/android-testing-part-1-espresso-basics-7219b86c862b)    