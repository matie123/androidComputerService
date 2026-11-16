package com.example.serwiskomputerowy;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends Activity {

    TextView tvDevice, tvReporter, tvDescription, tvStatus;
    Button btnChangeStatus, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvDevice = findViewById(R.id.tvDevice);
        tvReporter = findViewById(R.id.tvReporter);
        tvDescription = findViewById(R.id.tvDescription);
        tvStatus = findViewById(R.id.tvStatus);

        btnChangeStatus = findViewById(R.id.btnChangeStatus);
        btnBack = findViewById(R.id.btnBack);

        int index = getIntent().getIntExtra("reportIndex", -1);

        if (index < 0 || index >= RepairStorage.reports.size()) {
            Toast.makeText(this,
                    "Nie znaleziono zgłoszenia!",
                    Toast.LENGTH_SHORT).show();

            finish();
            return;
        }

        Repair repair = RepairStorage.reports.get(index);

        tvDevice.setText(repair.device);
        tvReporter.setText(repair.reporter);
        tvDescription.setText(repair.description);
        tvStatus.setText(repair.status);

        if (repair.status.equals("Zakończone")) {
            btnChangeStatus.setEnabled(false);
            btnChangeStatus.setText("Naprawa zakończona");
        }

        btnChangeStatus.setOnClickListener(v -> {

            if (repair.status.equals("Przyjęte")) {

                repair.status = "W trakcie";

            } else if (repair.status.equals("W trakcie")) {

                repair.status = "Zakończone";

            }

            tvStatus.setText(repair.status);

            Toast.makeText(this,
                    "Zmieniono status: " + repair.status,
                    Toast.LENGTH_SHORT).show();

            if (repair.status.equals("Zakończone")) {
                btnChangeStatus.setEnabled(false);
                btnChangeStatus.setText("Naprawa zakończona");
            }
        });

        btnBack.setOnClickListener(v -> finish());
    }
}