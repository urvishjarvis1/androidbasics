package com.example.urvish.contentproviderdemo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import static android.content.ContentValues.TAG;
import static java.lang.Integer.parseInt;

/**
 * Created by urvish on 5/2/18.
 * Content provider subclass which act as middleware between activity and Data
 */

public class WordContentProvider extends ContentProvider {
    public String[] mData;
    private static UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    @Override
    public boolean onCreate() {
        Context context=getContext();
        mData=context.getResources().getStringArray(R.array.words);
        initializeUriMatching();
        return true;
    }

    /**
     * Query method same as read method from Database.
     * @param uri=uniform resource indentifier
     * @param projection=column that can be retrieved
     * @param selection=where clause
     * @param selectionArgs=arguments for where clause
     * @param s1
     * @return
     */
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String s1) {
        int id=-1;
        switch (sUriMatcher.match(uri)){
            case 0:
                id=Contract.ALL_ITEM;
                if(selection!=null){
                    id=parseInt(selectionArgs[0]);
                }
                break;
            case 1:
                id=parseInt(uri.getLastPathSegment());
                break;
            case UriMatcher.NO_MATCH:
                Log.d(TAG, "NO MATCH FOR THIS URI IN SCHEME.");
                id = -1;
                break;
            default:
                Log.d(TAG, "INVALID URI - URI NOT RECOGNIZED.");
                id = -1;

        }
        Log.d(TAG, "query: " + id);
        return populateCursor(id);
    }

    /**
     *
     * @param id=id passed by query() method
     * @return return the cursor to query() method
     */
    private Cursor populateCursor(int id) {
        //general type of cursor which store the data into the Array of object
        MatrixCursor cursor = new MatrixCursor(new String[] { Contract.CONTENT_PATH });

        if (id == Contract.ALL_ITEM) {
            for (int i = 0; i < mData.length; i++) {
                String word = mData[i];
                cursor.addRow(new Object[]{word});
            }
        } else if (id >= 0) {

            String word = mData[id];

            cursor.addRow(new Object[]{word});
        }
        return cursor;
    }

    /**
     *
     * @param uri
     * @return return mime type
     */
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
       switch (sUriMatcher.match(uri)){
           case 0:
               return Contract.MULTIPLE_RECORD_MIME_TYPE;
           case 1:
               return Contract.SINGLE_RECORD_MIME_TYPE;
           default:
               return null;
       }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        Log.e(TAG, "Not implemented: update uri: " + uri.toString());
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        Log.e(TAG, "Not implemented: update uri: " + uri.toString());
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        Log.e(TAG, "Not implemented: update uri: " + uri.toString());
        return 0;
    }

    /**
     * matches Activity's uri to contract uri to find a match
     */
    private void initializeUriMatching(){
        sUriMatcher.addURI(Contract.AUTHORITY, Contract.CONTENT_PATH + "/#", 1);
        sUriMatcher.addURI(Contract.AUTHORITY, Contract.CONTENT_PATH, 0);
    }
}
