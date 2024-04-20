package com.example.diagnotrack;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CompassActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Sensor magnetometer;
    private ImageView compassImage;
    private TextView headingText;
    private float[] gravityValues = new float[3];
    private float[] magneticValues = new float[3];
    private float currentHeading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);

        compassImage = findViewById(R.id.compass);
        headingText = findViewById(R.id.heading_text);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            gravityValues = event.values.clone();
        }
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            magneticValues = event.values.clone();
        }
        float[] rotationMatrix = new float[9];
        boolean success = SensorManager.getRotationMatrix(rotationMatrix, null, gravityValues, magneticValues);
        if (success) {
            float[] orientation = new float[3];
            SensorManager.getOrientation(rotationMatrix, orientation);
            currentHeading = (float) Math.toDegrees(orientation[0]);
            currentHeading = (currentHeading + 360) % 360; // Normalize to 0-360 degrees
            updateUI(currentHeading);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used in this implementation
    }

    private void updateUI(float heading) {
        compassImage.setRotation(-heading);
        headingText.setText(String.format("Heading: %.2f°", heading));
    }
}
