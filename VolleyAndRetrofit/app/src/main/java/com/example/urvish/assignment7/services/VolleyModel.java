package com.example.urvish.assignment7.services;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by urvish on 15/2/18.
 * for getting data from web api and passing the response
 */

public class VolleyModel {
    private RequestQueue mRequestQueue;
    public VolleyModel(Context context){
        mRequestQueue= Volley.newRequestQueue(context);
    }
    public void getDataFormAPI(final DataListener dataListener,String query){
        String url="https://www.googleapis.com/books/v1/volumes?q="+query+"/1/books";
        
        StringRequest stringRequest=new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Data",response);

                        dataListener.onSuccess(response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //TODO
            }
        });
        mRequestQueue.add(stringRequest);
        mRequestQueue.start();
    }
    public interface DataListener{
        void onSuccess(String data);
    }
}
