package com.example.diagnotrack;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class PhoneActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final String LOG_TAG = "CallManagementApp";
    private ListView callLogListView;
    private ArrayAdapter<String> callLogAdapter;
    private ArrayList<String> callLogList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        callLogListView = findViewById(R.id.call_log_list_view);

        // Request permission to access the call log
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkPermission()) {
                loadCallLog();
            } else {
                requestPermission();
            }
        } else {
            loadCallLog();
        }

        callLogListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String phoneNumber = callLogList.get(position);
                makePhoneCall(phoneNumber);
            }
        });
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadCallLog();
            } else {
                Log.d(LOG_TAG, "Permission Denied!");
            }
        }
    }

    private void loadCallLog() {
        // Fetch call log data
        callLogList = new ArrayList<>();
        callLogAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, callLogList);
        callLogListView.setAdapter(callLogAdapter);

        String[] projection = {CallLog.Calls.NUMBER};
        String sortOrder = CallLog.Calls.DATE + " DESC";
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED) {
            Cursor cursor = getContentResolver().query(CallLog.Calls.CONTENT_URI, projection, null, null, sortOrder);
            if (cursor != null && cursor.moveToFirst()) {
                int numberColumn = cursor.getColumnIndex(CallLog.Calls.NUMBER);
                do {
                    String phoneNumber = cursor.getString(numberColumn);
                    callLogList.add(phoneNumber);
                } while (cursor.moveToNext());
                cursor.close();
                callLogAdapter.notifyDataSetChanged();
            }
        }
    }

    private void makePhoneCall(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(intent);
        } else {
            Log.d(LOG_TAG, "Permission Denied!");
        }
    }
}
