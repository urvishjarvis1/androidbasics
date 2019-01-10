package com.example.urvish.aidlclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.urvish.aidldemo.IMyAidlInterface;

/**
 * Main Activity:
 *              - AIDL
 *              - Remote Service
 */
public class MainActivity extends AppCompatActivity {
    private IMyAidlInterface myAidlInterface;
    private ServiceConnection mServiceConnection=new ServiceConnection() {
        //Service connection method
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myAidlInterface=IMyAidlInterface.Stub.asInterface(service);
            mBound=true;
            Toast.makeText(MainActivity.this, "Service Connected", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            myAidlInterface=null;
            mBound=false;
            Toast.makeText(MainActivity.this, "Service Disconnected", Toast.LENGTH_SHORT).show();
        }
    };
    private boolean mBound=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent intent=new Intent("com.example.urvish.aidldemo.MessageService");
        intent.setPackage("com.example.urvish.aidldemo");
        bindService(intent,mServiceConnection, Context.BIND_AUTO_CREATE);
        Button mBtnSend=findViewById(R.id.btn_sendmsg);
        Button mBtnRec=findViewById(R.id.btn_recmsg);
        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(mBound){
                   try {
                       //sending message to remote service
                       myAidlInterface.sendMessage("hello form another application");
                   }catch (RemoteException e){
                       e.printStackTrace();
                   }
               }
            }
        });
        mBtnRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBound){
                    try {
                        //receiving message from remote service
                        String msg=myAidlInterface.recieveMessage();
                        Toast.makeText(MainActivity.this, "Message:"+msg, Toast.LENGTH_SHORT).show();
                    }catch (RemoteException e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }



}
