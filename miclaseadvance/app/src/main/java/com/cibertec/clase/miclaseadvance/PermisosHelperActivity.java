package com.cibertec.clase.miclaseadvance;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cibertec.clase.miclaseadvance.helper.PermitHelper;

public class PermisosHelperActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "PermisosHelperActivity";

    private Context mContext;
    private TextView txtMessage, txtStorage, txtSMS, txtPhone, txtLocation;
    private Button btnGoSettings;

    private String [] permits = new String[]{
            android.Manifest.permission.READ_PHONE_STATE,
            android.Manifest.permission.ACCESS_NETWORK_STATE,
            android.Manifest.permission.INTERNET,
            android.Manifest.permission.SEND_SMS,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.CALL_PHONE,
    };

    private static final int REQUEST_ALL_PERMISSION = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permisos_helper);

        mContext = this;

        initParams();
        setParams();

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();


        if(PermitHelper.hasPermissions(getApplicationContext(), permits))
            finish();
    }

    private void initParams(){

        txtMessage = (TextView)findViewById(R.id.txtMessage);
        txtStorage = (TextView)findViewById(R.id.txtStorage);
        txtSMS = (TextView)findViewById(R.id.txtSMS);
        txtPhone = (TextView)findViewById(R.id.txtPhone);
        txtLocation = (TextView)findViewById(R.id.txtLocation);

        btnGoSettings = (Button) findViewById(R.id.btnGoSettings);
    }

    private void setParams(){


        btnGoSettings.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGoSettings:
                goSettings();
                break;

            default:
                break;
        }
    }


    private void goSettings(){

        // Here, thisActivity is the current activity
        if (!PermitHelper.hasPermissions(getApplicationContext(), permits)) {

            if (!PermitHelper.hasPermission(getApplicationContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(PermisosHelperActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_ALL_PERMISSION);
            }

            if (!PermitHelper.hasPermission(getApplicationContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(PermisosHelperActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_ALL_PERMISSION);
            }

            if (!PermitHelper.hasPermission(getApplicationContext(),
                    Manifest.permission.READ_PHONE_STATE)) {
                ActivityCompat.requestPermissions(PermisosHelperActivity.this,
                        new String[]{Manifest.permission.READ_PHONE_STATE},
                        REQUEST_ALL_PERMISSION);
            }

            if (!PermitHelper.hasPermission(getApplicationContext(),
                    Manifest.permission.RECEIVE_SMS)) {
                ActivityCompat.requestPermissions(PermisosHelperActivity.this,
                        new String[]{Manifest.permission.RECEIVE_SMS},
                        REQUEST_ALL_PERMISSION);
            }
        }else{
            finish();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if(PermitHelper.hasPermissions(getApplicationContext(), permits)){
            Intent intent = new Intent(PermisosHelperActivity.this, PermisosActivity.class);
            startActivity(intent);
        }
    }
}
