package com.example.urvish.uicomponant.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.urvish.uicomponant.R;
import com.example.urvish.uicomponant.adapter.GridViewAdapter;
import com.example.urvish.uicomponant.data.DataAssets;
import com.example.urvish.uicomponant.data.GridDataAssets;

public class DetailActivity extends AppCompatActivity {
    private GridView gridView;
    private GridViewAdapter mGridAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        gridView=(GridView) findViewById(R.id.gridview);
        mGridAdapter=new GridViewAdapter(this, GridDataAssets.getImg());
        gridView.setAdapter(mGridAdapter);

    }
}
