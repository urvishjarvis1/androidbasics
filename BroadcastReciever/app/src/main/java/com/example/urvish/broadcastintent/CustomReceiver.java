package com.example.urvish.broadcastintent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Toast;

/**
 * custom reciever for receiving inbuilt brodcast intent as well as custom broadcast intent
 */

public class CustomReceiver extends BroadcastReceiver {
    private static final String ACTION_CUSTOM_BROADCAST =
            "com.example.urvish.broadcastintentACTION_CUSTOM_BROADCAST";

    @Override
    public void onReceive(Context context, Intent intent) {

        String mIntentAction= intent.getAction();
        switch (mIntentAction){
            case Intent.ACTION_POWER_CONNECTED:
                Toast.makeText(context,"Power connected",Toast.LENGTH_SHORT).show();
                break;
            case Intent.ACTION_POWER_DISCONNECTED:
                Toast.makeText(context,"Power disconnected",Toast.LENGTH_SHORT).show();
                break;
            case Intent.ACTION_BATTERY_LOW:
                Toast.makeText(context,"Battery is low",Toast.LENGTH_SHORT).show();
                break;
            case Intent.ACTION_AIRPLANE_MODE_CHANGED:
                Toast.makeText(context,"Aeroplane mode changed",Toast.LENGTH_SHORT).show();
                break;
            case ACTION_CUSTOM_BROADCAST:
                Toast.makeText(context,"Custom broadcast intent",Toast.LENGTH_SHORT).show();
                break;


        }

    }
}
