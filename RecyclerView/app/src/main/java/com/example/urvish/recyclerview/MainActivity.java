package com.example.urvish.recyclerview;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.LinkedList;

/**
 * Floating Action Button
 * Recycler View
 *
 */
public class MainActivity extends AppCompatActivity {

    private final LinkedList<String> mWordlist=new LinkedList<>();
    private int mCount=0;
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i=0;i<50;i++){
            mWordlist.add("Word No: "+mCount++);
            Log.d("word",mWordlist.getLast());
        }
        mRecyclerView=(RecyclerView) findViewById(R.id.recyclerview);
        mAdapter=new WordListAdapter(this,mWordlist);
        //Attaching data to RecyclerView
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //this button will create the new wordlist item.
        FloatingActionButton fab=(FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mwordsize=mWordlist.size();
                mWordlist.add("Word No: "+mwordsize);
                mRecyclerView.getAdapter().notifyItemInserted(mwordsize);
                mRecyclerView.smoothScrollToPosition(mwordsize);
            }
        });
    }
}
