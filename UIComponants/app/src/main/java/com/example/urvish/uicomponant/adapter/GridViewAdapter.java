package com.example.urvish.uicomponant.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.urvish.uicomponant.R;

/**
 * Created by urvish on 20/1/18.
 */

public class GridViewAdapter extends BaseAdapter {
    private ImageView mImageView;
    public GridViewAdapter(Activity activity,int[] mImagIds) {
        this.mImagIds=mImagIds;
        this.activity=activity;
    }

    private static int[] mImagIds;
    Activity activity;

    @Override
    public int getCount() {
        return mImagIds.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int pos) {
        return mImagIds[pos];
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {
        LayoutInflater inflater=activity.getLayoutInflater();
        View v=inflater.inflate(R.layout.gridview_data,null,true);
        mImageView=(ImageView)v.findViewById(R.id.con_img);
        mImageView.setImageResource(mImagIds[pos]);
        mImageView.setAdjustViewBounds(true);
        mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //return the imageview for parent view
        return v;

    }
}
