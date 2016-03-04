package com.kemikalreaktion.genie.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import com.kemikalreaktion.genie.Tag;

/**
 * Created by greta.chang on 3/3/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = Tag.APP_TAG + ".DBHelper";
    private static final String DB_NAME = "Move List";
    private static final int DB_VERSION = 1;

    // sql statements for database creation
    private static final String CREATE_MOVES_DB = "create table Moves ("
            + BaseColumns._ID + " integer primary key auto_increment, "
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
        db.execSQL(CREATE_MOVES_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion);
        Log.d(TAG, "Method not implemented, do nothing for now");

        Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                 + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS Moves");
        onCreate(db);
    }
}