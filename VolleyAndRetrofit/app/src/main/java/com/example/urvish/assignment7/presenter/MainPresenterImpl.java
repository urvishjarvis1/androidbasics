package com.example.urvish.assignment7.presenter;

import android.content.Context;
import android.util.Log;

import com.example.urvish.assignment7.services.VolleyModel;
import com.example.urvish.assignment7.view.MainView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by urvish on 15/2/18.
 * Presenter: act as middle point between view and model
 *
 */

public class MainPresenterImpl implements MainPresenter {
    private MainView mainView;
    private VolleyModel mVolleyModel;

    public MainPresenterImpl(MainView mainView, Context context){
        this.mainView=mainView;
        mVolleyModel=new VolleyModel(context);
    }

    /**
     * setting data to view
     * @param query=query passed by user through the activity
     */
    @Override
    public void setData(String query){
        mainView.showLoading();
        mVolleyModel.getDataFormAPI(new VolleyModel.DataListener() {
            @Override
            public void onSuccess(String response) {

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("items");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject book=jsonArray.getJSONObject(i);
                        JSONObject volumeInfo=book.getJSONObject("volumeInfo");
                        String title="",author="",desc="null";
                        try {
                             title= volumeInfo.getString("title");
                             author = volumeInfo.getString("authors")
                                    .replaceAll("[\\[\\] ]", "");
                             desc = volumeInfo.getString("description");
                        }catch (Exception e){
                            if (title!=null && author!=null){
                                Log.d("data",title+"\n"+author+"\n" +desc+"\n");
                                mainView.showContent("Title:\n\n"+title+"\n\n"+"Authors:\n\n"+author+"\n\nDescription:\n\n" +desc+"\n");
                                mainView.hideLoading();
                                return;
                            }else{
                                mainView.showContent("not found");
                                mainView.hideLoading();
                                return;
                            }
                        }
                        if (title!=null && author!=null){
                            Log.d("data",title+"\n"+author+"\n" +desc+"\n");
                            mainView.showContent("Title:\n\n"+title+"\n\n"+"Authors:\n\n"+author+"\n\nDescription:\n\n" +desc+"\n");
                            mainView.hideLoading();
                            return;
                        }else{
                            mainView.showContent("not found");
                            mainView.hideLoading();
                            return;
                        }



                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },query);
    }


}
