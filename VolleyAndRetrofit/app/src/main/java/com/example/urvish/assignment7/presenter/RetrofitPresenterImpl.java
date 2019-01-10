package com.example.urvish.assignment7.presenter;

import com.example.urvish.assignment7.services.RetrofitService;
import com.example.urvish.assignment7.view.RetrofitInterface;

/**
 * Created by urvish on 16/2/18.
 * Presenter:communicator between model and view
 */

public class RetrofitPresenterImpl implements RetrofitPresenter{
    private RetrofitInterface retrofitInterface;
    private RetrofitService retrofitService;

    public RetrofitPresenterImpl(RetrofitInterface retrofitInterface) {
        this.retrofitInterface = retrofitInterface;
        retrofitService=new RetrofitService();
    }

    /**
     * setting data to database
     * @param name=name
     * @param email=email address
     */
    @Override
    public void setData(String name,String email){
        retrofitInterface.showLoading();
        retrofitService.setData(new RetrofitService.DataListener() {
            @Override
            public void onSuccess(String data) {
                retrofitInterface.showContent(data);
                retrofitInterface.hideLoading();
            }
        }, name, email);
    }

    /**
     * getting data form database
     * @param query=query
     */
    @Override
    public void getData(String query){
        retrofitInterface.showLoading();
        retrofitService.getData(new RetrofitService.DataListener() {
            @Override
            public void onSuccess(String data) {
                retrofitInterface.showContent(data);
                retrofitInterface.hideLoading();
            }
        },query);
    }
}
