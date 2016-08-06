package com.kemikalreaktion.genie.core;

import android.app.Application;
import android.content.ContentResolver;
import android.util.Log;

import com.kemikalreaktion.genie.R;
import com.kemikalreaktion.genie.Tag;
import com.kemikalreaktion.genie.db.XmlParser;
import com.kemikalreaktion.genie.util.TrickCatalog;

import org.xmlpull.v1.XmlPullParserException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class GenieManager extends Application {
    private static final String TAG = Tag.APP_TAG + "Manager";
    private static GenieManager instance;

    // deprecate this
    private TrickCatalog trickCatalog;

    public GenieManager() {
        Log.v(TAG, "GenieManager");
        instance = this;
        trickCatalog = new TrickCatalog();
    }

    protected void loadData() {
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

    public static ContentResolver getResolver() {
        return getInstance().getContentResolver();
    }
}
