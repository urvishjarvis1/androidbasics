package com.example.volansys.jobschedular;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

public class NotificationJobService extends JobService {
    private NotificationManager notificationManager;
    private String channel = "Job";
    private String channelId = "jobid";
    private String channelDesc = "JobScheduler";
    final int NOTIFICATION_ID = 1;
    private PendingIntent pendingIntent;

    @Override
    public boolean onStartJob(JobParameters params) {
       pendingIntent =PendingIntent.getActivity(this,0,new Intent(this,MainActivity.class),PendingIntent.FLAG_UPDATE_CURRENT);
        createNotification("Job Scheduled",this);

        return false;
    }
    private void createNotification(String msg,Context context) {

        NotificationCompat.Builder builder;
        if (notificationManager == null) {
            notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mchannel = notificationManager.getNotificationChannel(channelId);
            if (mchannel == null) {
                mchannel = new NotificationChannel(channelId, channel, NotificationManager.IMPORTANCE_HIGH);
                long[] longs = new long[]{100l, 200l, 300l, 400l, 500l, 400l, 300l, 200l, 400l};
                mchannel.setDescription(channelDesc);
                mchannel.enableVibration(true);
                mchannel.setVibrationPattern(longs);
                notificationManager.createNotificationChannel(mchannel);
            }
            builder = new NotificationCompat.Builder(context, channelId);


            builder.setContentTitle(msg)  // required
                    .setSmallIcon(android.R.drawable.ic_popup_reminder)
                    .setContentText(context.getString(R.string.app_name))
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setTicker(msg)
                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        } else {

            builder = new NotificationCompat.Builder(context);


            builder.setContentTitle(msg)                           // required
                    .setSmallIcon(android.R.drawable.ic_popup_reminder) // required
                    .setContentText(context.getString(R.string.app_name))  // required
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setTicker(msg)
                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                    .setPriority(Notification.PRIORITY_HIGH);
        }
        notificationManager.notify(NOTIFICATION_ID,builder.build());
    }

        @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
