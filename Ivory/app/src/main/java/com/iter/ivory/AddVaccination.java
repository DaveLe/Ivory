package com.iter.ivory;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Spinner;

public class AddVaccination extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_vaccination);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Spinner vaccinationList = findViewById(R.id.spinner);

        TextInputEditText yearView = findViewById(R.id.year);
        String yearInput = yearView.getText().toString();
        int yearNumber = Integer.valueOf(yearInput);

        TextInputEditText monthView = findViewById(R.id.month);
        String monthInput = monthView.getText().toString();
        int monthNumber = Integer.valueOf(monthInput);

        Vaccines v = new Vaccines("To - Do", monthNumber, yearNumber);
    }

}
