package com.example.urvish.titlefinder;

import android.net.Uri;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by urvish on 5/2/18.
 */

public class NetworkUtils {
    private static final String LOG_TAG=NetworkUtils.class.getSimpleName();
    private static final String BOOK_BASE_URL =  "https://www.googleapis.com/books/v1/volumes?"; // Base URI for the Books API
    private static final String QUERY_PARAM = "q"; // Parameter for the search string
    private static final String MAX_RESULTS = "maxResults"; // Parameter that limits search results
    private static final String PRINT_TYPE = "printType";   // Parameter to filter by print type

    /**
     * getting data from google's book API.
     * @param query=passed by user
     * @return json objcet
     */
    static String getBookInfo(String query){
        HttpURLConnection urlConnection=null;
        BufferedReader reader=null;
        String bookJSONString=null;
        try{
            Uri uriBuild= Uri.parse(BOOK_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM,query)
                    .appendQueryParameter(MAX_RESULTS,"1")
                    .appendQueryParameter(PRINT_TYPE,"books").build();
            URL url= new URL(uriBuild.toString());
            urlConnection=(HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            //connceting to API
            urlConnection.connect();
            //getting response
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer= new StringBuffer();
            if(inputStream==null){
                return  null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {

                return null;
            }
            bookJSONString = buffer.toString();
            //Log.d(LOG_TAG,bookJSONString);

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return bookJSONString;
    }
}
