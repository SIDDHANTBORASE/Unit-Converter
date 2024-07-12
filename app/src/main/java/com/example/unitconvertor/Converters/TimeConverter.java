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

public class TimeConverter extends AppCompatActivity {

    private EditText editTextValue;
    private Spinner spinnerUnitsFrom, spinnerUnitsTo;
    private Button buttonConvert, backButton;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time);

        // Initialize UI components
        editTextValue = findViewById(R.id.editText_value);
        spinnerUnitsFrom = findViewById(R.id.spinner_units_from);
        spinnerUnitsTo = findViewById(R.id.spinner_units_to);
        buttonConvert = findViewById(R.id.button_convert);
        backButton = findViewById(R.id.backbutton);
        textViewResult = findViewById(R.id.textView_result);

        // Populate spinners with volume units
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.time_unit, android.R.layout.simple_spinner_item);
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
            case "Seconds":
                return convertFromSeconds(value, unitTo);
            case "Minutes":
                return convertFromMinutes(value, unitTo);
            case "Hours":
                return convertFromHours(value, unitTo);
            case "Days":
                return convertFromDays(value, unitTo);
            case "Months":
                return convertFromMonths(value, unitTo);
            case "Years":
                return convertFromYears(value, unitTo);
            case "Milliseconds":
                return convertFromMilliseconds(value, unitTo);
            // Add more cases as needed
        }
        return value; // Default: return the same value if no conversion rule is matched
    }

    private double convertFromSeconds(double value, String unitTo) {
        switch (unitTo) {
            case "Seconds":
                return value;
            case "Milliseconds":
                return value * 1000;
            case "Minutes":
                return value / 60;
            case "Hours":
                return value / 3600;
            case "Days":
                return value / 86400;
            case "Months":
                return value / 2.628e+6; // Average month length in seconds
            case "Years":
                return value / 3.154e+7; // Average year length in seconds
        }
        return value; // Default: return the same value if no conversion rule is matched
    }

    private double convertFromMinutes(double value, String unitTo) {
        switch (unitTo) {
            case "Seconds":
                return value * 60;
            case "Milliseconds":
                return value * 60000;
            case "Minutes":
                return value;
            case "Hours":
                return value / 60;
            case "Days":
                return value / 1440;
            case "Months":
                return value / 43800; // Average month length in minutes
            case "Years":
                return value / 525600; // Average year length in minutes
        }
        return value; // Default: return the same value if no conversion rule is matched
    }

    private double convertFromHours(double value, String unitTo) {
        switch (unitTo) {
            case "Seconds":
                return value * 3600;
            case "Milliseconds":
                return value * 3.6e+6;
            case "Minutes":
                return value * 60;
            case "Hours":
                return value;
            case "Days":
                return value / 24;
            case "Months":
                return value / 730; // Average month length in hours
            case "Years":
                return value / 8760; // Average year length in hours
        }
        return value; // Default: return the same value if no conversion rule is matched
    }

    private double convertFromDays(double value, String unitTo) {
        switch (unitTo) {
            case "Seconds":
                return value * 86400;
            case "Milliseconds":
                return value * 8.64e+7;
            case "Minutes":
                return value * 1440;
            case "Hours":
                return value * 24;
            case "Days":
                return value;
            case "Months":
                return value / 30.417; // Average month length in days
            case "Years":
                return value / 365;
        }
        return value; // Default: return the same value if no conversion rule is matched
    }

    private double convertFromMonths(double value, String unitTo) {
        switch (unitTo) {
            case "Seconds":
                return value * 2.628e+6;
            case "Milliseconds":
                return value * 2.628e+9;
            case "Minutes":
                return value * 43800;
            case "Hours":
                return value * 730;
            case "Days":
                return value * 30.417;
            case "Months":
                return value;
            case "Years":
                return value / 12;
        }
        return value; // Default: return the same value if no conversion rule is matched
    }

    private double convertFromYears(double value, String unitTo) {
        switch (unitTo) {
            case "Seconds":
                return value * 3.154e+7;
            case "Milliseconds":
                return value * 3.154e+10;
            case "Minutes":
                return value * 525600;
            case "Hours":
                return value * 8760;
            case "Days":
                return value * 365;
            case "Months":
                return value * 12;
            case "Years":
                return value;
        }
        return value; // Default: return the same value if no conversion rule is matched
    }

    private double convertFromMilliseconds(double value, String unitTo) {
        switch (unitTo) {
            case "Seconds":
                return value / 1000;
            case "Milliseconds":
                return value;
            case "Minutes":
                return value / 60000;
            case "Hours":
                return value / 3.6e+6;
            case "Days":
                return value / 8.64e+7;
            case "Months":
                return value / 2.628e+9; // Average month length in milliseconds
            case "Years":
                return value / 3.154e+10; // Average year length in milliseconds
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
