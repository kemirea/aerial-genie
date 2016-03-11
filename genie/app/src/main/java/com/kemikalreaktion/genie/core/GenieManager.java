package com.kemikalreaktion.genie.core;

import android.app.Application;
import android.util.Log;

import com.kemikalreaktion.genie.R;
import com.kemikalreaktion.genie.Tag;
import com.kemikalreaktion.genie.db.DatabaseContentProvider;
import com.kemikalreaktion.genie.db.XmlParser;
import com.kemikalreaktion.genie.util.Trick;
import com.kemikalreaktion.genie.util.TrickCatalog;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class GenieManager extends Application {
    private static final String TAG = Tag.APP_TAG + "Manager";
    private static GenieManager instance;

    // deprecate this
    private TrickCatalog trickCatalog;

    public GenieManager() {
        instance = this;
        trickCatalog = new TrickCatalog();

        TrickCatalog.generateTestCatalog();
    }

    public void loadData() {
        // read in xml data
        // we really only want to do this when the DB has been updated...
        try {
            XmlParser parser = new XmlParser(this);
            InputStream input = getResources().openRawResource(R.raw.moveslist);
            parser.parseInput(input);
        }
        catch (FileNotFoundException noFileException) {
            Log.e(TAG, "File not found. " + noFileException.getMessage());
        }
        catch (IOException ioException) {
            Log.e(TAG, "Encountered IOException. " + ioException.getMessage());
        }
        catch (XmlPullParserException xmlException) {
            Log.e(TAG, "Error parsing XML. " + xmlException.getMessage());
        }
    }

    public static GenieManager getInstance() {
        if (null == instance) {
            instance = new GenieManager();
        }
        return instance;
    }
}
