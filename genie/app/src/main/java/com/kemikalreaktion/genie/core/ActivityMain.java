package com.kemikalreaktion.genie.core;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;

import com.kemikalreaktion.genie.R;
import com.kemikalreaktion.genie.fragment.FragmentAbout;
import com.kemikalreaktion.genie.fragment.FragmentBrowseMoves;
import com.kemikalreaktion.genie.fragment.FragmentMain;
import com.kemikalreaktion.genie.fragment.FragmentNavigationDrawer;
import com.kemikalreaktion.genie.fragment.FragmentSearchResults;
import com.kemikalreaktion.genie.fragment.FragmentYourMoves;


public class ActivityMain extends ActionBarActivity
        implements FragmentNavigationDrawer.NavigationDrawerCallbacks,
        SearchView.OnQueryTextListener {

    private static final String TAG = "ActivityMain";
    private static final boolean DBG = true;

    private FragmentMain fMain;
    private FragmentBrowseMoves fBrowseMoves;
    private FragmentYourMoves fYourMoves;
    private FragmentAbout fAbout;
    private FragmentSearchResults fSearchResults;

    private FragmentNavigationDrawer mFragmentNavigationDrawer;
    private CharSequence mTitle;
    private SearchView searchView;
    private MenuItem menuSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fMain = FragmentMain.newInstance();
        fBrowseMoves = FragmentBrowseMoves.newInstance(getString(R.string.title_browse_tricks));
        fYourMoves = FragmentYourMoves.newInstance(getString(R.string.title_your_tricks));
        fAbout = FragmentAbout.newInstance(getString(R.string.title_about));
        fSearchResults = new FragmentSearchResults();

        mFragmentNavigationDrawer = (FragmentNavigationDrawer)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mFragmentNavigationDrawer.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        if (DBG) Log.i(TAG, "onNavigationDrawerItemSelected=" + position);

        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        switch (position) {
            case 0:
                // Browse Moves
                navigateTo(fBrowseMoves);
                break;
            case 1:
                // Your Moves
                navigateTo(fYourMoves);
                break;
            case 2:
                // About
                navigateTo(fAbout);
                break;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            if (fSearchResults.isVisible()) {
                fSearchResults.getSearchResults(query);
            }
        }
    }

    // Search box text has changed, update shown tricks
    public boolean onQueryTextChange(String newText) {
        Log.v(TAG, "query text changed=" + newText);

        if (fSearchResults.isVisible()) {
            fSearchResults.getSearchResults(newText);
        }
        return true;
    }

    // User submitted search query
    public boolean onQueryTextSubmit(String query) {
        Log.v(TAG, "query text submit=" + query);

        if (fSearchResults.isVisible()) {
            fSearchResults.getSearchResults(query);
            searchView.clearFocus();
        }
        return true;
    }

    private void navigateTo(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container_main, fragment)
                .commit();
    }

    public void setTitle(String title) {
        mTitle = title;
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(mTitle);

    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mFragmentNavigationDrawer.isDrawerOpen() && fBrowseMoves.isVisible()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            restoreActionBar();

            // Associate searchable configuration with the SearchView
            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            menuSearch = menu.findItem(R.id.search);
            searchView = (SearchView) menu.findItem(R.id.search).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            MenuItemCompat.setOnActionExpandListener(menuSearch, new MenuItemCompat.OnActionExpandListener() {
                @Override
                public boolean onMenuItemActionExpand(MenuItem menuItem) {
                    navigateTo(fSearchResults);
                    return true;
                }

                @Override
                public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                    navigateTo(fBrowseMoves);
                    return true;
                }
            });
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        /*else if (id == R.id.search){
            navigateTo(fSearchResults);
        }*/
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (fSearchResults.isVisible()) {
            menuSearch.collapseActionView();
        }
        else {
            super.onBackPressed();
        }
    }

}
