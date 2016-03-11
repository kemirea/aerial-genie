package com.kemikalreaktion.genie.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import com.kemikalreaktion.genie.Tag;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = Tag.APP_TAG + ".DBHelper";
    private static final String DB_NAME = "Move List";
    private static final int DB_VERSION = 1;

    // sql statement for moveset table creation
    private static final String CREATE_MOVESET_DB = "create table moveset ("
            + BaseColumns._ID + " integer primary key, "
            + "id integer unique not null, "
            + "name text not null, "
            + "img text"
            + ");";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v(TAG, "onCreate");

        // don't know how to trigger an update so just drop the table
        // and recreate from scratch every time for now
        db.execSQL("DROP TABLE IF EXISTS moveset");
        db.execSQL(CREATE_MOVESET_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion);
        Log.d(TAG, "Method not implemented, do nothing for now");

        Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                 + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS moveset");
        onCreate(db);
    }
}