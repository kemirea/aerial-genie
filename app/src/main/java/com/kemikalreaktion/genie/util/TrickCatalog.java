package com.kemikalreaktion.genie.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class TrickCatalog {
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

    /**
     * add trick to catalog
     * return true if successful
     */
    public static boolean addTrick(Trick trick) {
        if (null != allTricks.put(trick.getId(), trick)) {
            return true;
        }
        else {
            return false;
        }
    }

    public static Trick findById(int id) {
        return allTricks.get(id);
    }

    /**
     * Returns the list of all tricks in catalog
     * @return      the list of all tricks in TrickCatalog
     */
    public static ArrayList<Trick> getAllTricks() {
        return new ArrayList<>(allTricks.values());
    }

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
            addTrick(new Trick(idCount, name));
            idCount++;
        }

        return instance;
    }
}
