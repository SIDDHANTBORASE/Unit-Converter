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

public class SpeedConverter extends AppCompatActivity {

    private EditText editTextValue;
    private Spinner spinnerUnitsFrom, spinnerUnitsTo;
    private Button buttonConvert, backButton;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speed);

        // Initialize UI components
        editTextValue = findViewById(R.id.editText_value);
        spinnerUnitsFrom = findViewById(R.id.spinner_units_from);
        spinnerUnitsTo = findViewById(R.id.spinner_units_to);
        buttonConvert = findViewById(R.id.button_convert);
        backButton = findViewById(R.id.backbutton);
        textViewResult = findViewById(R.id.textView_result);

        // Populate spinners with volume units
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.speed_unit, android.R.layout.simple_spinner_item);
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

            String unit;
            if (unitTo == "m/s"){
                unit = "Meter Per Second";
            }
            else if(unitTo == "km/h") unit = "Kilometer per Second";
            else if(unitTo == "mi/h") unit = "Miles Per Second";
            else unit = "Feets per Second";
            // Display the result
            textViewResult.setText(String.format("%.2f %s", result, unit));

        } catch (NumberFormatException e) {
            textViewResult.setText("Invalid input. Please enter a valid number.");
        }
    }
    private double convertUnit(double value, String unitFrom, String unitTo) {
        // Conversion logic
        switch (unitFrom) {
            case "m/s":
                return convertFromMetersPerSecond(value, unitTo);
            case "km/h":
                return convertFromKilometersPerHour(value, unitTo);
            case "mi/h":
                return convertFromMilesPerHour(value, unitTo);
            case "ft/s":
                return convertFromFeetPerSecond(value, unitTo);
            // Add more cases as needed
        }
        return value; // Default: return the same value if no conversion rule is matched
    }

    private double convertFromMetersPerSecond(double value, String unitTo) {
        switch (unitTo) {
            case "m/s":
                return value;
            case "km/h":
                return value * 3.6;
            case "mi/h":
                return value * 2.23694;
            case "ft/s":
                return value * 3.28084;
        }
        return value; // Default: return the same value if no conversion rule is matched
    }

    private double convertFromKilometersPerHour(double value, String unitTo) {
        switch (unitTo) {
            case "m/s":
                return value / 3.6;
            case "km/h":
                return value;
            case "mi/h":
                return value * 0.621371;
            case "ft/s":
                return value * 0.911344;
        }
        return value; // Default: return the same value if no conversion rule is matched
    }

    private double convertFromMilesPerHour(double value, String unitTo) {
        switch (unitTo) {
            case "m/s":
                return value / 2.23694;
            case "km/h":
                return value / 0.621371;
            case "mi/h":
                return value;
            case "ft/s":
                return value * 1.46667;
        }
        return value; // Default: return the same value if no conversion rule is matched
    }

    private double convertFromFeetPerSecond(double value, String unitTo) {
        switch (unitTo) {
            case "m/s":
                return value / 3.28084;
            case "km/h":
                return value / 0.911344;
            case "mi/h":
                return value / 1.46667;
            case "ft/s":
                return value;
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
