package com.example.urvish.notificationdemo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * MainActivity:
 * sendNotification():for genrating push notification
 * updateNotification(): for updating same notification
 * cancelNotification(): clearing notification
 */
public class MainActivity extends AppCompatActivity {
    private static final int NOTIFICATION_ID = 0;
    private static final String CHANNEL_ID = "my_channel_01";
    private static final String CHANNEL_NAME = "my Channel Name";
    private static final String URL = "https://developer.android.com/design/patterns/notifications.html";
    private static final String ACTION_UPDATE = "com.example.urvish.notificationdemo.ACTION_UPDATE";
    private Button mBtnNotify, mBtnUpdate, mBtnCancel,mBtnFireBase;
    private NotificationManager mNotificationManager;
    private PendingIntent mPendingIntent;
    private ReceieveNotification mReceiver = new ReceieveNotification();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Broadcast Intent Register
        registerReceiver(mReceiver, new IntentFilter(ACTION_UPDATE));
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //for Android 8.0 and above
        NotificationChannel notificationChannel;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            notificationChannel = new NotificationChannel(CHANNEL_ID,
                    CHANNEL_NAME, NotificationManager.IMPORTANCE_LOW);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setShowBadge(true);
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

            mNotificationManager.createNotificationChannel(notificationChannel);
        }

        Intent intent = new Intent(this, MainActivity.class);
        mPendingIntent = PendingIntent.
                getActivity(this, NOTIFICATION_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBtnNotify = findViewById(R.id.btn_notifyme);
        mBtnCancel = findViewById(R.id.btn_clear);
        mBtnUpdate = findViewById(R.id.btn_update);
        //mBtnFireBase=findViewById(R.id.btn_firebase);
        mBtnCancel.setEnabled(false);
        mBtnUpdate.setEnabled(false);
        mBtnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification();
            }
        });
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancleNotification();
            }
        });
        mBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateNotification();
            }
        });
    }

    /**
     * this will create notification and build it and notifies to user's notification bar
     */

    public void sendNotification() {
        //implicit intent for opening web browser

        Intent learnMoreIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
        PendingIntent learnMorePendingIntent = PendingIntent.getActivity
                (this, NOTIFICATION_ID, learnMoreIntent, PendingIntent.FLAG_ONE_SHOT);

        // Broadcast Receiever for update notification

        Intent updateIntent = new Intent(ACTION_UPDATE);
        PendingIntent updatePendingIntent = PendingIntent.getBroadcast
                (this, NOTIFICATION_ID, updateIntent, PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder mNotificationBuilder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setContentTitle("You've been notified!")
                        .setContentText("This is your notification text.")
                        .setSmallIcon(R.drawable.ic_android).setContentIntent(mPendingIntent)
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .addAction(R.drawable.ic_learn_more, "Learn More", learnMorePendingIntent)
                        .addAction(R.drawable.ic_update, "Update", updatePendingIntent);
        Notification notification = mNotificationBuilder.build();
        mNotificationManager.notify(NOTIFICATION_ID, notification);
        mBtnUpdate.setEnabled(true);
        mBtnCancel.setEnabled(true);
        mBtnNotify.setEnabled(false);

    }

    /**
     * this will update notification banner to bigpicture notifaction banner
     */
    public void updateNotification() {
        //implicit intent for opening browser
        Intent learnMoreIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
        PendingIntent learnMorePendingIntent = PendingIntent.getActivity
                (this, NOTIFICATION_ID, learnMoreIntent, PendingIntent.FLAG_ONE_SHOT);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.and);
        NotificationCompat.Builder mNotificationBuilder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setContentTitle("You've been notified!")
                        .setContentText("This is your notification text.")
                        .setSmallIcon(R.drawable.ic_android).setContentIntent(mPendingIntent)
                        .setDefaults(NotificationCompat.DEFAULT_ALL).setStyle(new NotificationCompat
                        .BigPictureStyle()
                        .bigPicture(bitmap)
                        .setBigContentTitle("Notification Updated"))
                        .addAction(R.drawable.ic_learn_more, "Learn More", learnMorePendingIntent);
        Notification notification = mNotificationBuilder.build();
        mNotificationManager.notify(NOTIFICATION_ID, notification);
        mBtnUpdate.setEnabled(false);
        mBtnCancel.setEnabled(true);
        mBtnNotify.setEnabled(true);

    }

    /**
     * This will clear the notification of the application
     */

    public void cancleNotification() {

        mNotificationManager.cancel(NOTIFICATION_ID);
        mBtnUpdate.setEnabled(true);
        mBtnCancel.setEnabled(false);
        mBtnNotify.setEnabled(true);

    }

    //inner class for receiving broadcast intent

    @Override
    protected void onDestroy() {
        //unregistering Broadcast reciever
        unregisterReceiver(mReceiver);
        super.onDestroy();

    }

    public class ReceieveNotification extends BroadcastReceiver {

        public ReceieveNotification() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            updateNotification();
        }
    }

}
