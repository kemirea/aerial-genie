package com.kemikalreaktion.genie.db;

import com.kemikalreaktion.genie.Tag;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;

public class XmlParser {
    private static final String TAG = Tag.APP_TAG + ".XmlParser";

    protected static void parseInput(InputStream in) throws XmlPullParserException, IOException {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser parser = factory.newPullParser();
        parser.setInput(in, null);

        while (parser.next() != XmlPullParser.END_DOCUMENT) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            else if (parser.getName().equals("moveset")) {
                // start parsing for moveset table
            }
        }

        in.close();
    }
}
