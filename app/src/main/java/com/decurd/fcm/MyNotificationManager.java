package com.decurd.fcm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

/**
 * Created by decurd on 2017-05-29.
 */

public class MyNotificationManager {

    public static final int NOTIFICATION_ID = 11100;
    private Context context;

    public MyNotificationManager(Context context) {
        this.context = context;
    }

    public void showNotification(String from, String notification, Intent intent) {
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                NOTIFICATION_ID,
                intent,
                PendingIntent.FLAG_CANCEL_CURRENT
        );

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        Notification mNotification = builder.setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setContentTitle(from)
                .setContentText(notification)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                .build();

        mNotification.flags |= Notification.FLAG_AUTO_CANCEL;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, mNotification);
    }

}
