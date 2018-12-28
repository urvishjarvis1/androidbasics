package com.example.urvish.contentproviderdemo;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * contentResolver to access content provider
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG=MainActivity.class.getSimpleName();
    private TextView mTxtShow;
    private Button mBtnShowAll,mBtnShowSingle,mBtnClear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTxtShow=(TextView)findViewById(R.id.txt_show);
        mBtnShowAll=(Button)findViewById(R.id.btn_showall);
        mBtnShowSingle=(Button)findViewById(R.id.btn_showsingle);
        mBtnShowAll.setOnClickListener(this);
        mBtnShowSingle.setOnClickListener(this);

    }

    /**
     * switch case for user input
     * @param view=view object
     */
    @Override
    public void onClick(View view) {
        String queryUri = Contract.CONTENT_URI.toString();
        String[] projection = new String[] {Contract.CONTENT_PATH};
        String selectionClause=null;
        String selectionArgs[]=null;
        String sortOrder = null;


        switch (view.getId()){
            case R.id.btn_showall:
                selectionClause = null;
                selectionArgs = null;
                break;
            case R.id.btn_showsingle:
                selectionClause = Contract.WORD_ID + " = ?";
                selectionArgs = new String[] {"0"};
                break;

        }
        Cursor cursor = getContentResolver().query(Uri.parse(queryUri), projection, selectionClause, selectionArgs, sortOrder);
        if(cursor!=null){
            if (cursor.getCount()>0){
                cursor.moveToFirst();
                int columnIndex=cursor.getColumnIndex(projection[0]);
                do {
                    String word = cursor.getString(columnIndex);
                    mTxtShow.append(word + "\n");
                } while (cursor.moveToNext());

            }else {
                Log.d(TAG, "onClickDisplayEntries " + "No data returned.");
                mTxtShow.append("No data returned." + "\n");
            }
            cursor.close();

        }else{
            Log.d(TAG, "onClickDisplayEntries " + "Cursor is null.");
            mTxtShow.append("Cursor is null." + "\n");
        }
    }
}
