package com.example.diagnotrack;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class BluetoothActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BLUETOOTH = 1;
    private static final int REQUEST_PERMISSION_BLUETOOTH = 2;

    private TextView bluetoothStatusTextView;
    private Button enableBluetoothButton;
    private Button disableBluetoothButton;

    private BluetoothAdapter bluetoothAdapter;
    private BluetoothReceiver bluetoothReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        bluetoothStatusTextView = findViewById(R.id.bluetooth_status_text_view);
        enableBluetoothButton = findViewById(R.id.enable_bluetooth_button);
        disableBluetoothButton = findViewById(R.id.disable_bluetooth_button);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        bluetoothReceiver = new BluetoothReceiver();

        enableBluetoothButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableBluetooth();
            }
        });

        disableBluetoothButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableBluetooth();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerBluetoothReceiver();
        updateBluetoothStatus();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterBluetoothReceiver();
    }

    private void enableBluetooth() {
        if (bluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not supported on this device", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!bluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BLUETOOTH);
        } else {
            Toast.makeText(this, "Bluetooth is already enabled", Toast.LENGTH_SHORT).show();
        }
    }

    private void disableBluetooth() {
        if (bluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not supported on this device", Toast.LENGTH_SHORT).show();
            return;
        }

        if (bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.disable();
            Toast.makeText(this, "Bluetooth disabled", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Bluetooth is already disabled", Toast.LENGTH_SHORT).show();
        }
    }

    private void registerBluetoothReceiver() {
        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(bluetoothReceiver, filter);
    }

    private void unregisterBluetoothReceiver() {
        unregisterReceiver(bluetoothReceiver);
    }

    private void updateBluetoothStatus() {
        if (bluetoothAdapter.isEnabled()) {
            bluetoothStatusTextView.setText("Bluetooth is enabled");
        } else {
            bluetoothStatusTextView.setText("Bluetooth is disabled");
        }
    }

    private class BluetoothReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
                int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);

                switch (state) {
                    case BluetoothAdapter.STATE_OFF:
                        Toast.makeText(context, "Bluetooth turned off", Toast.LENGTH_SHORT).show();
                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        Toast.makeText(context, "Bluetooth turning off", Toast.LENGTH_SHORT).show();
                        break;
                    case BluetoothAdapter.STATE_ON:
                        Toast.makeText(context, "Bluetooth turned on", Toast.LENGTH_SHORT).show();
                        break;
                    case BluetoothAdapter.STATE_TURNING_ON:
                        Toast.makeText(context, "Bluetooth turning on", Toast.LENGTH_SHORT).show();
                        break;
                }

                updateBluetoothStatus();
            }
        }
    }
}
