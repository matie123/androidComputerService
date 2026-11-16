package com.example.serwiskomputerowy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ReportsActivity extends Activity {

    ListView lvReports;
    TextView tvEmpty;
    Button btnNew;

    ArrayList<String> titles;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        lvReports = findViewById(R.id.lvReports);
        tvEmpty = findViewById(R.id.tvEmpty);
        btnNew = findViewById(R.id.btnNew);

        titles = new ArrayList<>();

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                titles
        );

        lvReports.setAdapter(adapter);


        lvReports.setOnItemClickListener((parent, view, position, id) -> {

            Intent intent = new Intent(
                    ReportsActivity.this,
                    DetailActivity.class
            );

            intent.putExtra("reportIndex", position);

            startActivity(intent);
        });


        btnNew.setOnClickListener(v -> {

            Intent intent = new Intent(
                    ReportsActivity.this,
                    MainActivity.class
            );

            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        titles.clear();

        for (Repair repair : RepairStorage.reports) {
            titles.add(repair.getListTitle());
        }

        adapter.notifyDataSetChanged();

        if (titles.isEmpty()) {
            tvEmpty.setVisibility(View.VISIBLE);
            lvReports.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            lvReports.setVisibility(View.VISIBLE);
        }
    }
}