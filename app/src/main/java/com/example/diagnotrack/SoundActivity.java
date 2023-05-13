package com.example.diagnotrack;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SoundActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);

        Button playButton = findViewById(R.id.btnPlay);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSound();
            }
        });

        Button stopButton = findViewById(R.id.btnStop);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopSound();
            }
        });
    }

    private void playSound() {
        try {
            mMediaPlayer = MediaPlayer.create(this, R.raw.liguidtime);
            mMediaPlayer.setLooping(true);
            mMediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopSound() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}
