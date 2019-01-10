package com.example.urvish.localservicebinddemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Chronometer;



/**
 * Created by urvish on 8/2/18.
 * Service:
 *         - Chronometer
 *
 *
 */

public class LocalService extends Service {
    private final IBinder mBinder= new LocalBinder();
    private Chronometer mChronometer;

    int ans;
    private static final String TAG="BoundService";

    @Override
    public void onCreate() {
        super.onCreate();
        mChronometer=new Chronometer(this);
        mChronometer.setBase(SystemClock.elapsedRealtime());
        mChronometer.start();

    }

    public class LocalBinder extends Binder {
        LocalService getService(){
            return LocalService.this;
        }

    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"in OnBind");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG,"in OnUnBind");
        return false;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mChronometer.stop();
        Log.d(TAG,"in OnDestory");
    }

    public String getTime(){
        long elapsedMilis=SystemClock.elapsedRealtime()-mChronometer.getBase();
        int hours=(int)(elapsedMilis/3600000);
        int min=(int)(elapsedMilis-hours*3600000)/60000;
        int seconds = (int) (elapsedMilis - hours * 3600000 -min * 60000) / 1000;
        int millis = (int) (elapsedMilis - hours * 3600000 - min * 60000 - seconds * 1000);
        return hours+":"+min+":"+seconds+":"+millis;
    }

}
