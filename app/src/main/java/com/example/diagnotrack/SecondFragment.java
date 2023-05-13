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

        ImageView imgView2 = view.findViewById(R.id.imageView2);
        imgView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), InfoActivity.class);
                startActivity(intent);
            }
        });

        ImageView imgView=view.findViewById(R.id.imageView);
        imgView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(),BatteryActivity.class);
                startActivity(intent);
            }
        });

        ImageView imgView3=view.findViewById(R.id.imageView3);
        imgView3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(), SensorActivity.class);
                startActivity(intent);
            }
        });

        ImageView imgView4=view.findViewById(R.id.imageView4);
        imgView4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(),StorageActivity.class);
                startActivity(intent);
            }
        });


        ImageView imgView5=view.findViewById(R.id.imageView5);
        imgView5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(),SignalActivity.class);
                startActivity(intent);
            }
        });

        ImageView imgView6=view.findViewById(R.id.imageView6);
        imgView6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(), DisplayActivity.class);
                startActivity(intent);
            }
        });


        ImageView imgView7=view.findViewById(R.id.imageView7);
        imgView7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(),SoundActivity.class);
                startActivity(intent);
            }
        });


        ImageView imgView8=view.findViewById(R.id.imageView8);
        imgView8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(),CameraActivity.class);
                startActivity(intent);
            }
        });


        ImageView imgView9=view.findViewById(R.id.imageView9);
        imgView9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(),RotationActivity.class);
                startActivity(intent);
            }
        });


        ImageView imgView10=view.findViewById(R.id.imageView10);
        imgView10.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(), LocationActivity.class);
                startActivity(intent);
            }
        });


        ImageView imgView11=view.findViewById(R.id.imageView11);
        imgView11.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(),FlashActivity.class);
                startActivity(intent);
            }
        });

        ImageView imgView12=view.findViewById(R.id.imageView12);
        imgView12.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(),NotificationActivity.class);
                startActivity(intent);
            }
        });


        ImageView imgView13=view.findViewById(R.id.imageView13);
        imgView13.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(),PowerButtonActivity.class);
                startActivity(intent);
            }
        });


        ImageView imgView14=view.findViewById(R.id.imageView14);
        imgView14.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(),VibrationActivity.class);
                startActivity(intent);
            }
        });



        ImageView imgView15=view.findViewById(R.id.imageView15);
        imgView15.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(),WifiActivity.class);
                startActivity(intent);
            }
        });


        ImageView imgView16=view.findViewById(R.id.imageView16);
        imgView16.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(),CompassActivity.class);
                startActivity(intent);
            }
        });



        ImageView imgView17=view.findViewById(R.id.imageView17);
        imgView17.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(),PhoneActivity.class);
                startActivity(intent);
            }
        });

        ImageView imgView18=view.findViewById(R.id.imageView18);
        imgView18.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(),BluetoothActivity.class);
                startActivity(intent);
            }
        });


        ImageView imgView19=view.findViewById(R.id.imageView19);
        imgView19.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(),FingerPrintActivity.class);
                startActivity(intent);
            }
        });


        ImageView imgView20=view.findViewById(R.id.imageView20);
        imgView20.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(),USBActivity.class);
                startActivity(intent);
            }
        });



        ImageView imgView21=view.findViewById(R.id.imageView21);
        imgView20.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent =new Intent(getActivity(),AirplaneActivity.class);
                startActivity(intent);
            }
        });



        return view;
    }
}
