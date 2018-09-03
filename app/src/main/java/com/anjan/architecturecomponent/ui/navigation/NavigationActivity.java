package com.anjan.architecturecomponent.ui.navigation;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.anjan.architecturecomponent.R;

/**
 * Created by Anjan Debnath on 9/3/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public class NavigationActivity extends AppCompatActivity{

    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;

    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_navigation);

        //region 1.Set the toolbar as the action bar
        //end region
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //region 2.Add the nav drawer button
        //endregion
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);


        mDrawerLayout = findViewById(R.id.drawer_layout);



        //region 5.Handle navigation click events
        //endregion
        navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        //creating fragment object
                        int id = menuItem.getItemId();
                        switch(id)
                        {
                            case R.id.nav_profile:
                                Toast.makeText(NavigationActivity.this, "My Profile",Toast.LENGTH_SHORT).show();
                                break;

                            case R.id.nav_settings:
                                Toast.makeText(NavigationActivity.this, "Settings",Toast.LENGTH_SHORT).show();
                                break;

                            case R.id.nav_edit_profile:
                                Toast.makeText(NavigationActivity.this, "Edit Profile",Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.nav_backup:
                                Toast.makeText(NavigationActivity.this, "Backup",Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                return true;
                        }


                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // set title as fragment
                        setTitle(menuItem.getTitle());
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        return true;
                    }
                });


        // Insert the fragment by replacing any existing fragment
        MyProfileFragment myProfileFragment = MyProfileFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content_frame,  myProfileFragment, "myProfile")
                .commit();

        SettingsPreferenceFragment settingsFragment = SettingsPreferenceFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content_frame, settingsFragment, "settings")
                .commit();

        //region 4.Listen for open/close events and other state changes
        //endregion
        /*mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );*/


    }


    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open,  R.string.drawer_close);
    }


    // region 3.Open the drawer when the button is tapped
    // endregion
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }




    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
