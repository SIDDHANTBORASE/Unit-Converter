package com.example.unitconvertor.Converters;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.unitconvertor.MainActivity;
import com.example.unitconvertor.R;

public class Temperature extends AppCompatActivity {

    private EditText editTextValue;
    private Spinner spinnerUnitsFrom, spinnerUnitsTo;
    private Button buttonConvert, backButton;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temperature);

        // Initialize UI components
        editTextValue = findViewById(R.id.editText_value);
        spinnerUnitsFrom = findViewById(R.id.spinner_units_from);
        spinnerUnitsTo = findViewById(R.id.spinner_units_to);
        buttonConvert = findViewById(R.id.button_convert);
        backButton = findViewById(R.id.backbutton);
        textViewResult = findViewById(R.id.textView_result);

        // Populate spinners with volume units
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.temp_unit, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnitsFrom.setAdapter(adapter);
        spinnerUnitsTo.setAdapter(adapter);

        // Set click listener for the Convert button
        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertVolume();
            }
        });

        // Set click listener for the Back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backFunction();
            }
        });
    }

    private void convertVolume() {
        // Get the value to convert from EditText
        String valueStr = editTextValue.getText().toString().trim();
        if (valueStr.isEmpty()) {
            textViewResult.setText("Please enter a value");
            return;
        }

        try {
            double value = Double.parseDouble(valueStr);
            // Get selected units from spinners
            String unitFrom = spinnerUnitsFrom.getSelectedItem().toString();
            String unitTo = spinnerUnitsTo.getSelectedItem().toString();

            // Perform conversion based on selected units
            double result = convertUnit(value, unitFrom, unitTo);

            // Display the result
            textViewResult.setText(String.format("%.2f %s", result, unitTo));

        } catch (NumberFormatException e) {
            textViewResult.setText("Invalid input. Please enter a valid number.");
        }
    }
    private double convertUnit(double value, String unitFrom, String unitTo) {
        // Conversion logic
        switch (unitFrom) {
            case "Kelvin":
                return convertFromKelvin(value, unitTo);
            case "Celsius":
                return convertFromCelsius(value, unitTo);
            case "Fahrenheit":
                return convertFromFahrenheit(value, unitTo);
            // Add more cases as needed
        }
        return value; // Default: return the same value if no conversion rule is matched
    }

    private double convertFromKelvin(double value, String unitTo) {
        switch (unitTo) {
            case "Kelvin":
                return value;
            case "Celsius":
                return value - 273.15; // Kelvin to Celsius
            case "Fahrenheit":
                return (value - 273.15) * 9/5 + 32; // Kelvin to Fahrenheit
            // Add more conversions as needed
        }
        return value; // Default: return the same value if no conversion rule is matched
    }

    private double convertFromCelsius(double value, String unitTo) {
        switch (unitTo) {
            case "Kelvin":
                return value + 273.15; // Celsius to Kelvin
            case "Celsius":
                return value;
            case "Fahrenheit":
                return (value * 9/5) + 32; // Celsius to Fahrenheit
            // Add more conversions as needed
        }
        return value; // Default: return the same value if no conversion rule is matched
    }

    private double convertFromFahrenheit(double value, String unitTo) {
        switch (unitTo) {
            case "Kelvin":
                return (value - 32) * 5/9 + 273.15; // Fahrenheit to Kelvin
            case "Celsius":
                return (value - 32) * 5/9; // Fahrenheit to Celsius
            case "Fahrenheit":
                return value;
            // Add more conversions as needed
        }
        return value; // Default: return the same value if no conversion rule is matched
    }

    private void backFunction() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Press Back Twice to exit", Toast.LENGTH_SHORT).show();
        finish();
    }
}
