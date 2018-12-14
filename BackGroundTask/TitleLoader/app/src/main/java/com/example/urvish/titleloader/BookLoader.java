package com.example.urvish.titleloader;

/**
 * Created by urvish on 5/2/18.
 */


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

/**
 * Created by urvish on 5/2/18.
 * Async Loader
 */

public class BookLoader extends AsyncTaskLoader<String> {
    private String queryBook;

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        //forceload
        forceLoad();
    }

    public BookLoader(Context context,String queryBook) {
        super(context);
        this.queryBook=queryBook;
    }

    /**
     * background loading
     * @return jsonobjcet as string
     */
    @Override
    public String loadInBackground() {
        return NetworkUtils.getBookInfo(queryBook);
    }
}
