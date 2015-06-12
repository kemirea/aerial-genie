package com.kemikalreaktion.genie.core;

import android.app.Application;

import com.kemikalreaktion.genie.util.Trick;
import com.kemikalreaktion.genie.util.TrickCatalog;

import java.util.ArrayList;

public class GenieManager extends Application {
    private static GenieManager instance;
    private TrickCatalog trickCatalog;

    public GenieManager() {
        instance = this;
        trickCatalog = new TrickCatalog();

        TrickCatalog.generateTestCatalog();
    }

    public static GenieManager getInstance() {
        if (null == instance) {
            instance = new GenieManager();
        }
        return instance;
    }
}
