package org.d3ifcool.finpro.core.helpers;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import org.d3ifcool.finpro.R;

import androidx.core.app.NotificationCompat;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 14/04/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Line     : bullbee117
 * Phone    : 081357108568
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 * id.amirisback.frogobox
 */
public class NotificationCall {

    private int NOTIFICATION_ID;
    private String CHANNEL_ID;
    private CharSequence CHANNEL_NAME;
    private ComponentName componentName;

    public void setID(int NOTIFICATION_ID, String CHANNEL_ID, CharSequence CHANNEL_NAME){
        this.NOTIFICATION_ID = NOTIFICATION_ID;
        this.CHANNEL_ID = CHANNEL_ID;
        this.CHANNEL_NAME = CHANNEL_NAME;
    }

    public void setComponentName(String packageRoot, String packagePath){
        this.componentName = new ComponentName(packageRoot, packagePath);
    }

    public void sendNotification(Context context, String contentTitle, String contentText) {
        // -----------------------------------------------------------------------------------------
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setComponent(componentName);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        // -----------------------------------------------------------------------------------------
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_logo_notification)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_logo_notification))
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setSound(alarmSound);
        // -----------------------------------------------------------------------------------------
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            mBuilder.setChannelId(CHANNEL_ID);
            if (mNotificationManager != null) {
                mNotificationManager.createNotificationChannel(channel);
            }
        }

        // -----------------------------------------------------------------------------------------
        Notification notification = mBuilder.build();
        if (mNotificationManager != null) {
            mNotificationManager.notify(NOTIFICATION_ID, notification);
        }
        // -----------------------------------------------------------------------------------------
    }

}
