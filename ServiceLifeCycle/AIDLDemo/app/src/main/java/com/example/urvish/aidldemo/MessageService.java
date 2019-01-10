package com.example.urvish.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by urvish on 9/2/18.
 * Service
 *          - Receives the message from another application.
 *          - Sends message from another application.
 */

public class MessageService extends Service {

    private String mMessage;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    private final IMyAidlInterface.Stub mBinder=new IMyAidlInterface.Stub() {
        @Override
        public void sendMessage(String message) throws RemoteException {
            mMessage=message;
            Log.d("Message Arrived:",message);
        }

        @Override
        public String recieveMessage() throws RemoteException {
            Log.d("Message sent:",mMessage);
            return "Hello from remote!"+mMessage;
        }
    };
}
