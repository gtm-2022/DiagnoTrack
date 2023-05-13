package com.example.diagnotrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

public class FlashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);
        ToggleButton toggleButton=findViewById(R.id.toggleButton);

        final CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        final String cameraId;
        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
            return;
        }
        final boolean[] isFlashlightOn = {false};
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (isFlashlightOn[0]) {
                        cameraManager.setTorchMode(cameraId, false);
                        isFlashlightOn[0] = false;
                        Toast.makeText(FlashActivity.this, "FlashLight is turned   off", Toast.LENGTH_SHORT).show();
                    } else {
                        cameraManager.setTorchMode(cameraId, true);
                        isFlashlightOn[0] = true;
                        Toast.makeText(FlashActivity.this, "FlashLight is turned on", Toast.LENGTH_SHORT).show();
                    }
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
        });




    }
}