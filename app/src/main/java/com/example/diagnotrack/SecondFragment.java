package com.example.diagnotrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class SecondFragment extends Fragment {

    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
         ImageView deviceInfo,battery,sensor,storage,signal,display,sound, camera,rotation,gps,light,notification,power,
                 vibration,wifi,compass,phone,bluetooth,fingerprint,usb,airplane;


        deviceInfo=view.findViewById(R.id.deviceInfo);
        battery=view.findViewById(R.id.battery);
        sensor=view.findViewById(R.id.sensor);
        storage=view.findViewById(R.id.storage);
        signal=view.findViewById(R.id.signal);
        display=view.findViewById(R.id.display);
        sound=view.findViewById(R.id.sound);
        camera=view.findViewById(R.id.camera);
        rotation=view.findViewById(R.id.rotation);
        gps=view.findViewById(R.id.gps);
        light=view.findViewById(R.id.light);
        notification=view.findViewById(R.id.notification);
        power=view.findViewById(R.id.power);
        vibration=view.findViewById(R.id.vibration);
        wifi=view.findViewById(R.id.wifi);
        compass=view.findViewById(R.id.compass);
        phone=view.findViewById(R.id.phone);
        bluetooth=view.findViewById(R.id.bluetooth);
        fingerprint=view.findViewById(R.id.fingerprint);
        usb=view.findViewById(R.id.usb);
        airplane=view.findViewById(R.id.airplane);


        deviceInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), InfoActivity.class);
                startActivity(intent);
            }
        });


        battery.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(),BatteryActivity.class);
                startActivity(intent);
            }
        });

        sensor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(), SensorActivity.class);
                startActivity(intent);
            }
        });


        storage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(),StorageActivity.class);
                startActivity(intent);
            }
        });



        signal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(),SignalActivity.class);
                startActivity(intent);
            }
        });


        display.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(), DisplayActivity.class);
                startActivity(intent);
            }
        });


        sound.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(),SoundActivity.class);
                startActivity(intent);
            }
        });



        camera.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(),CameraActivity.class);
                startActivity(intent);
            }
        });

        rotation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(),RotationActivity.class);
                startActivity(intent);
            }
        });



        gps.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(), LocationActivity.class);
                startActivity(intent);
            }
        });


        light.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(),FlashActivity.class);
                startActivity(intent);
            }
        });

        notification.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(),NotificationActivity.class);
                startActivity(intent);
            }
        });


        power.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(),PowerButtonActivity.class);
                startActivity(intent);
            }
        });


        vibration.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(),VibrationActivity.class);
                startActivity(intent);
            }
        });



        wifi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(),WifiActivity.class);
                startActivity(intent);
            }
        });


        compass.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(),CompassActivity.class);
                startActivity(intent);
            }
        });



        phone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(),PhoneActivity.class);
                startActivity(intent);
            }
        });

        bluetooth.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(),BluetoothActivity.class);
                startActivity(intent);
            }
        });


        fingerprint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(),FingerPrintActivity.class);
                startActivity(intent);
            }
        });


        usb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(),USBActivity.class);
                startActivity(intent);
            }
        });



        airplane.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(),AirplaneActivity.class);
                startActivity(intent);
            }
        });



        return view;
    }
}
