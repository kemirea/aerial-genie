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
//       it may also need to be renamed to better describe its functionality
public class TrickCatalog {
    private static final String TAG = Tag.APP_TAG + ".TrickCatalog";
    private static TrickCatalog instance;

    public TrickCatalog() {
        instance = this;
    }

    public TrickCatalog getInstance() {
        if (instance == null) {
            instance = new TrickCatalog();
        }
        return instance;
    }

    public static Trick findById(int id) {
        String clause = Tag.MOVESET_ID + " = ?";
        String[] args = {Integer.toString(id)};
        ArrayList<Trick> tricks = getTricks(clause, args, null);
        if (tricks != null && !tricks.isEmpty()) {
            return tricks.get(0);
        }
        else {
            return null;
        }
    }

    private static ArrayList<Trick> getTricks(String selectionClause,
                                              String[] selectionArgs,
                                              String sortOrder) {
        ArrayList<Trick> trickList = new ArrayList<>();
        Uri uri = Uri.parse(Tag.CONTENT_AUTHORITY + Tag.MOVESET_TABLE_NAME);
        String[] projection = {Tag.MOVESET_ID,
                Tag.MOVESET_NAME,
                Tag.MOVESET_IMG};
        Cursor mCursor = GenieManager.getResolver().query(
                uri, projection, selectionClause, selectionArgs, sortOrder);

        if (mCursor != null) {
            int idIndex = mCursor.getColumnIndex(Tag.MOVESET_ID);
            int nameIndex = mCursor.getColumnIndex(Tag.MOVESET_NAME);
            int imgIndex = mCursor.getColumnIndex(Tag.MOVESET_IMG);
            while (mCursor.moveToNext()) {
                int id = mCursor.getInt(idIndex);
                String name = mCursor.getString(nameIndex);
                String img = mCursor.getString(imgIndex);
                Trick mTrick = new Trick(id, name, img);
                trickList.add(mTrick);
            }
            mCursor.close();
        }

        return trickList;
    }

    /**
     * Returns the list of all tricks in catalog
     * @return      the list of all tricks in TrickCatalog
     */
    public static ArrayList<Trick> getAllTricks() {
        return getTricks(null, null, null);
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

        /*for (Trick trick : allTricks.values()) {
            if (trick.isFavorite()) {
                favorites.add(trick);
            }
        }*/

        return favorites;
    }

    /**
     * Search TrickCatalog for all Tricks that match the search query
     * @param   query   search query
     * @return          a list of tricks matching the search query
     */
    public static ArrayList<Trick> findWithName(String query) {
        ArrayList<Trick> results = new ArrayList<>();
        if (query == null || query.isEmpty()) {
            // if no search argument, just return the empty array.
            return results;
        }

        String clause = Tag.MOVESET_NAME + " LIKE ?";
        String[] args = {"%" + query + "%"};
        results = getTricks(clause, args, null);
        if (results != null && !results.isEmpty()) {
            return results;
        }
        else {
            return null;
        }
    }
}
