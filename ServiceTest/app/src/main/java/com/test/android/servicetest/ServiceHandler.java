package com.test.android.servicetest;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.util.Log;


/**
 * Created by Android on 23/12/2017.
 */

public class ServiceHandler extends Handler {
    private Service parent;
    public ServiceHandler(Looper looper, Service service) {
        super(looper);
        this.parent = service;
    }

    @Override
    public void handleMessage(Message msg){
        try {
            int number = msg.getData().getInt("number");
            if (number < 10) {
                number = 10-number;
            }
            if (number > 10) {
                number = number - 10;
            }
            Thread.sleep(number * 1000);

            Bundle data = msg.getData();
            String text = msg.getData().getString("TEXTO");
            int id = msg.getData().getInt("NOTIF_ID");
            Log.d("DEBUG", text +" from "+Integer.toString(System.identityHashCode(this)));
            //showText(text, this.parent, id);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        parent.stopSelf(msg.arg1);
    }

    public void showText(String text, Context context, int id){
        NotificationManager mNotifyMgr =
                (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setContentTitle("My notification");
        mBuilder.setContentText(text);
        mBuilder.setAutoCancel(true);

        mBuilder.setSmallIcon(R.drawable.ic_priority_high_black_24px);

        Intent actionIntent = new Intent(context, MainActivity.class);
        PendingIntent actionPendingIntent = PendingIntent.getActivity(context, 0, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(actionPendingIntent);
        // Sets an ID for the notification
        int mNotificationId = id++;
        // Gets an instance of the NotificationManager service

        // Builds the notification and issues it.
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }
}



