package com.example.diagnotrack;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.List;

public class WifiActivity extends AppCompatActivity {

    private WifiManager wifiManager;
    private ConnectivityManager connectivityManager;
    private TextView wifiStatusTextView;
    private Button scanButton;

    private BroadcastReceiver wifiScanReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (wifiManager != null) {
                if (ActivityCompat.checkSelfPermission(WifiActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)  {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                List<ScanResult> scanResults = wifiManager.getScanResults();
                // Process the scan results as needed
                // ...
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        wifiStatusTextView = findViewById(R.id.text_wifi_status);
        scanButton = findViewById(R.id.button_scan);

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanWifiNetworks();
            }
        });

        // Check WiFi status on app startup
        checkWifiStatus();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(wifiScanReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(wifiScanReceiver);
    }

    private void scanWifiNetworks() {
        if (!wifiManager.isWifiEnabled()) {
            // WiFi is disabled, prompt the user to enable it
            Toast.makeText(this, "Please enable WiFi", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
        } else {
            // WiFi is enabled, perform the scan
            wifiManager.startScan();
        }
    }

    private boolean isWifiConnected() {
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected() &&
                activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
    }

    private String getConnectedWifiSSID() {
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        if (wifiInfo != null && wifiManager.isWifiEnabled() && wifiInfo.getNetworkId() != -1) {
            return wifiInfo.getSSID().replace("\"", "");
        }
        return null;
    }

    private void connectToWifi(String ssid, String password) {
        WifiConfiguration wifiConfig = new WifiConfiguration();
        wifiConfig.SSID = "\"" + ssid + "\"";
        wifiConfig.preSharedKey = "\"" + password + "\"";

        int networkId = wifiManager.addNetwork(wifiConfig);
        wifiManager.disconnect();
        wifiManager.enableNetwork(networkId, true);
        wifiManager.reconnect();

        // Add a delay before checking the connection status
        wifiStatusTextView.setText("Connecting...");
        scanButton.setEnabled(false);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        checkWifiStatus();
                        scanButton.setEnabled(true);
                    }
                }, 5000);
    }

    private void checkWifiStatus() {
        if (isWifiConnected()) {
            String connectedSSID = getConnectedWifiSSID();
            if (connectedSSID != null) {
                wifiStatusTextView.setText("Connected to: " + connectedSSID);
            } else {
                wifiStatusTextView.setText("Connected to unknown network");
            }
        } else {
            wifiStatusTextView.setText("Disconnected");
        }
    }
}