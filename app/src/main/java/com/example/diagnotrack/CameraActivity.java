package com.example.diagnotrack;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CameraActivity extends AppCompatActivity {

    private static final int CAMERA_PERMISSION_CODE = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;

    private TextView diagnosisTextView;
    private ImageView pictureImageView;

    private Bitmap pictureBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        diagnosisTextView = findViewById(R.id.diagnosisTextView);
        pictureImageView = findViewById(R.id.pictureImageView);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
        } else {
            checkCameraDiagnosis();
        }

        Button takePictureButton = findViewById(R.id.takePictureButton);
        takePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
    }

    private void checkCameraDiagnosis() {
        String diagnosis = "";
        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            diagnosis += "Camera is available\n";
        } else {
            diagnosis += "Camera is not available\n";
        }

        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_AUTOFOCUS)) {
            diagnosis += "Camera autofocus is supported\n";
        } else {
            diagnosis += "Camera autofocus is not supported\n";
        }

        diagnosisTextView.setText(diagnosis);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras != null && extras.get("data") != null) {
                pictureBitmap = (Bitmap) extras.get("data");
                pictureImageView.setImageBitmap(pictureBitmap);
                savePicture(pictureBitmap);
                pictureImageView.setVisibility(View.VISIBLE);
            }
        }
    }

    private void savePicture(Bitmap bitmap) {
        File directory = getApplicationContext().getFilesDir();
        File pictureFile = new File(directory, "picture.jpg");

        try (FileOutputStream fos = new FileOutputStream(pictureFile)) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                checkCameraDiagnosis();
            } else {
                diagnosisTextView.setText("Camera permission is required to use the camera");
            }
        }
    }
}