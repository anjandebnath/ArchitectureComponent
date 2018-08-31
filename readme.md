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
- [x] Add accessibility hooks to a custom view
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



### How to create Custom View

Sometimes you need to set a few of the same properties on the same types of views throughout your app. 
In that case `you could make a layout and use the include tag to include it` in every layout you need it in. 
So you can re-use it wherever you need it.

That may be fine, but what `if you want to change a property on that view in one place it’s included?` 
For example, you want to change the text colour of a TextView inside that layout. 
`Since it’s in the included layout, you can either change it for every place it’s used or you have to do it programmatically.`
You can’t set it through the include tag.

To solve this problem, you can create a custom view by extending one of the existing views.


### Coding Format

**merge tag in xml layout**

I use the `merge tag` so the layout can be `inflated directly` into my view, which is a LinearLayout.

At first you can set the layout with *LinearLayout* and after placing the view components on the suitable place just remove the *layout tag with merge tag*.

**attrs.xml in values folder to set styleable**

In my CustomView layout I have TextViews for a title and subtitle. I want to be able to set these in any layout where they are included, so I have to add custom attributes in my attrs.xml file.


### Example

- `MovieListView.java` is the Custom view that will be set on `RecyclerView Adapter`.

- `ColorOptionsView.java` is the Custom view that will be used to get idea of `accessibility hook`.



### How to enable Accessibility hook on Custom View

### 1. Implement accessibility API methods

_@Override below methods_

 - `sendAccessibilityEvent()`
 - `sendAccessibilityEventUnchecked()`
 - `dispatchPopulateAccessibilityEvent()`
 - `onPopulateAccessibilityEvent()`
 - `onInitializeAccessibilityEvent()`
 - `onInitializeAccessibilityNodeInfo()`
 - `onRequestSendAccessibilityEvent()`
 
 _**Here the important fact is you should implement the following accessibility methods for your custom view class**_
 
 _@Override below methods always_
 
 - `dispatchPopulateAccessibilityEvent()`
 - `onPopulateAccessibilityEvent()`
 - `onInitializeAccessibilityEvent()`
 - `onInitializeAccessibilityNodeInfo()`
 
 _When we will use them_
 
 **1.sendAccessibilityEvent()** : 
 
     if a user clicks on your custom view, you will be required to override `onTouchEvent(MotionEvent event)`, then apply switch case on event.getAction(), then at case `MotionEvent.ACTION_UP` add `sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_CLICKED).`
     
     You typically do not need to implement this method unless you are creating a custom view.
     
   **Send accessibility events type:** Depending on the specifics of your custom view, it may need to send `AccessibilityEvent objects` at a different times or for events not handled by the default implementation. The View class provides a default implementation for these event types:
     
     Starting with API Level 4:
     TYPE_VIEW_CLICKED
     TYPE_VIEW_LONG_CLICKED
     TYPE_VIEW_FOCUSED
     
     Starting with API Level 14:
     TYPE_VIEW_SCROLLED
     TYPE_VIEW_HOVER_ENTER
     TYPE_VIEW_HOVER_EXIT
     
     In general, you should send an AccessibilityEvent whenever the content of your custom view changes.
     
     For example, if you are implementing a custom slider bar that allows a user to select a numeric value by pressing the left or right arrows, your custom view should emit an event of type TYPE_VIEW_TEXT_CHANGED whenever the slider value changes. 
     
     
 
 **2.sendAccessibilityEventUnchecked()** : 
 
     same as sendAccessibilityEvent(), regardless of the fact if or if not accessibility is enabled in the system settings.
       
     You typically do not need to implement this method for a custom view.


 **3.dispatchPopulateAccessibilityEvent()** :
 
     
     If you are creating a custom view component, you must provide some information about the content and characteristics of the view.  
     
     The minimum requirement for providing information to accessibility services with a custom view is to implement dispatchPopulateAccessibilityEvent() 
     
     and makes your custom view compatible with accessibility services on Android 1.6 (API Level 4) and higher.
     
     
 **4.onPopulateAccessibilityEvent()** :
 
     Use the onPopulateAccessibilityEvent() method specifically for adding or modifying the text content of the event, which is turned into audible prompts by accessibility services such as TalkBack.
     
    
     This method sets the spoken text prompt of the AccessibilityEvent for your view. 
     
     This method is also called if the view is a child of a view which generates an accessibility event. 
     
     For example, a Textview with content description “Text View” was clicked, a sound prompt “Text View was clicked” may be triggered if TYPE_VIEW_CLICKED was dispatched.    


 **5.onInitializeAccessibilityEvent()**:
 
    Use the onInitializeAccessibilityEvent() method for populating additional information about the event, such as the selection state of the view.
 
 
 **6.onInitializeAccessibilityNodeInfo()**:
 
      Implement the onInitializeAccessibilityNodeInfo() method. 
      
      The AccessibilityNodeInfo objects populated by this method are used by accessibility services to investigate the view hierarchy that generated an accessibility event after receiving that event, to obtain a more detailed context information and provide appropriate feedback to users.
      
      
   **Important Note**
   _ViewCompat.setAccessibilityDelegate() is used to override the 3 methods._
 
### Handle custom touch events

- To handle these requirements (`maintain compatibility with accessibility services`) in an efficient way, your code should override the `performClick()` method, which must call the super implementation of this method and then execute whatever actions are required by the click event. When the custom click action is detected, that code should then call your `performClick()` method. 

- Be careful not to call `performClick()` twice if you also have it in `onTouchEvent(MotionEvent event)`.






















