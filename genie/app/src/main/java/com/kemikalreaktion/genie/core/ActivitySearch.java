package com.kemikalreaktion.genie.core;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;

import com.kemikalreaktion.genie.R;
import com.kemikalreaktion.genie.Tag;

public class ActivitySearch extends ActionBarActivity {
    private static final String TAG = Tag.APP_TAG + "ActivitySearch";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleIntent(getIntent());
        Log.v(TAG, "search activity created!");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        Log.v(TAG, "handleIntent: " + intent.toString());
        if (Intent.ACTION_SEARCH.equals(intent)) {
            String query = intent.getStringExtra(SearchManager.QUERY);
        }
    }
}
