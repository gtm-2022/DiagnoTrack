package com.example.diagnotrack;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class LocationActivity extends AppCompatActivity implements LocationListener {
    private static final String TAG = "GpsActivity";
    private static final int PERMISSIONS_REQUEST_LOCATION = 100;
    private TextView latitudeTextView, longitudeTextView, altitudeTextView, accuracyTextView, speedTextView;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        latitudeTextView = findViewById(R.id.latitude_text_view);
        longitudeTextView = findViewById(R.id.longitude_text_view);
        altitudeTextView = findViewById(R.id.altitude_text_view);
        accuracyTextView = findViewById(R.id.accuracy_text_view);
        speedTextView = findViewById(R.id.speed_text_view);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (checkLocationPermission()) {
            if (isLocationEnabled()) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            } else {
                Toast.makeText(this, "Please enable GPS.", Toast.LENGTH_SHORT).show();
            }
        } else {
            requestLocationPermission();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "Location changed: " + location.toString());
        latitudeTextView.setText(String.format("%.6f", location.getLatitude()));
        longitudeTextView.setText(String.format("%.6f", location.getLongitude()));
        altitudeTextView.setText(String.format("%.2f m", location.getAltitude()));
        accuracyTextView.setText(String.format("%.2f m", location.getAccuracy()));
        speedTextView.setText(String.format("%.2f m/s", location.getSpeed()));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d(TAG, "Status changed: " + status);
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d(TAG, "Provider enabled: " + provider);
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d(TAG, "Provider disabled: " + provider);
    }

    private boolean checkLocationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_LOCATION);
    }

    @SuppressLint("MissingPermission")
    private boolean isLocationEnabled() {
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt(getContentResolver(), Settings.Secure.LOCATION_MODE);
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
            }
            return locationMode != Settings.Secure.LOCATION_MODE_OFF;
        } else {
            locationProviders = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !locationProviders.isEmpty();
        }
    }
}