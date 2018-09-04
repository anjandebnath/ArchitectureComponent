package com.anjan.architecturecomponent.ui.navigation;

import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anjan.architecturecomponent.R;

/**
 * Created by Anjan Debnath on 9/3/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public class EditProfileFragment extends Fragment {

    public static EditProfileFragment newInstance(){
        return new EditProfileFragment();
    }

    public EditProfileFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_edit, container, false);
    }
}
