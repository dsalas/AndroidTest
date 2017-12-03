package com.cibertec.clase.miclaseadvance;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class PermisosActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnCamara;
    private View mLayout;
    private String TAG="Permisos";
    private static final int PERMISSION_REQUEST_CAMERA = 0;
    private static final int REQUEST_PERMISSION_SETTING = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permisos);
        inicializarVariables();
        inicializarObjetos();

    }

    private void inicializarObjetos() {
        btnCamara=findViewById(R.id.button_camara);
        mLayout=findViewById(R.id.main_layout);
        btnCamara.setOnClickListener(this);

    }

    private void inicializarVariables() {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.button_camara:
                mostrarCamara();
                break;
        }
    }

    private void mostrarCamara() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            Snackbar.make(mLayout, "Si tiene Permiso", Snackbar.LENGTH_SHORT).show();
            //startCamera();
        } else {
            Snackbar.make(mLayout, "No tiene Permiso", Snackbar.LENGTH_SHORT).show();
            requestCameraPermission();
        }
    }
    private void requestCameraPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                Snackbar.make(mLayout, "El acceso a camara es requerido.",
                    Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Request the permission
                    ActivityCompat.requestPermissions(PermisosActivity.this,
                            new String[]{Manifest.permission.CAMERA},
                            PERMISSION_REQUEST_CAMERA);
                }
            }).show();

        } else {
            Snackbar.make(mLayout, "permiso no esta habilitado. requiriendo permiso", Snackbar.LENGTH_SHORT).show();
            // Request the permission. The result will be received in onRequestPermissionResult().
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    PERMISSION_REQUEST_CAMERA);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        Log.i(TAG, "onRequestPermissionsResult: RequestCode" + requestCode);
        if (requestCode == PERMISSION_REQUEST_CAMERA) {
            // Request for camera permission.
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission has been granted. Start camera preview Activity.
                Snackbar.make(mLayout, "El permiso a Camara fue concedido",
                        Snackbar.LENGTH_SHORT)
                        .show();
                startCamera();
            } else {
                // Permission request was denied.
                Snackbar.make(mLayout, "Camera permission request was denied.", Snackbar.LENGTH_SHORT).show();
                String permission = permissions[0];
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    boolean showRationale = shouldShowRequestPermissionRationale( permission);
                    if (! showRationale) {
                        Log.i(TAG, "onRequestPermissionsResult: " + "Hizo check en NO preguntar de nuevo.");
                        Snackbar.make(mLayout, "Hizo check en NO preguntar de nuevo.", Snackbar.LENGTH_SHORT).show();

                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivityForResult(intent, REQUEST_PERMISSION_SETTING);

                    } else if (Manifest.permission.CAMERA.equals(permission)) {
                        Log.i(TAG, "onRequestPermissionsResult: " + "NO Hizo check en NO preguntar de nuevo");
                        Snackbar.make(mLayout, "NO Hizo check en NO preguntar de nuevo.", Snackbar.LENGTH_SHORT).show();
                        requestCameraPermission();

                    } //else if ( /* possibly check more permissions...*/ ) {
                    //}
                }


            }
        }
        // END_INCLUDE(onRequestPermissionsResult)
    }

    private void mensaje() {


    }

    private void startCamera() {

    }

}
