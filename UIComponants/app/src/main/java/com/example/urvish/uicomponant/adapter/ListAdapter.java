package com.example.urvish.uicomponant.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.urvish.uicomponant.R;

/**
 * Created by urvish on 20/1/18.
 * Adapter for list
 */

public class ListAdapter extends ArrayAdapter {
    private final Activity mActivity;
    private final int[] mImageIds;
    private final String[] mTitles;
    private final String[] mDescriptions;
    private  ImageView mImageView;
    private TextView mNameTextField;
    private TextView mInfoTextField;


    public ListAdapter(Activity mActivity, int[] mImageIds, String[] mTitles, String[] mDescriptions) {
        super(mActivity, R.layout.listview_row,mTitles);
        this.mActivity=mActivity;
        this.mImageIds=mImageIds;
        this.mTitles=mTitles;
        this.mDescriptions=mDescriptions;
    }

    public View getView(int pos, View v, ViewGroup parent){
        LayoutInflater inflater=mActivity.getLayoutInflater();
        View rowview=inflater.inflate(R.layout.listview_row,null,true);
        mNameTextField = (TextView) rowview.findViewById(R.id.txt_title);
        mInfoTextField = (TextView) rowview.findViewById(R.id.txt_desc);
        mImageView = (ImageView) rowview.findViewById(R.id.img_icon);
        mNameTextField.setText(mTitles[pos]);
        mInfoTextField.setText(mDescriptions[pos]);
        mImageView.setImageResource(mImageIds[pos]);
        //Returns a Single row view for parent Viewgroup.
        return rowview;
    }
}
