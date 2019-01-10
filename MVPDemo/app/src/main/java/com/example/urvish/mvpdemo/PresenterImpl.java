package com.example.urvish.mvpdemo;

/**
 * Created by urvish on 29/1/18.
 */

public class PresenterImpl implements Presenter {
    private MainView mMainView;
    private String mFirstName;
    private String mLastName;

    public PresenterImpl(MainView mMainView) {
        this.mMainView = mMainView;

    }
    @Override
    public void getData() {
        mFirstName="Urvish";
        mLastName="Rana";
        mMainView.setView(mFirstName,mLastName);
    }


}
