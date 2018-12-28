package com.example.urvish.twoactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY="com.example.urvish.twoactivity";
    public static final String LOG_TAG=SecondActivity.class.getSimpleName();
    private EditText mReply;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent i=getIntent();
        String msg=i.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView= (TextView) findViewById(R.id.msg);
        mReply=(EditText) findViewById(R.id.edit_text);
        msg=msg.toUpperCase();

        textView.setText(msg);

    }
    @Override
    public void onPause(){
        super.onPause();
        Log.d(LOG_TAG,"onPause");
    }
    @Override
    public void onStart(){
        super.onStart();
        Log.d(LOG_TAG,"onStart");
    }
    @Override
    public void onStop(){
        super.onStop();
        Log.d(LOG_TAG,"onStop");
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.d(LOG_TAG,"onResume");
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(LOG_TAG,"onDestroy");
    }

    public void returnReply(View view) {
        Intent i1=new Intent();
        String reply = mReply.getText().toString();
        i1.putExtra(EXTRA_REPLY,reply);
        setResult(RESULT_OK,i1);
        Log.d(LOG_TAG, "End SecondActivity");
        finish();
    }
}
