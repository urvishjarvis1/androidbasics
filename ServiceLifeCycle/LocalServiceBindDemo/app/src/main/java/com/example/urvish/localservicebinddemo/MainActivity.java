package com.example.urvish.localservicebinddemo;


import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.urvish.localservicebinddemo.LocalService.LocalBinder;

/**
 * MainActivity:
 *              - BoundService
 *              - ServiceConnection
 *
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "BoundService";
    boolean mBound = false;
    int winnerIndex = 1;
    private LocalService mService;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LocalBinder binder = (LocalBinder) iBinder;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBound = false;
        }
    };
    private TextView mTxtShow;
    private Button mButtonShow, mButtonClear;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTxtShow = (TextView) findViewById(R.id.txt_show);
        mButtonShow = (Button) findViewById(R.id.btn_show);
        intent = new Intent(this, LocalService.class);
        mButtonShow.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(mBound) {
                    String time = mService.getTime();
                    mTxtShow.append("Posotion" + winnerIndex + "-" + time + "\n");
                    winnerIndex++;
                }else{
                    mTxtShow.setText("Service Stopped");
                    Toast.makeText(mService, "Please Restart Service by relaunching application", Toast.LENGTH_SHORT).show();
                }

            }


        });
        mButtonClear = (Button) findViewById(R.id.btn_clear);
        mButtonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTxtShow.setText("");
                chekcService();
                winnerIndex = 1;
                if (mBound) {
                    unbindService(serviceConnection);
                    stopService(new Intent(MainActivity.this, LocalService.class));
                    chekcService();
                    mBound = false;
                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        startService(intent);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            unbindService(serviceConnection);
            mBound = false;
        }
    }

    private void chekcService() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager
                .getRunningServices(Integer.MAX_VALUE)) {
            if (LocalService.class.getName().equals(
                    service.service.getClassName())) {
                Toast.makeText(mService, "Service Closed", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
