package com.kemikalreaktion.genie.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;

import com.kemikalreaktion.genie.Tag;

import java.util.List;
import java.util.Objects;

public class DatabaseContentProvider extends ContentProvider {
    private static final String TAG = Tag.APP_TAG + ".ContentProvider";

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate");
        dbHelper = new DatabaseHelper(getContext());

        return db != null;
    }

    @Override
    public synchronized Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.d(TAG, "query");
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        List<String> uriSegments = uri.getPathSegments();
        for(String segment : uriSegments) {
            Log.i(TAG, uriSegments.indexOf(segment) + ": " + segment);
        }

        // check for table
        if (uriSegments.size() >=1) {
            builder.setTables(uriSegments.get(0));
        }

        // check for specific entry
        if (uriSegments.size() >= 2) {
            selection = selection + BaseColumns._ID + uri.getLastPathSegment();
        }

        db = dbHelper.getWritableDatabase();
        Cursor cursor = builder.query(db,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public synchronized Uri insert(Uri uri, ContentValues values) {
        String table = "";
        List<String> uriSegments = uri.getPathSegments();
        for(String segment : uriSegments) {
            Log.i(TAG, uriSegments.indexOf(segment) + ": " + segment);
        }

        // check for table
        if (uriSegments.size() >=1) {
            table = uriSegments.get(0);
        }

        db = dbHelper.getWritableDatabase();
        Log.d(TAG, "insert for " + table + " table");

        return uri.buildUpon()
                .appendEncodedPath(
                        Objects.toString(db.insert(table, null, values), null))
                .build();
    }

    @Override
    public synchronized int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Log.d(TAG, "update");

        return 0;
    }

    // doesn't do anything right now.
    @Override
    public synchronized int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.v(TAG, "delete");

        return 0;
    }

    @Override
    public synchronized String getType(Uri uri) {
        Log.d(TAG, "getType");

        return "";
    }
}