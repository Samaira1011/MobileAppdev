package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

    private void applyBackground(boolean isDark) {
        View root = findViewById(android.R.id.content);

        if (isDark)
            root.setBackgroundColor(0xFF000000);
        else
            root.setBackgroundColor(0xFFFFFFFF);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button light = findViewById(R.id.lightBtn);
        Button dark = findViewById(R.id.darkBtn);

        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);

        boolean isDark = prefs.getBoolean("darkMode", false);
        applyBackground(isDark);

        light.setOnClickListener(v -> {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            prefs.edit().putBoolean("darkMode", false).apply();
            applyBackground(false);
        });

        dark.setOnClickListener(v -> {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            prefs.edit().putBoolean("darkMode", true).apply();
            applyBackground(true);
        });
    }
}