package com.test.android.servicetest;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class TestService extends Service {

    private Looper mServiceLooper;
    private ServiceHandler mServiceHandler;
    public static final String EXTRA_MESSAGE = "message";
    public static final String NUMBER = "number";
    private String TAG = "Servicio";
    private static int id = 1;
    private int startId = -1;
    public TestService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate " + Integer.toString(System.identityHashCode(this)));
        HandlerThread thread = new HandlerThread("ServiceStartArguments", HandlerThread.MAX_PRIORITY);
        thread.start();
        mServiceLooper = thread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper, this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        HandlerThread thread = new HandlerThread("ServiceStartArguments", HandlerThread.MAX_PRIORITY);
        thread.start();
        mServiceLooper = thread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper, this);
        this.startId = startId;
        int parent =  super.onStartCommand(intent, flags, startId);
        Log.d("DEBUG","service starting " + Integer.toString(startId) + ",local id " + Integer.toString(System.identityHashCode(this)));
        Message msg = mServiceHandler.obtainMessage();//Message.obtain(mServiceHandler);
        msg.arg1 = startId;
        Bundle arguments = new Bundle();
        String text = intent.getStringExtra(EXTRA_MESSAGE);
        int number = intent.getIntExtra(NUMBER,0);
        arguments.putString("TEXTO", text);
        arguments.putInt("NOTIF_ID",id++);
        arguments.putInt(NUMBER,number);
        msg.setData(arguments);
        mServiceHandler.sendMessage(msg);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("DEBUG","Servide destroyed " + Integer.toString(this.startId) + ", local id " + Integer.toString(System.identityHashCode(this)));
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
