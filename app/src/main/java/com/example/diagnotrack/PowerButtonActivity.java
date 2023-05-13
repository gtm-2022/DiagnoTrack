package com.example.diagnotrack;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PowerManager;
import android.widget.TextView;



import androidx.appcompat.app.AppCompatActivity;

public class PowerButtonActivity extends AppCompatActivity {
    private PowerManager powerManager;
    private TextView powerButtonTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_button);

        powerButtonTextView = findViewById(R.id.power_button_textview);
        powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);

        registerReceiver(powerButtonReceiver, new IntentFilter(Intent.ACTION_SCREEN_OFF));
    }

    private BroadcastReceiver powerButtonReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "WakeLock");
                wakeLock.acquire(3000); // Acquire for 3 seconds

                if (!wakeLock.isHeld()) {
                    powerButtonTextView.setText("Power button not working");
                } else {
                    powerButtonTextView.setText("Power button is working");
                    wakeLock.release();
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(powerButtonReceiver);
    }
}
