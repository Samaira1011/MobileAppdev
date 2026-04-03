package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    EditText amount;
    Spinner from, to;
    Button convert;
    TextView result;

    String[] currencies = {"INR","USD","EUR","JPY"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.amount);
        from = findViewById(R.id.fromCurrency);
        to = findViewById(R.id.toCurrency);
        convert = findViewById(R.id.convertBtn);
        result = findViewById(R.id.result);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_dropdown_item,
                        currencies);

        from.setAdapter(adapter);
        to.setAdapter(adapter);

        convert.setOnClickListener(v -> convertCurrency());
    }

    private void convertCurrency() {

        double amt = Double.parseDouble(amount.getText().toString());

        String fromCur = from.getSelectedItem().toString();
        String toCur = to.getSelectedItem().toString();

        double inr = 0;

        // convert to INR first
        switch (fromCur) {

            case "USD":
                inr = amt * 83;
                break;

            case "EUR":
                inr = amt * 90;
                break;

            case "JPY":
                inr = amt * 0.55;
                break;

            default:
                inr = amt;
        }

        double finalAmount = 0;

        switch (toCur) {

            case "USD":
                finalAmount = inr / 83;
                break;

            case "EUR":
                finalAmount = inr / 90;
                break;

            case "JPY":
                finalAmount = inr / 0.55;
                break;

            default:
                finalAmount = inr;
        }

        result.setText("Result: " + finalAmount + " " + toCur);
    }
}