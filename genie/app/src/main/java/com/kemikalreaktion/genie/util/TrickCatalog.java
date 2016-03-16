package com.kemikalreaktion.genie.util;

import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.kemikalreaktion.genie.Tag;
import com.kemikalreaktion.genie.core.GenieManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

// TODO: class should be updated to retrieve data from DatabaseContentProvider
public class TrickCatalog {
    private static final String TAG = Tag.APP_TAG + ".TrickCatalog";
    private static TrickCatalog instance;
    private static HashMap<Integer, Trick> allTricks;

    public TrickCatalog() {
        instance = this;
        allTricks = new HashMap<Integer, Trick>();
    }

    public TrickCatalog getInstance() {
        if (instance == null) {
            instance = new TrickCatalog();
        }
        return instance;
    }

    public static Trick findById(int id) {
        return allTricks.get(id);
    }

    /**
     * Returns the list of all tricks in catalog
     * @return      the list of all tricks in TrickCatalog
     */
    public static ArrayList<Trick> getAllTricks() {
        ArrayList<Trick> trickList = new ArrayList<>();
        Uri uri = Uri.parse(Tag.CONTENT_AUTHORITY + Tag.MOVESET_TABLE_NAME);
        String[] projection = {Tag.MOVESET_ID,
                               Tag.MOVESET_NAME,
                               Tag.MOVESET_IMG};
        Cursor mCursor = GenieManager.getResolver().query(uri, projection, null, null, null);

        if (mCursor != null) {
            int idIndex = mCursor.getColumnIndex(Tag.MOVESET_ID);
            int nameIndex = mCursor.getColumnIndex(Tag.MOVESET_NAME);
            int imgIndex = mCursor.getColumnIndex(Tag.MOVESET_IMG);
            while (mCursor.moveToNext()) {
                int id = mCursor.getInt(idIndex);
                String name = mCursor.getString(nameIndex);
                String img = mCursor.getString(imgIndex);
                Log.i(TAG, "got new trick: " + name);

                Trick mTrick = new Trick(id, name, img);
                trickList.add(mTrick);
            }
            mCursor.close();
        }

        return trickList;
    }

    // TODO: add favorited property? or create new table for favorites?
    //       latter is probably better option so that tricks DB can be
    //       updated without erasing favorites
    /**
     * Returns a list of favorited tricks
     * @return      a list of favorited tricks
     */
    public static ArrayList<Trick> getFavoriteTricks() {
        ArrayList<Trick> favorites = new ArrayList<>();

        for (Trick trick : allTricks.values()) {
            if (trick.isFavorite()) {
                favorites.add(trick);
            }
        }

        return favorites;
    }

    /**
     * Search TrickCatalog for all Tricks that match the search query
     * @param   query   search query
     * @return          a list of tricks matching the search query
     */
    public static ArrayList<Trick> findWithName(String query) {
        ArrayList<Trick> results = new ArrayList<>();

        //Search for tricks beginning with query first
        for (Trick trick : allTricks.values() ) {
            if (trick.getName().toLowerCase().startsWith(query.toLowerCase())) {
                results.add(trick);
            }
        }

        // Search again for tricks containing query
        for(Trick trick : allTricks.values()) {
            if (trick.getName().toLowerCase().contains(query.toLowerCase())
                    && !results.contains(trick)) {
                results.add(trick);
            }
        }

        return results;
    }

    public static TrickCatalog generateTestCatalog() {
        String nameList[] = {"Fireman Spin",
                             "Cupid",
                             "Genie",
                             "Jasmine",
                             "Superman",
                             "Basic Invert",
                             "Wrist Seat",
                             "Butterfly",
                             "Ballerina",
                             "Twisted Ballerina"};
        int idCount = 0;

        for (String name : nameList) {
            idCount++;
        }

        return instance;
    }
}
