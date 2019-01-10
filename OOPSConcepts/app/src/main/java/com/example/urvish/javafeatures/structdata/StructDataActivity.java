package com.example.urvish.javafeatures.structdata;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.urvish.javafeatures.R;

import java.util.ArrayList;

public class StructDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_struct_data);
        TextView mTxt;
        ArrayList<String> mArray = new ArrayList<>();
        String mFinalText="";
        mTxt = (TextView) findViewById(R.id.txt_text);

        for (int i = 0; i < 30; i++) {
            mArray.add("data " + i + "\n");
        }
        for (int i = 0; i < mArray.size(); i++) {
            mFinalText=mFinalText+mArray.get(i)+"\n";
        }

        mTxt.setText(mFinalText);
    }
}
