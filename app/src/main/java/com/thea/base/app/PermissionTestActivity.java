package com.thea.base.app;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.thea.base.BaseActivity;
import com.thea.base.R;

/**
 * @author Thea (theazhu0321@gmail.com)
 */
public class PermissionTestActivity extends BaseActivity {
    private static final String TAG = PermissionTestActivity.class.getSimpleName();

    private static final int REQUEST_READ_CONTACTS_PERMISSION = 22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_test);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_READ_CONTACTS_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    queryContacts();
                else
                    Toast.makeText(this, "没有读取联系人权限", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public boolean checkPermission(String permission) {
        int permissionCheck = ContextCompat.checkSelfPermission(
                this, permission);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            // 判断是否应该说明申请权限的理由
            boolean shouldShowRationale = ActivityCompat.shouldShowRequestPermissionRationale(
                    this, Manifest.permission.READ_CONTACTS);
            Log.i(TAG, "rationale: " + shouldShowRationale);
            if (shouldShowRationale) {
                // 说明理由，等待用户反馈，再申请权限
            }
            ActivityCompat.requestPermissions(this, new String[]{permission},
                    REQUEST_READ_CONTACTS_PERMISSION);
        }
        return permissionCheck == PackageManager.PERMISSION_GRANTED;
    }

    public void getContacts(View view) {
        if (Build.VERSION.SDK_INT < 23)
            queryContacts();
        else if (checkPermission(Manifest.permission.READ_CONTACTS))
            queryContacts();
    }

    public void queryContacts() {
        Cursor cursor = getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,
                new String[]{ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME},
                null, null, ContactsContract.Contacts.SORT_KEY_PRIMARY);

        if (cursor == null)
            return;

        while (cursor.moveToNext()) {
            Log.i(TAG, "id: " + cursor.getString(0) + ", name: " + cursor.getString(1));
        }

        cursor.close();
    }
}
