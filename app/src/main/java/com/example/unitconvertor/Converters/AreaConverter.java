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

public class AreaConverter extends AppCompatActivity {

    private EditText editTextValue;
    private Spinner spinnerUnitsFrom, spinnerUnitsTo;
    private Button buttonConvert, backButton;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.area);

        // Initialize UI components
        editTextValue = findViewById(R.id.editText_value);
        spinnerUnitsFrom = findViewById(R.id.spinner_units_from);
        spinnerUnitsTo = findViewById(R.id.spinner_units_to);
        buttonConvert = findViewById(R.id.button_convert);
        backButton = findViewById(R.id.backbutton);
        textViewResult = findViewById(R.id.textView_result);

        // Populate spinners with length units
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.area_units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnitsFrom.setAdapter(adapter);
        spinnerUnitsTo.setAdapter(adapter);

        // Set click listener for the Convert button
        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertArea();
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

    private void convertArea() {
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
            case "Square Meter":
                return convertFromSquareMeter(value, unitTo);
            case "Square Yard":
                return convertFromSquareYard(value, unitTo);
            case "Square Foot":
                return convertFromSquareFoot(value, unitTo);
            case "Square Inch":
                return convertFromSquareInch(value, unitTo);
            case "Hectare":
                return convertFromHectare(value, unitTo);
            case "Acre":
                return convertFromAcre(value, unitTo);
        }
        return value; // Default: return the same value if no conversion rule is matched
    }

    private double convertFromSquareMeter(double value, String unitTo) {
        switch (unitTo) {
            case "Square Meter":
                return value;
            case "Square Yard":
                return value * 1.19599; // 1 Square Meter = 1.19599 Square Yards
            case "Square Foot":
                return value * 10.7639; // 1 Square Meter = 10.7639 Square Feet
            case "Square Inch":
                return value * 1550.00; // 1 Square Meter = 1550.00 Square Inches
            case "Hectare":
                return value * 0.0001; // 1 Square Meter = 0.0001 Hectare
            case "Acre":
                return value * 0.000247105; // 1 Square Meter = 0.000247105 Acre
        }
        return value; // Default: return the same value if no conversion rule is matched
    }

    private double convertFromSquareYard(double value, String unitTo) {
        switch (unitTo) {
            case "Square Meter":
                return value * 0.836127; // 1 Square Yard = 0.836127 Square Meters
            case "Square Yard":
                return value;
            case "Square Foot":
                return value * 9.00000; // 1 Square Yard = 9.00000 Square Feet
            case "Square Inch":
                return value * 1296.00; // 1 Square Yard = 1296.00 Square Inches
            case "Hectare":
                return value * 0.0000836127; // 1 Square Yard = 0.0000836127 Hectare
            case "Acre":
                return value * 0.000206612; // 1 Square Yard = 0.000206612 Acre
        }
        return value; // Default: return the same value if no conversion rule is matched
    }

    private double convertFromSquareFoot(double value, String unitTo) {
        switch (unitTo) {
            case "Square Meter":
                return value * 0.092903; // 1 Square Foot = 0.092903 Square Meters
            case "Square Yard":
                return value * 0.111111; // 1 Square Foot = 0.111111 Square Yards
            case "Square Foot":
                return value;
            case "Square Inch":
                return value * 144.000; // 1 Square Foot = 144.000 Square Inches
            case "Hectare":
                return value * 0.0000092903; // 1 Square Foot = 0.0000092903 Hectare
            case "Acre":
                return value * 0.0000229568; // 1 Square Foot = 0.0000229568 Acre
        }
        return value; // Default: return the same value if no conversion rule is matched
    }

    private double convertFromSquareInch(double value, String unitTo) {
        switch (unitTo) {
            case "Square Meter":
                return value * 0.00064516; // 1 Square Inch = 0.00064516 Square Meters
            case "Square Yard":
                return value * 0.000771605; // 1 Square Inch = 0.000771605 Square Yards
            case "Square Foot":
                return value * 0.00694444; // 1 Square Inch = 0.00694444 Square Feet
            case "Square Inch":
                return value;
            case "Hectare":
                return value * 6.4516e-8; // 1 Square Inch = 6.4516e-8 Hectare
            case "Acre":
                return value * 1.59423e-7; // 1 Square Inch = 1.59423e-7 Acre
        }
        return value; // Default: return the same value if no conversion rule is matched
    }

    private double convertFromHectare(double value, String unitTo) {
        switch (unitTo) {
            case "Square Meter":
                return value * 10000.0; // 1 Hectare = 10000.0 Square Meters
            case "Square Yard":
                return value * 11959.9; // 1 Hectare = 11959.9 Square Yards
            case "Square Foot":
                return value * 107639.0; // 1 Hectare = 107639.0 Square Feet
            case "Square Inch":
                return value * 1.55e+7; // 1 Hectare = 1.55e+7 Square Inches
            case "Hectare":
                return value;
            case "Acre":
                return value * 2.47105; // 1 Hectare = 2.47105 Acre
        }
        return value; // Default: return the same value if no conversion rule is matched
    }

    private double convertFromAcre(double value, String unitTo) {
        switch (unitTo) {
            case "Square Meter":
                return value * 4046.86; // 1 Acre = 4046.86 Square Meters
            case "Square Yard":
                return value * 4840.0; // 1 Acre = 4840.0 Square Yards
            case "Square Foot":
                return value * 43560.0; // 1 Acre = 43560.0 Square Feet
            case "Square Inch":
                return value * 6.273e+6; // 1 Acre = 6.273e+6 Square Inches
            case "Hectare":
                return value * 0.404686; // 1 Acre = 0.404686 Hectare
            case "Acre":
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