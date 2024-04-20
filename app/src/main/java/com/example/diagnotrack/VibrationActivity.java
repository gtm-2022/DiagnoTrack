package com.example.diagnotrack;

import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VibrationActivity extends Activity {

    private Button vibrateButton;
    private TextView textViewStatus;
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vibration);
        textViewStatus=findViewById(R.id.text_vibration_status);

        vibrateButton = findViewById(R.id.button_vibrate);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        vibrateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Vibrate for 500 milliseconds
                vibrator.vibrate(1000);
                textViewStatus.setText("Vibration Status: Vibraton triggered");
            }
        });
    }
}
