package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    Spinner fromCurrency, toCurrency;
    EditText amount;
    TextView result;

    String[] currencies = {"INR", "USD", "EUR", "JPY"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        boolean isDark = prefs.getBoolean("darkMode", false);

        if (isDark)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // background fix
        View root = findViewById(android.R.id.content);

        if (isDark)
            root.setBackgroundColor(0xFF000000);
        else
            root.setBackgroundColor(0xFFFFFFFF);

        fromCurrency = findViewById(R.id.fromCurrency);
        toCurrency = findViewById(R.id.toCurrency);
        amount = findViewById(R.id.amount);
        result = findViewById(R.id.result);

        Button convertBtn = findViewById(R.id.convertBtn);
        Button settingsBtn = findViewById(R.id.settingsBtn);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, currencies);

        fromCurrency.setAdapter(adapter);
        toCurrency.setAdapter(adapter);

        convertBtn.setOnClickListener(v -> convertCurrency());

        settingsBtn.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, SettingsActivity.class)));
    }

    private void convertCurrency() {

        if (amount.getText().toString().isEmpty()) {
            result.setText("Enter amount");
            return;
        }

        double amt = Double.parseDouble(amount.getText().toString());

        String from = fromCurrency.getSelectedItem().toString();
        String to = toCurrency.getSelectedItem().toString();

        double rate = getRate(from, to);

        result.setText("Converted: " + (amt * rate));
    }

    private double getRate(String from, String to) {

        double inrToUsd = 0.012;
        double inrToEur = 0.011;
        double inrToJpy = 1.8;

        if (from.equals(to)) return 1;

        double amtInInr = 1;

        switch (from) {
            case "USD": amtInInr = 1 / inrToUsd; break;
            case "EUR": amtInInr = 1 / inrToEur; break;
            case "JPY": amtInInr = 1 / inrToJpy; break;
        }

        switch (to) {
            case "USD": return amtInInr * inrToUsd;
            case "EUR": return amtInInr * inrToEur;
            case "JPY": return amtInInr * inrToJpy;
        }

        return amtInInr;
    }
}