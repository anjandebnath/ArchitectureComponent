package com.anjan.architecturecomponent.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.anjan.architecturecomponent.ui.main.MainActivity;

/**
 * Created by Anjan Debnath on 1/14/2019.
 * Copyright (c) 2019, W3 Engineers Ltd. All rights reserved.
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent;
        intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();




    }

}
