package com.home.grishnak.filckrexplorer.network;

public interface ApiConstants {
    public static final String FLICKR_KEY = "7b7c94e857d456add2fc7352a4960330";
    public static final String  FLICKR_SECRET = "e89ed7f6dc6d9091";
    public static final String BASE_URL = "https://api.flickr.com/services/rest/";

    public static final int HTTP_CONNECT_TIMEOUT =  6000; // milliseconds
    public static final int HTTP_READ_TIMEOUT = 10000; // milliseconds

    public static final String OK_STATE = "ok";
    public static final String FAIL_STATE = "fail";

}
