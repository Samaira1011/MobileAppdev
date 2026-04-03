package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

        Button settings = findViewById(R.id.settingsBtn);

        settings.setOnClickListener(v ->
                startActivity(new Intent(
                        MainActivity.this,
                        SettingsActivity.class)));

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_dropdown_item,
                        currencies);

        from.setAdapter(adapter);
        to.setAdapter(adapter);

        convert.setOnClickListener(v -> convert());
    }

    private void convert(){
        if(amount.getText().toString().isEmpty()){
            result.setText("Enter amount first");
            return;
        }
        double amt = Double.parseDouble(amount.getText().toString());

        String fromCur = from.getSelectedItem().toString();
        String toCur = to.getSelectedItem().toString();

        double inr = 0;

        switch (fromCur){

            case "USD": inr = amt * 83; break;
            case "EUR": inr = amt * 90; break;
            case "JPY": inr = amt * 0.55; break;
            default: inr = amt;
        }

        double resultValue = 0;

        switch (toCur){

            case "USD": resultValue = inr / 83; break;
            case "EUR": resultValue = inr / 90; break;
            case "JPY": resultValue = inr / 0.55; break;
            default: resultValue = inr;
        }

        result.setText("Result: " + String.format("%.2f", resultValue) + " " + toCur);
    }
}