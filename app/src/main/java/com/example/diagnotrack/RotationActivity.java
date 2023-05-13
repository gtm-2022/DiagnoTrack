package com.example.diagnotrack;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RotationActivity extends AppCompatActivity implements SensorEventListener {
    private static final String TAG = "RotationActivity";
    private SensorManager sensorManager;
    private Sensor rotationSensor;
    private TextView orientationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotation);

        orientationTextView = findViewById(R.id.orientation_text_view);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        rotationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, rotationSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
            float[] rotationMatrix = new float[9];
            SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values);

            int screenOrientation = getWindowManager().getDefaultDisplay().getRotation();

            float[] adjustedRotationMatrix = new float[9];
            switch (screenOrientation) {
                case Surface.ROTATION_0:
                    adjustedRotationMatrix = rotationMatrix.clone();
                    break;
                case Surface.ROTATION_90:
                    SensorManager.remapCoordinateSystem(rotationMatrix, SensorManager.AXIS_Y, SensorManager.AXIS_MINUS_X, adjustedRotationMatrix);
                    break;
                case Surface.ROTATION_180:
                    SensorManager.remapCoordinateSystem(rotationMatrix, SensorManager.AXIS_MINUS_X, SensorManager.AXIS_MINUS_Y, adjustedRotationMatrix);
                    break;
                case Surface.ROTATION_270:
                    SensorManager.remapCoordinateSystem(rotationMatrix, SensorManager.AXIS_MINUS_Y, SensorManager.AXIS_X, adjustedRotationMatrix);
                    break;
            }

            float[] orientation = new float[3];
            SensorManager.getOrientation(adjustedRotationMatrix, orientation);

            float pitch = orientation[1];
            float roll = orientation[2];

            String orientationString = "";

            if (pitch < -Math.PI / 4 && pitch > -3 * Math.PI / 4) {
                orientationString = "Portrait (Upside Down)";
            } else if (pitch > Math.PI / 4 && pitch < 3 * Math.PI / 4) {
                orientationString = "Portrait";
            } else if (roll < -Math.PI / 4 && roll > -3 * Math.PI / 4) {
                orientationString = "Landscape (Reverse)";
            } else if (roll > Math.PI / 4 && roll < 3 * Math.PI / 4) {
                orientationString = "Landscape";
            } else if (pitch < -3 * Math.PI / 4 || pitch > 3 * Math.PI / 4) {
                orientationString = "Portrait (Upside Down)";
            } else if (pitch > -Math.PI / 4 && pitch < Math.PI / 4 && roll < -3 * Math.PI / 4) {
                orientationString = "Reverse Portrait";
            } else if (pitch > -Math.PI / 4 && pitch < Math.PI / 4 && roll > 3 * Math.PI / 4) {
                orientationString = "Reverse Portrait (Upside Down)";
            } else if (pitch > -Math.PI / 4 && pitch < Math.PI / 4 && roll > -Math.PI / 4 && roll < Math.PI / 4) {
                orientationString = "Landscape (Reverse)";
            }

            orientationTextView.setText(orientationString);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        if (sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
            switch (accuracy) {
                case SensorManager.SENSOR_STATUS_ACCURACY_HIGH:
                    Log.d(TAG, "Rotation sensor accuracy is high.");
                    break;
                case SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM:
                    Log.d(TAG, "Rotation sensor accuracy is medium.");
                    break;
                case SensorManager.SENSOR_STATUS_ACCURACY_LOW:
                    Log.d(TAG, "Rotation sensor accuracy is low.");
                    break;
                case SensorManager.SENSOR_STATUS_UNRELIABLE:
                    Log.d(TAG, "Rotation sensor accuracy is unreliable.");
                    break;
            }
        }
    }
}