package com.example.urvish.javafeatures.controlflow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.urvish.javafeatures.R;

/**
 * Control flow - using switch case
 * in this i kept single mBtn variable for two different UI componant to demonstrate control flow.
 *
 */
public class ControlFlowActivity extends AppCompatActivity {
    
    private int mCount = 0;
    private TextView mTxtCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_flow);
        mTxtCount = (TextView) findViewById(R.id.txt_ans);
        mTxtCount.setText(Integer.toString(mCount));
    }

    public void onPress(View view) {
        Button mBtn=(Button) view;
        switch (mBtn.getId()) {
            case R.id.btn_plus:
                mCount++;
                mTxtCount.setText(Integer.toString(mCount));
                break;
            case R.id.btn_minus:
                mCount--;
                mTxtCount.setText(Integer.toString(mCount));
                break;
        }
    }
}
