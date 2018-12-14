package com.example.urvish.handlerdemo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

/**
 * MainActivity:
 *              - Handler
 *              - Thread
 *              - handler.post() method
 */
public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private Button mBtnDownload;
    private Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHandler=new Handler();
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar1);
        mBtnDownload=(Button) findViewById(R.id.btn_download);
        mBtnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Runnable runnable = new Runnable() {

                    public void run() {
                        for (int i = 0; i <= 10; i++) {
                            final int value = i;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            mHandler.post(new Runnable() {

                                public void run() {
                                    mProgressBar.setProgress(value);
                                }
                            });
                        }
                    }
                };
                new Thread(runnable).start();
            }
        });
    }
}
