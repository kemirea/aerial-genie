package com.kemikalreaktion.genie.core;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.kemikalreaktion.genie.R;
import com.kemikalreaktion.genie.fragment.FragmentNavigationDrawer;

/**
 * ActivityTest
 * dummy activity for testing layout changes
 */
public class ActivityTest extends AppCompatActivity implements FragmentNavigationDrawer.NavigationDrawerCallbacks {

    private FragmentNavigationDrawer mFragmentNavigationDrawer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);

        // set recycler view layout manager
        //RecyclerView.LayoutManager mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // set recycler view adapter
        TrickViewAdapter mAdapter = new TrickViewAdapter();
        mRecyclerView.setAdapter(mAdapter);

        // Set up the drawer.
        /*mFragmentNavigationDrawer.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));*/
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        switch (position) {
            case 0:
                // Browse Moves
                break;
            case 1:
                // Your Moves
                break;
            case 2:
                // About
                break;
        }
    }
}
