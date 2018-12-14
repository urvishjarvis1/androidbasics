package com.example.urvish.titlefinder;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by urvish on 5/2/18.
 * Async Task
 */

public class FetchBook extends AsyncTask <String, Void ,String>{
    private TextView mTitleText;
    private TextView mAuthorText;
    private TextView mDescText;
    private static final String TAG=FetchBook.class.getSimpleName();
    public FetchBook(TextView mTitleText,TextView mAuthorText,TextView mDescText){
        this.mTitleText = mTitleText;
        this.mAuthorText = mAuthorText;
        this.mDescText=mDescText;
    }

    /**
     * get the json result from NetworkUtils and parse it
     * @param s=jsonobjcet
     */
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try{
            //json parsing
            JSONObject jsonObject = new JSONObject(s);
            JSONArray itemsArray = jsonObject.getJSONArray("items");
            System.out.println("data"+itemsArray.length());
            for(int i = 0; i<itemsArray.length(); i++){
                JSONObject book = itemsArray.getJSONObject(i); //Get the current item
                String title=null;
                String authors=null;
                String Desc=null;
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");
                Log.d(TAG,"data"+i);
                System.out.println("data"+i);
                try {
                    title = volumeInfo.getString("title");
                    authors = volumeInfo.getString("authors").replaceAll("[\\[\\] ]","");
                    Desc=volumeInfo.getString("description");
                } catch (Exception e){
                    e.printStackTrace();
                }

                //If both a title and author exist, update the TextViews and return
                if (title != null && authors != null){
                    mTitleText.setText(title);
                    mAuthorText.setText(authors);
                    mDescText.setText(Desc);
                    return;
                }
            }
            mTitleText.setText("No Results Found");
            mAuthorText.setText("");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * gettign data from google's webAPI
     * @param strings=input by the user
     * @return jsonobjcet as string
     */
    @Override
    protected String doInBackground(String... strings) {
        return NetworkUtils.getBookInfo(strings[0]);

    }
}
