package com.example.urvish.contentproviderdemo;

import android.net.Uri;

/**
 * Created by urvish on 5/2/18.
 */

public final class Contract {
    public static final String AUTHORITY="com.example.urvish.contentproviderdemo.provider";
    public static final String CONTENT_PATH = "words";
    public static final Uri CONTENT_URI=Uri.parse("content://"+AUTHORITY+"/"+CONTENT_PATH);
    static final int ALL_ITEM=-2;
    static final String WORD_ID="id";
    static final String SINGLE_RECORD_MIME_TYPE = "vnd.android.cursor.item/vnd.com.example.provider.words";
    static final String MULTIPLE_RECORD_MIME_TYPE = "vnd.android.cursor.dir/vnd.com.example.provider.words";

}
