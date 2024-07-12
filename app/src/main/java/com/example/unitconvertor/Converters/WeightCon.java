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

public class WeightCon extends AppCompatActivity {

    private EditText editTextValue;
    private Spinner spinnerUnitsFrom, spinnerUnitsTo;
    private Button buttonConvert, backButton;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weight);

        // Initialize UI components
        editTextValue = findViewById(R.id.editText_value);
        spinnerUnitsFrom = findViewById(R.id.spinner_units_from);
        spinnerUnitsTo = findViewById(R.id.spinner_units_to);
        buttonConvert = findViewById(R.id.button_convert);
        backButton = findViewById(R.id.backbutton);
        textViewResult = findViewById(R.id.textView_result);

        // Populate spinners with length units
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.weight_units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnitsFrom.setAdapter(adapter);
        spinnerUnitsTo.setAdapter(adapter);

        // Set click listener for the Convert button
        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertLength();
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

    private void convertLength() {
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
            case "Milligrams":
                return convertFromMilligrams(value, unitTo);
            case "Grams":
                return convertFromGrams(value, unitTo);
            case "Kilograms":
                return convertFromKilograms(value, unitTo);
            case "Pounds":
                return convertFromPounds(value, unitTo);
            case "Tons":
                return convertFromTons(value, unitTo);
        }
        return value; // Default: return the same value if no conversion rule is matched
    }

    private double convertFromMilligrams(double value, String unitTo) {
        switch (unitTo) {
            case "Grams":
                return value / 1000;
            case "Kilograms":
                return value / 1000000;
            case "Pounds":
                return value / 453592.37;
            case "Tons":
                return value / 1000000000;
            default:
                return value; // No conversion needed if units are the same
        }
    }

    private double convertFromGrams(double value, String unitTo) {
        switch (unitTo) {
            case "Milligrams":
                return value * 1000;
            case "Kilograms":
                return value / 1000;
            case "Pounds":
                return value * 0.00220462;
            case "Tons":
                return value / 1000000;
            default:
                return value;
        }
    }

    private double convertFromKilograms(double value, String unitTo) {
        switch (unitTo) {
            case "Milligrams":
                return value * 1000000;
            case "Grams":
                return value * 1000;
            case "Pounds":
                return value * 2.20462;
            case "Tons":
                return value / 1000;
            default:
                return value;
        }
    }

    private double convertFromPounds(double value, String unitTo) {
        switch (unitTo) {
            case "Milligrams":
                return value * 453592.37;
            case "Grams":
                return value * 453.59237;
            case "Kilograms":
                return value / 2.20462;
            case "Tons":
                return value / 2204.62;
            default:
                return value;
        }
    }

    private double convertFromTons(double value, String unitTo) {
        switch (unitTo) {
            case "Milligrams":
                return value * 1000000000;
            case "Grams":
                return value * 1000000;
            case "Kilograms":
                return value * 1000;
            case "Pounds":
                return value * 2204.62;
            default:
                return value;
        }
    }

    private void backFunction() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Press Back Twice to exit", Toast.LENGTH_SHORT).show();
        finish();
    }
}
