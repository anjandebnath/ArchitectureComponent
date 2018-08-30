This project covers the syllabus of `Associate Android Developer Certification`

[Exam Content](https://developers.google.com/training/certification/associate-android-developer/#exam-content) is here and [x] `topics are covered here`.


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


## Accessibility

- When labeling graphical elements, such `ImageView` and `ImageButton` objects, use the `android:contentDescription` XML attribute for static elements and the `setContentDescription()` method for dynamic elements.

             <ImageButton
               ..
               ..
               android:contentDescription="@string/share"
               />
               
               
             ImageView playPauseImageView = new ImageView(...);
             playPauseImageView.setImageResource(R.drawable.ic_pause);            
             playPauseImageView.setContentDescription(getString(R.string.pause));
             
- When labeling editable elements, such as `EditText` objects, use the `android:hint` XML attribute for static elements and the `setHint()` method for dynamic elements to indicate each element's purpose.  

            <EditText
               ..
               ..
               android:hint="Director"
               />    
               
- If your app is installed on a device running Android 4.2 (API level 17) or higher, use the `android:labelFor` attribute when labeling View objects that serve as content labels for other View objects. 
  **Editable items in an app allow users to enter text. Each editable item should have a descriptive label stating its purpose.**
  
            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:orientation="vertical">
                <TextView
                   ..
                   android:text="You can add User name"
                   android:labelFor="@id/email_subject" />
                <EditText
                    android:id = "@+id/email_subject"
                    android:hint = "Username"
                    .. />
            </LinearLayout>  
  
               

- `Note: Accessibility services automatically capture the text that appears in TextView objects, so you usually don't need to label these elements.`       

- `Note: Many accessibility services, such as TalkBack and BrailleBack, automatically announce an element's type after announcing its label, so you shouldn't include element types in your labels. For example, "submit" is a good label for a Button object, but "submitButton" isn't a good label.`


[link](https://codelabs.developers.google.com/codelabs/basic-android-accessibility/index.html?index=..%2F..%2Findex#0) of details