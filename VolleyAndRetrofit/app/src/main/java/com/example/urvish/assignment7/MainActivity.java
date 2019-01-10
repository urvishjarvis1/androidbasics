package com.example.urvish.assignment7;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.crashlytics.android.Crashlytics;
import com.example.urvish.assignment7.presenter.MainPresenterImpl;
import com.example.urvish.assignment7.view.MainView;

import io.fabric.sdk.android.Fabric;

/**
 * MainActivity
 * used Main view interface and communicate with presenter to get data from web API using volley library
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener,MainView {
    private TextView mTxtContent;
    private Button mBtnGetData;
    private ProgressDialog mProgressDialog;
    private MainPresenterImpl mMainPresenter;
    private String query;
    private EditText mEdittxtquery;
    private Button mButtonSecActivity;


    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    mProgressDialog.show();
                    break;
                case 2:
                    mProgressDialog.hide();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        mProgressDialog=new ProgressDialog(this);
        mTxtContent=findViewById(R.id.txtcontent);
        mBtnGetData=findViewById(R.id.btngetdata);
        mEdittxtquery=findViewById(R.id.edittxtquery);
        mButtonSecActivity=findViewById(R.id.floatingActionButton);
        mButtonSecActivity.setOnClickListener(this);
        mBtnGetData.setOnClickListener(this);
        mMainPresenter=new MainPresenterImpl(this,getApplicationContext());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btngetdata:
                query=mEdittxtquery.getText().toString();
                InputMethodManager inputMethodManager=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(mBtnGetData.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                if(query.isEmpty()){
                 mEdittxtquery.setError("enter query please!");
                }else {
                    mMainPresenter.setData(query);
                }
                break;
            case R.id.floatingActionButton:
                Intent intent=new Intent(this, RetrofitActivity.class);
                startActivity(intent);
                break;

        }
    }

    @Override
    public void showLoading() {
        Message msg=new Message();
        msg.what=1;
        mHandler.sendMessage(msg);
    }

    @Override
    public void hideLoading() {
         Message msg1=new Message();
        msg1.what=2;
        mHandler.sendMessage(msg1);
    }

    /**
     * setting view
     * @param data=data
     */
    @Override
    public void showContent(String data) {
        mTxtContent.setText(data);
    }

    /**
     * fabric crashlytics
     * @param view
     */
    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }

}
