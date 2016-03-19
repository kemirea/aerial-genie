package com.kemikalreaktion.genie.util;

public class Trick {
    private final int id;
    private final String name;
    private String img;                 // this will be an array of some sort in the future
    private boolean favorite;

    public Trick(int id, String name) {
        this.id = id;
        this.name = name;
        this.favorite = false;
    }

    // this should be deprecated
    // favorites will be tracked through db
    public Trick(int id, String name, boolean favorite) {
        this.id = id;
        this.name = name;
        this.favorite = favorite;
    }

    public Trick(int id, String name, String img) {
        this.id = id;
        this.name = name;
        this.img = img;
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != Trick.class) {
            return false;
        }

        return (this.getId() == ((Trick) o).getId());
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getImages() {
        return img;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public String toString() {
        return name;
    }
}
