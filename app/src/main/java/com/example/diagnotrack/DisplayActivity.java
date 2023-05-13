package com.example.diagnotrack;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {

    private TextView mTextViewDeviceInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        mTextViewDeviceInfo = findViewById(R.id.textViewDeviceInfo);

        // Display device info
        displayDeviceInfo();
    }

    private void displayDeviceInfo() {
        // Get device information
        String deviceManufacturer = android.os.Build.MANUFACTURER;
        String deviceModel = android.os.Build.MODEL;
        String deviceBrand = android.os.Build.BRAND;
        String deviceProduct = android.os.Build.PRODUCT;
        String deviceCPU_ABI = android.os.Build.CPU_ABI;
        String deviceOSVersion = android.os.Build.VERSION.RELEASE;
        String deviceSDKVersion = android.os.Build.VERSION.SDK;
        int deviceOrientation = getResources().getConfiguration().orientation;
        int deviceScreenWidth = getResources().getDisplayMetrics().widthPixels;
        int deviceScreenHeight = getResources().getDisplayMetrics().heightPixels;

        // Display device information
        mTextViewDeviceInfo.setText("Device Manufacturer: " + deviceManufacturer + "\n" +
                "Device Model: " + deviceModel + "\n" +
                "Device Brand: " + deviceBrand + "\n" +
                "Device Product: " + deviceProduct + "\n" +
                "Device CPU ABI: " + deviceCPU_ABI + "\n" +
                "Device OS Version: " + deviceOSVersion + "\n" +
                "Device SDK Version: " + deviceSDKVersion + "\n" +
                "Device Orientation: " + (deviceOrientation == Configuration.ORIENTATION_PORTRAIT ? "Portrait" : "Landscape") + "\n" +
                "Device Screen Width: " + deviceScreenWidth + "\n" +
                "Device Screen Height: " + deviceScreenHeight);
    }
}
