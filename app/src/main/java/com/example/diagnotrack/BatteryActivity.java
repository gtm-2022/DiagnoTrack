package com.example.diagnotrack;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BatteryActivity extends AppCompatActivity {

    TextView tvBatteryHealth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);

        tvBatteryHealth = findViewById(R.id.tv_battery_health);
        String batteryHealth = getBatteryHealth(this);
        tvBatteryHealth.setText(batteryHealth);
    }

    public static String getBatteryHealth(Context context) {
        Intent intent = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, BatteryManager.BATTERY_HEALTH_UNKNOWN);

        switch (health) {
            case BatteryManager.BATTERY_HEALTH_GOOD:
                return "Battery health: Good";
            case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                return "Battery health: Overheated";
            case BatteryManager.BATTERY_HEALTH_DEAD:
                return "Battery health: Dead";
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                return "Battery health: Over voltage";
            case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                return "Battery health: Unspecified failure";
            case BatteryManager.BATTERY_HEALTH_COLD:
                return "Battery health: Cold";
            default:
                return "Battery health: Unknown";
        }
    }
}
