package com.kemikalreaktion.genie.db;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.kemikalreaktion.genie.Tag;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;

public class XmlParser {
    private static final String TAG = Tag.APP_TAG + ".XmlParser";
    private Context mContext;

    public XmlParser (Context context) {
        mContext = context;
    }

    public void parseInput(InputStream in) throws XmlPullParserException, IOException {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser parser = factory.newPullParser();
        parser.setInput(in, null);

        while (parser.next() != XmlPullParser.END_DOCUMENT) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            if (parser.getName().equals("moveset")) {
                // start parsing for moveset table
                parseMoveSet(parser);
            }
        }

        in.close();
    }

    // parse the list of moves
    private void parseMoveSet(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, null, "moveset");
        Log.d(TAG, "parseMoveSet");

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String tag = parser.getName();
            if ("move".equals(tag)) {
                parseMove(parser);
            }
            else {
                skip(parser);
            }
        }
    }

    // parse a move
    private void parseMove(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, null, "move");
        Log.d(TAG, "parseMove");
        Uri uriAuthority = Uri.parse(Tag.CONTENT_AUTHORITY + "moveset");
        ContentValues values = new ContentValues();
        String id = "";
        String name = "";
        String img = "";

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String tag = parser.getName();
            if ("id".equals(tag)) {
                id = parseText(parser);
                Log.d(TAG, "id=" + id);
            }
            else if ("name".equals(tag)) {
                name = parseText(parser);
                Log.d(TAG, "name=" + name);
            }
            else if ("img".equals(tag)) {
                img = parseText(parser);
                Log.d(TAG, "img=" + img);
            }
        }

        values.put("id", id);
        values.put("name", name);
        values.put("img", img);
        mContext.getContentResolver().insert(uriAuthority, values);
    }

    // get text from a field
    private String parseText(XmlPullParser parser) throws XmlPullParserException, IOException {
        String result = "";
        parser.next();
        result = parser.getText();
        parser.nextTag();
        return result;
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}
