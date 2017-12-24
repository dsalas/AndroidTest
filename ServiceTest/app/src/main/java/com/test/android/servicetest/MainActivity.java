package com.test.android.servicetest;

import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private OdometerService odometer;
    private Button acctionButton;
    private Button acctionButton2;
    private TextView distanceText;
    private static int i= 0;
    private boolean bound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setObjects();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder binder) {
            OdometerService.OdometerBinder odometerBinder = (OdometerService.OdometerBinder) binder;
            odometer = odometerBinder.getOdometer();
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bound = false;
        }
    };

    private void setObjects(){
        acctionButton = (Button) findViewById(R.id.action_button);
        acctionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, TestIntentService.class);
                //for (int i = 0; i< 20 ; i++) {
                intent.putExtra(TestIntentService.EXTRA_MESSAGE,"message"/* " + Integer.toString(i)*/);
                startService(intent);
                //}
                finish();
            }
        });

        acctionButton2 = (Button) findViewById(R.id.action_button2);
        acctionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, TestService.class);
                    intent.putExtra(TestService.EXTRA_MESSAGE, "message " + Integer.toString(i++));
                    intent.putExtra(TestService.NUMBER, i);
                    startService(intent);
                //finish();
            }
        });

        distanceText = (TextView) findViewById(R.id.distance_text);
    }
}
