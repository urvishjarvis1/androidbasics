package com.example.urvish.broadcastintent;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
//used localbroadcastmanager to manage the security issue of mIntent sharing
public class MainActivity extends AppCompatActivity {
    private PackageManager mManager;
    private ComponentName mComponentName;
    private CustomReceiver mReceiver;
    private  Intent mIntent;
    private IntentFilter mIntentFilter;

    private static final String ACTION_CUSTOM_BROADCAST =
            "com.example.urvish.broadcastintentACTION_CUSTOM_BROADCAST";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mManager=getPackageManager();
        mIntent=new Intent(ACTION_CUSTOM_BROADCAST);
        mComponentName=new ComponentName(this,CustomReceiver.class);
        mReceiver = new CustomReceiver();
        mIntentFilter=new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
    }
    @Override
    protected void onResume() {
        super.onResume();
        mManager.setComponentEnabledSetting(mComponentName,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,PackageManager.DONT_KILL_APP);


    }

    @Override
    protected void onPause() {

        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
        mManager.setComponentEnabledSetting(mComponentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,PackageManager.DONT_KILL_APP);
        unregisterReceiver(mReceiver);
    }

    @Override
    protected void onStart(){
        super.onStart();
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mReceiver, new IntentFilter(ACTION_CUSTOM_BROADCAST));

        // Register the filter for listening broadcast.
        registerReceiver(mReceiver,mIntentFilter);



    }

    @Override
    protected void onStop(){
        super.onStop();




    }
    @Override
    protected void onDestroy() {
        super.onDestroy();


    }

    public void sendCustomBroadcast(View view) {

        LocalBroadcastManager.getInstance(this).sendBroadcast(mIntent);
    }
}
