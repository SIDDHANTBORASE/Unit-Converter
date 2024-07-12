package com.example.unitconvertor;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.unitconvertor.Converters.AreaConverter;
import com.example.unitconvertor.Converters.DataConverter;
import com.example.unitconvertor.Converters.LengthConverterActivity;
import com.example.unitconvertor.Converters.SpeedConverter;
import com.example.unitconvertor.Converters.Temperature;
import com.example.unitconvertor.Converters.TimeConverter;
import com.example.unitconvertor.Converters.VolumeConverterActivity;
import com.example.unitconvertor.Converters.WeightCon;

public class MainActivity extends AppCompatActivity {

    private ImageButton buttonLength, buttonWeight, buttonVolume, buttonTemperature,
            buttonArea, buttonSpeed, buttonTime, buttonData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize buttons
        buttonLength = findViewById(R.id.button_length);
        buttonWeight = findViewById(R.id.button_weight);
        buttonVolume = findViewById(R.id.button_volume);
        buttonTemperature = findViewById(R.id.button_temperature);
        buttonArea = findViewById(R.id.button_area);
        buttonSpeed = findViewById(R.id.button_speed);
        buttonTime = findViewById(R.id.button_time);
        buttonData = findViewById(R.id.button_data);

        // Set click listeners
        buttonLength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LengthConverterActivity.class));
                finish();
            }
        });

        buttonWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WeightCon.class));
                finish();
            }
        });

        buttonVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, VolumeConverterActivity.class));
                finish();
            }
        });

        buttonTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Temperature.class));
                finish();
            }
        });

        buttonArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AreaConverter.class));
                finish();
            }
        });

        buttonSpeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SpeedConverter.class));
                finish();
            }
        });

        buttonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TimeConverter.class));
                finish();
            }
        });

        buttonData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DataConverter.class));
                finish();
            }
        });
    }
}


