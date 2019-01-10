package com.example.urvish.uicomponant.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.urvish.uicomponant.R;
import com.example.urvish.uicomponant.adapter.ListAdapter;
import com.example.urvish.uicomponant.data.DataAssets;

/**
 * List view activity uses adapter.
 */
public class ListViewActivity extends AppCompatActivity {
    private ListAdapter mListAdapter;
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        mListAdapter=new ListAdapter(this, DataAssets.getImg(),
                DataAssets.getTitle(),DataAssets.getMoreIn());
        listview=(ListView) findViewById(R.id.list_view);
        listview.setAdapter(mListAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Intent intent=new Intent(ListViewActivity.this,DetailActivity.class);
                String mCountryName=DataAssets.getTitle(pos);
                intent.putExtra("countryName",mCountryName);
                startActivity(intent);
            }
        });
    }
}
