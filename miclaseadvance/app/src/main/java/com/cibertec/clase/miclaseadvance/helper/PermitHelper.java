package com.cibertec.clase.miclaseadvance.helper;

/**
 * Created by Administrador on 02/12/2017.
 */

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * Created by janaqdev on 2/08/17.
 */

public class PermitHelper {

    public static String TAG = "UtilsPermission";

    public static boolean hasPermission(Context context, String permission) {

        int res = context.checkCallingOrSelfPermission(permission);

        Log.v(TAG, "permission: " + permission + " = \t\t" +
                (res == PackageManager.PERMISSION_GRANTED ? "GRANTED" : "DENIED"));

        return res == PackageManager.PERMISSION_GRANTED;

    }

    /** Determines if the context calling has the required permissions
     * @param context - the IPC context
     * @param permissions - The permissions to check
     * @return true if the IPC has the granted permission
     */
    public static boolean hasPermissions(Context context, String... permissions) {

        boolean hasAllPermissions = true;

        for(String permission : permissions) {
            //return false instead of assigning, but with this you can log all permission values
            if (! hasPermission(context, permission)) {hasAllPermissions = false; }
        }

        return hasAllPermissions;

    }

}
