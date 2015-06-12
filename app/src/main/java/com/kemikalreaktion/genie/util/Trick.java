package com.kemikalreaktion.genie.util;

public class Trick {
    private final int id;
    private final String name;
    private String[] imageURL;
    private boolean favorite;

    public Trick(int id, String name) {
        this.id = id;
        this.name = name;
        this.favorite = false;
    }

    public Trick(int id, String name, boolean favorite) {
        this.id = id;
        this.name = name;
        this.favorite = favorite;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String[] getImages() {
        return imageURL;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public String toString() {
        return name;
    }
}
