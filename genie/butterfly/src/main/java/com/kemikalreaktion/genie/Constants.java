package com.kemikalreaktion.genie;

/**
 * Constants
 * TODO: add class header comment
 */

public class Constants {
    public static final String CLIENT_ID = "add5f55fb0674994ababe952acf2d130";
    public static final String CLIENT_SECRET = "bea11025a715474fb17f1ce7440dcf6a";
    public static final String REDIRECT_URI = "http://localhost";

    public static final String AUTH_REQUEST_URL = "https://api.instagram.com/oauth/authorize/?client_id="
            + CLIENT_ID + "&redirect_uri="
            + REDIRECT_URI + "&response_type=token";
    public static final String LOGOUT_URL = "https://instagram.com/accounts/logout/";
}
