package com.anjan.architecturecomponent.custom_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anjan.architecturecomponent.R;

/**
 * Created by Anjan Debnath on 8/30/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public class ColorOptionsView extends LinearLayout{


    public ColorOptionsView(Context context) {
        super(context);
    }

    @SuppressLint("ResourceAsColor")
    public ColorOptionsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //set orientation and gravity
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        // set layout of the custom view
        LayoutInflater.from(context).inflate(R.layout.view_color_options, this, true);


        String titleText;
        int valueColor = 0;

        // get styleable attrs
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ColorOptionsView, 0, 0);

        try{
            titleText = a.getString(R.styleable.ColorOptionsView_titleText);
            valueColor = a.getColor(R.styleable.ColorOptionsView_valueColor, android.R.color.holo_blue_light);
        }finally{
            a.recycle();
        }


        // Throw an exception if required attributes are not set
        if (titleText == null) {
            throw new RuntimeException("No title provided");
        }


        init(titleText, valueColor);

        installAccessibilityDelegate();

    }

    private void init(String title, int valueColor) {

        View view = findViewById(R.id.color_options_view);
        TextView titleView = (TextView) findViewById(R.id.color_options_view_textView);
        ImageView colorView = (ImageView) findViewById(R.id.color_options_view_imageView);

        view.setBackgroundColor(valueColor);
        titleView.setText(title);

    }


    @Override
    public boolean onKeyUp (int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
            sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED);
            return true;
        }
     return false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        // Listening for the down and up touch events
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return true;

            case MotionEvent.ACTION_UP:
                sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_CLICKED);
                performClick(); // Call this method to handle the response, and
                // thereby enable accessibility services to
                // perform this action for a user who cannot
                // click the touchscreen.
                return true;

        }
        return false; // Return false for other touch events

    }


    // Because we call this from onTouchEvent, this code will be executed for both
    // normal touch events and for when the system calls this using Accessibility
    @Override
    public boolean performClick() {
        // Calls the super implementation, which generates an AccessibilityEvent
        // and calls the onClick() listener on the view, if any
        super.performClick();
        sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_CLICKED);
        // Handle the action for the custom click here
        clickBackColor();

        return true;
    }

    private void clickBackColor() {
        Toast.makeText(getContext(), "Clicked on color", Toast.LENGTH_SHORT).show();
    }



    @Override
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        // Call the super implementation to populate its text to the event, which
        // calls onPopulateAccessibilityEvent() on API Level 14 and up.
        boolean completed = super.dispatchPopulateAccessibilityEvent(event);

        // In case this is running on a API revision earlier that 14, check
        // the text content of the event and add an appropriate text
        // description for this custom view:
        CharSequence text = getText();
        if (!TextUtils.isEmpty(text)) {
            event.getText().add(text);
            return true;
        }
        return completed;
    }

    private void installAccessibilityDelegate() {
        // The accessibility delegate enables customizing accessibility behavior
        // via composition as opposed as inheritance. The main benefit is that
        // one can write a backwards compatible application by setting the delegate
        // only if the API level is high enough i.e. the delegate is part of the APIs.
        // The easiest way to achieve that is by using the support library which
        // takes the burden of checking API version and knowing which API version
        // introduced the delegate off the developer.
        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegateCompat() {


            @Override
            public void onPopulateAccessibilityEvent(View host, AccessibilityEvent event) {
                super.onPopulateAccessibilityEvent(host, event);
                // We call the super implementation to populate its text for the
                // event. Then we add our text not present in a super class.
                // Very often you only need to add the text for the custom view.
                CharSequence text = getText();
                if (!TextUtils.isEmpty(text)) {
                    event.getText().add(text);
                }
            }
            @Override
            public void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
                super.onInitializeAccessibilityEvent(host, event);
                // Note that View.onInitializeAccessibilityNodeInfo was introduced in
                // ICS and we would like to tweak a bit the text that is reported to
                // accessibility services via the AccessibilityNodeInfo.
                if (event.getEventType() == AccessibilityEventCompat.TYPE_VIEW_SCROLLED) {
                    event.setFromIndex(0);
                }
            }
            @Override
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
                super.onInitializeAccessibilityNodeInfo(host, info);
                // A custom action description. For example, you could use "pause"
                // to have TalkBack speak "double-tap to pause."
                CharSequence description = host.getResources().getText(R.string.my_click_desc);
                AccessibilityNodeInfoCompat.AccessibilityActionCompat customClick = new AccessibilityNodeInfoCompat.AccessibilityActionCompat(
                        AccessibilityNodeInfoCompat.ACTION_CLICK, description);
                info.addAction(customClick);
            }
        });
    }


    public boolean isChecked(){
        return true;
    }


    public CharSequence getText(){
        return "Hello";
    }


}
