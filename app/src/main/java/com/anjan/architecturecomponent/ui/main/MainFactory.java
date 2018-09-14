package com.anjan.architecturecomponent.ui.main;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Anjan Debnath on 9/14/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public class MainFactory implements ViewModelProvider.Factory {

    private Context mContext;
    private Application mApplication;

    public MainFactory(Context context, Application application){
        mContext = context;
        mApplication = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MoviesViewModel.class)) {
            return (T) new MoviesViewModel(mApplication);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
