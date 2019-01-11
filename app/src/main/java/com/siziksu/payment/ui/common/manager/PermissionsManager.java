package com.siziksu.payment.ui.common.manager;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import javax.inject.Inject;

public final class PermissionsManager {

    private static final int PERMISSION_REQUEST_CODE = 1001;

    @Inject
    public PermissionsManager() {}

    public void checkPermissions(Activity context) {
        String[] PERMISSIONS = {
                Manifest.permission.READ_CONTACTS
        };
        ActivityCompat.requestPermissions(context, PERMISSIONS, PERMISSION_REQUEST_CODE);
    }

    public boolean areAllPermissionsGranted(int requestCode, int[] grantResults) {
        boolean readContacts = false;
        switch (requestCode) {
            case PermissionsManager.PERMISSION_REQUEST_CODE:
                readContacts = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        return readContacts;
    }
}
