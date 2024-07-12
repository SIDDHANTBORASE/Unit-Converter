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

public class LengthConverterActivity extends AppCompatActivity {

    private EditText editTextValue;
    private Spinner spinnerUnitsFrom, spinnerUnitsTo;
    private Button buttonConvert, backButton;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.length_activity);

        // Initialize UI components
        editTextValue = findViewById(R.id.editText_value);
        spinnerUnitsFrom = findViewById(R.id.spinner_units_from);
        spinnerUnitsTo = findViewById(R.id.spinner_units_to);
        buttonConvert = findViewById(R.id.button_convert);
        backButton = findViewById(R.id.backbutton);
        textViewResult = findViewById(R.id.textView_result);

        // Populate spinners with length units
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.length_units, android.R.layout.simple_spinner_item);
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
        double result ;
                switch (unitFrom) {
            case "Centimeters":
                if (unitTo.equals("Meters")) return value/100;
                if (unitTo.equals("Kilometers")) return value / 100000;
                if (unitTo.equals("Feet")) return value / 30.48;
                if (unitTo.equals("Inches")) return value / 2.54;
                if (unitTo.equals("Yards")) return value / 91.44;
                if (unitTo.equals("Miles")) return value / 160934;
                break;
            case "Meters":
                if (unitTo.equals("Centimeters")) return value * 100;
                if (unitTo.equals("Kilometers")) return value / 1000;
                if (unitTo.equals("Feet")) return value * 3.28084;
                if (unitTo.equals("Inches")) return value * 39.3701;
                if (unitTo.equals("Yards")) return value / 0.9144;
                if (unitTo.equals("Miles")) return value / 1609.34;
                break;
            case "Kilometers":
                if (unitTo.equals("Centimeters")) return value * 100000;
                if (unitTo.equals("Meters")) return value * 1000;
                if (unitTo.equals("Feet")) return value * 3280.84;
                if (unitTo.equals("Inches")) return value * 39370.1;
                if (unitTo.equals("Yards")) return value * 1093.61;
                if (unitTo.equals("Miles")) return value / 1.60934;
                break;
            case "Feet":
                if (unitTo.equals("Centimeters")) return value * 30.48;
                if (unitTo.equals("Meters")) return value / 3.28084;
                if (unitTo.equals("Kilometers")) return value / 3280.84;
                if (unitTo.equals("Inches")) return value * 12;
                if (unitTo.equals("Yards")) return value / 3;
                if (unitTo.equals("Miles")) return value / 5280;
                break;
            case "Inches":
                if (unitTo.equals("Centimeters")) return value * 2.54;
                if (unitTo.equals("Meters")) return value * 0.0254;
                if (unitTo.equals("Kilometers")) return value * 0.0000254;
                if (unitTo.equals("Feet")) return value / 12;
                if (unitTo.equals("Yards")) return value / 36;
                if (unitTo.equals("Miles")) return value / 63360;
                break;
            case "Yards":
                if (unitTo.equals("Centimeters")) return value * 91.44;
                if (unitTo.equals("Meters")) return value * 0.9144;
                if (unitTo.equals("Kilometers")) return value * 0.0009144;
                if (unitTo.equals("Feet")) return value * 3;
                if (unitTo.equals("Inches")) return value * 36;
                if (unitTo.equals("Miles")) return value / 1760;
                break;
            case "Miles":
                if (unitTo.equals("Centimeters")) return value * 160934;
                if (unitTo.equals("Meters")) return value * 1609.34;
                if (unitTo.equals("Kilometers")) return value * 1.60934;
                if (unitTo.equals("Feet")) return value * 5280;
                if (unitTo.equals("Inches")) return value * 63360;
                if (unitTo.equals("Yards")) return value * 1760;
                break;
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
