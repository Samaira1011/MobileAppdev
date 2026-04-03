package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

    Button light, dark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        light = findViewById(R.id.lightBtn);
        dark = findViewById(R.id.darkBtn);

        light.setOnClickListener(v ->
                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_NO));

        dark.setOnClickListener(v ->
                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_YES));
    }
}