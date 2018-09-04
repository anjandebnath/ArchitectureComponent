package com.anjan.architecturecomponent.ui.navigation;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.anjan.architecturecomponent.R;

/**
 * Created by Anjan Debnath on 9/3/2018.
 * Copyright (c) 2018, W3 Engineers Ltd. All rights reserved.
 */
public class NavigationActivity extends AppCompatActivity{

    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;

    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;
    private MenuItem menuItem;
    // index to identify current nav menu item
    public static int navItemIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_navigation);

        if (savedInstanceState == null) {
            navItemIndex = 0;
        }

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

        drawerToggle = setupDrawerToggle();

        //region 4.Listen for open/close events and other state changes
        //endregion
        mDrawerLayout.addDrawerListener(drawerToggle);



        //region 5.Handle navigation click events and set default Fragment
        //endregion
        navigationView =  findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.post(new Runnable() {
            @Override
            public void run() {
                navItemIndex = 0;
                android.app.FragmentTransaction tx = getFragmentManager().beginTransaction();
                tx.replace(R.id.content_frame, MyProfileFragment.newInstance());
                tx.commit();
            }
        });


        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        selectDrawerItem(menuItem);
                        return true;
                    }
                });




    }

    private void selectDrawerItem(MenuItem menuItem){

        Fragment fragment = null;
        Class fragmentClass = null;

        //creating fragment object
        int id = menuItem.getItemId();
        switch(id)
        {
            case R.id.nav_profile:
                navItemIndex = 0;
                fragmentClass = MyProfileFragment.class;
                Toast.makeText(NavigationActivity.this, "My Profile",Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_settings:
                navItemIndex = 1;
                fragmentClass = SettingsPreferenceFragment.class;
                Toast.makeText(NavigationActivity.this, "Settings",Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_edit_profile:
                navItemIndex = 2;
                fragmentClass = EditProfileFragment.class;
                Toast.makeText(NavigationActivity.this, "Edit Profile",Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_backup:
                navItemIndex = 3;
                fragmentClass = BackupFragment.class;
                Toast.makeText(NavigationActivity.this, "Backup",Toast.LENGTH_SHORT).show();
                break;

            default:
                navItemIndex = 0;
                fragmentClass = MyProfileFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();


        // set item as selected to persist highlight
        menuItem.setChecked(true);
        // set title as fragment
        setTitle(menuItem.getTitle());
        // close drawer when item is tapped
        mDrawerLayout.closeDrawers();

        invalidateOptionsMenu();

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
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        // show menu only when home fragment is selected
        if (navItemIndex == 1) {
            getMenuInflater().inflate(R.menu.settings_view, menu);
        }

        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
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
