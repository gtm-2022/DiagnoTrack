package com.example.diagnotrack;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SignalActivity extends AppCompatActivity {

    private TextView mTextViewSignalStatus;
    private TelephonyManager mTelephonyManager;
    private MyPhoneStateListener mPhoneStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signal);

        mTextViewSignalStatus = findViewById(R.id.textViewSignalStatus);

        mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        mPhoneStateListener = new MyPhoneStateListener();
        mTelephonyManager.listen(mPhoneStateListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);

        checkSignalStatus();
    }

    private void checkSignalStatus() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            int type = networkInfo.getType();

            if (type == ConnectivityManager.TYPE_WIFI) {
                // Connected to a Wi-Fi network
                mTextViewSignalStatus.setText("Connected to Wi-Fi network");
            } else if (type == ConnectivityManager.TYPE_MOBILE) {
                // Connected to a mobile network
                int signalStrength = mPhoneStateListener.getSignalStrength();

                if (signalStrength >= -50) {
                    mTextViewSignalStatus.setText("Excellent signal strength");
                } else if (signalStrength >= -70) {
                    mTextViewSignalStatus.setText("Good signal strength");
                } else if (signalStrength >= -80) {
                    mTextViewSignalStatus.setText("Fair signal strength");
                } else {
                    mTextViewSignalStatus.setText("Poor signal strength");
                }
            }
        } else {
            mTextViewSignalStatus.setText("No network connection");
        }
    }

    private class MyPhoneStateListener extends PhoneStateListener {
        private int mSignalStrength;

        public int getSignalStrength() {
            return mSignalStrength;
        }

        @Override
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);

            if (signalStrength.isGsm()) {
                // GSM signal strength
                mSignalStrength = signalStrength.getGsmSignalStrength();
            } else {
                // CDMA signal strength
                mSignalStrength = signalStrength.getCdmaDbm();
            }
        }
    }
}
