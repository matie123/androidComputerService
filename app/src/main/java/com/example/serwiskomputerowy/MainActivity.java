package com.example.serwiskomputerowy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {

    Spinner spinnerDevice;
    EditText etReporter, etDescription;
    Button btnSave, btnList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerDevice = findViewById(R.id.spinnerDevice);
        etReporter = findViewById(R.id.etReporter);
        etDescription = findViewById(R.id.etDescription);

        btnSave = findViewById(R.id.btnSave);
        btnList = findViewById(R.id.btnList);

        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(
                        this,
                        R.array.devices,
                        android.R.layout.simple_spinner_item
                );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spinnerDevice.setAdapter(adapter);

        btnSave.setOnClickListener(v -> {

            String reporter = etReporter.getText().toString().trim();
            String description = etDescription.getText().toString().trim();

            if (description.isEmpty()) {
                Toast.makeText(this,
                        "Wpisz opis problemu!",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            String device = spinnerDevice.getSelectedItem().toString();

            if (reporter.isEmpty()) {
                reporter = "Nie podano";
            }

            Repair repair = new Repair(
                    device,
                    reporter,
                    description,
                    "Przyjęte"
            );

            RepairStorage.reports.add(repair);

            Toast.makeText(this,
                    "Zgłoszenie zostało dodane!",
                    Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(
                    MainActivity.this,
                    ReportsActivity.class
            );

            startActivity(intent);
            finish();
        });

        btnList.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    ReportsActivity.class
            );

            startActivity(intent);
        });
    }
}