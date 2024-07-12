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

public class VolumeConverterActivity extends AppCompatActivity {

    private EditText editTextValue;
    private Spinner spinnerUnitsFrom, spinnerUnitsTo;
    private Button buttonConvert, backButton;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volume);

        // Initialize UI components
        editTextValue = findViewById(R.id.editText_value);
        spinnerUnitsFrom = findViewById(R.id.spinner_units_from);
        spinnerUnitsTo = findViewById(R.id.spinner_units_to);
        buttonConvert = findViewById(R.id.button_convert);
        backButton = findViewById(R.id.backbutton);
        textViewResult = findViewById(R.id.textView_result);

        // Populate spinners with volume units
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.volume_unit, android.R.layout.simple_spinner_item);
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
            case "Liters":
                return convertFromLiters(value, unitTo);
            case "Milliliters":
                return convertFromMilliliters(value, unitTo);
            case "Gallons":
                return convertFromGallons(value, unitTo);
            case "Cups":
                return convertFromCups(value, unitTo);
            // Add more cases as needed
        }
        return value; // Default: return the same value if no conversion rule is matched
    }

    private double convertFromLiters(double value, String unitTo) {
        switch (unitTo) {
            case "Liters":
                return value;
            case "Milliliters":
                return value * 1000; // 1 Liter = 1000 Milliliters
            case "Gallons":
                return value * 0.264172; // 1 Liter = 0.264172 Gallons
            case "Cups":
                return value * 4.22675; // 1 Liter = 4.22675 Cups
            // Add more conversions as needed
        }
        return value; // Default: return the same value if no conversion rule is matched
    }

    private double convertFromMilliliters(double value, String unitTo) {
        switch (unitTo) {
            case "Liters":
                return value / 1000;
            case "Milliliters":
                return value;
            case "Gallons":
                return value * 0.000264172; // 1 Milliliter = 0.000264172 Gallons
            case "Cups":
                return value * 0.00422675; // 1 Milliliter = 0.00422675 Cups
            // Add more conversions as needed
        }
        return value; // Default: return the same value if no conversion rule is matched
    }

    private double convertFromGallons(double value, String unitTo) {
        switch (unitTo) {
            case "Liters":
                return value * 3.78541; // 1 Gallon = 3.78541 Liters
            case "Milliliters":
                return value * 3785.41; // 1 Gallon = 3785.41 Milliliters
            case "Gallons":
                return value;
            case "Cups":
                return value * 16; // 1 Gallon = 16 Cups
            // Add more conversions as needed
        }
        return value; // Default: return the same value if no conversion rule is matched
    }

    private double convertFromCups(double value, String unitTo) {
        switch (unitTo) {
            case "Liters":
                return value * 0.236588; // 1 Cup = 0.236588 Liters
            case "Milliliters":
                return value * 236.588; // 1 Cup = 236.588 Milliliters
            case "Gallons":
                return value * 0.0625; // 1 Cup = 0.0625 Gallons
            case "Cups":
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
