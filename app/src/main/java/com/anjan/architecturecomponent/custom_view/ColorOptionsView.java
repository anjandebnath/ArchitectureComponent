package com.anjan.architecturecomponent.custom_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

    }

    private void init(String title, int valueColor) {

        View view = findViewById(R.id.color_options_view);
        TextView titleView = (TextView) findViewById(R.id.color_options_view_textView);
        ImageView colorView = (ImageView) findViewById(R.id.color_options_view_imageView);

        view.setBackgroundColor(valueColor);
        titleView.setText(title);

    }

    public ColorOptionsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ColorOptionsView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    //region important

    //endregion important
    @Override
    public void sendAccessibilityEvent(int eventType) {
        super.sendAccessibilityEvent(eventType);
    }

    @Override
    public void sendAccessibilityEventUnchecked(AccessibilityEvent event) {
        super.sendAccessibilityEventUnchecked(event);
    }

    @Override
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        return super.dispatchPopulateAccessibilityEvent(event);
    }

    @Override
    public void onPopulateAccessibilityEvent(AccessibilityEvent event) {
        super.onPopulateAccessibilityEvent(event);
    }


    //region semi important

    //endregion semi important

    @Override
    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
        super.onInitializeAccessibilityEvent(event);
    }

    @Override
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
    }

    @Override
    public boolean onRequestSendAccessibilityEvent(View child, AccessibilityEvent event) {
        return super.onRequestSendAccessibilityEvent(child, event);
    }


}
