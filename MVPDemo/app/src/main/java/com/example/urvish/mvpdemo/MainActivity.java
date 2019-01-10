package com.example.urvish.mvpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MainView,View.OnClickListener {
    private Button mButtonShow;
    private TextView mFirstNameTextView,mLastNameTextView;
    private PresenterImpl mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButtonShow = (Button) findViewById(R.id.btn_show);
        mFirstNameTextView=(TextView) findViewById(R.id.txt_fname);
        mLastNameTextView=(TextView) findViewById(R.id.txt_lname);
        mButtonShow.setOnClickListener(this);
        mPresenter=new PresenterImpl(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_show:
                mPresenter.getData();
                break;
            default:
                    Toast.makeText(this, "press button", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void setView(String fname,String lname) {
        mFirstNameTextView.setVisibility(View.VISIBLE);
        mLastNameTextView.setVisibility(View.VISIBLE);
        mFirstNameTextView.setText(fname);
        mLastNameTextView.setText(lname);


    }
}
