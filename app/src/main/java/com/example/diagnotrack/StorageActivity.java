package com.example.diagnotrack;

import android.os.Bundle;
import android.os.Environment;
import android.text.format.Formatter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class StorageActivity extends AppCompatActivity {

    private TextView mTextViewExternalStorage;
    private TextView mTextViewInternalStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        mTextViewExternalStorage = findViewById(R.id.textViewExternalStorage);
        mTextViewInternalStorage = findViewById(R.id.textViewInternalStorage);

        checkExternalStorage();
        checkInternalStorage();
    }

    private void checkExternalStorage() {
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // We can read and write the media
            File path = Environment.getExternalStorageDirectory();
            long totalSize = path.getTotalSpace();
            long availableSize = path.getFreeSpace();
            String totalFormatted = Formatter.formatFileSize(this, totalSize);
            String availableFormatted = Formatter.formatFileSize(this, availableSize);

            mTextViewExternalStorage.setText("Total space: " + totalFormatted + "\n" +
                    "Available space: " + availableFormatted);
        } else {
            mTextViewExternalStorage.setText("External storage not mounted");
        }
    }

    private void checkInternalStorage() {
        File path = Environment.getDataDirectory();
        long totalSize = path.getTotalSpace();
        long availableSize = path.getFreeSpace();
        String totalFormatted = Formatter.formatFileSize(this, totalSize);
        String availableFormatted = Formatter.formatFileSize(this, availableSize);

        mTextViewInternalStorage.setText("Total space: " + totalFormatted + "\n" +
                "Available space: " + availableFormatted);
    }
}
