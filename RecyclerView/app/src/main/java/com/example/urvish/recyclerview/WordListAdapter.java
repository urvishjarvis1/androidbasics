package com.example.urvish.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by urvish on 22/1/18.
 * Adapter class which combines the data and view group and inflate to parent view group.
 * the WordViewHolder class for maintaining the runtime change of data in recycler view.
 */

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder>  {



    public class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final TextView wordItemTextview;
        final WordListAdapter mAdapter;

        public WordViewHolder(View itemView, WordListAdapter adapter) {
            super(itemView);
            wordItemTextview = (TextView) itemView.findViewById(R.id.word);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            int mPositon=getLayoutPosition();
            String element= mWordlist.get(mPositon);
            mWordlist.set(mPositon,"clicked"+element);
            mAdapter.notifyDataSetChanged();
                /*if you don't wanna update the actual data and just update on only for runtime purpose
                 use following method for only changing data for running time.
                 it won't change the actual data if you scroll up it will erased
                */
            //wordItemTextview.setText("clicked"+wordItemTextview.getText());

        }
    }
    private LinkedList<String> mWordlist;
    private LayoutInflater mInflater;
    public WordListAdapter(Context context,LinkedList<String> Wordlist){
        mInflater=LayoutInflater.from(context);
        this.mWordlist=Wordlist;

    }
    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView=mInflater.inflate(R.layout.wordlist_item,parent,false);
        return new WordViewHolder(mItemView,this);
    }

    @Override
    public void onBindViewHolder(WordListAdapter.WordViewHolder holder, int position) {
        String mCurrent=mWordlist.get(position);
        holder.wordItemTextview.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mWordlist.size();
    }


}

