package com.iter.ivory;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class AddVaccination extends AppCompatActivity {

    ArrayList<String> sublist = new ArrayList<>();
    FirebaseAuth authUser = FirebaseAuth.getInstance();
    User u = new User(authUser.getCurrentUser().getDisplayName(),new ArrayList<Vaccines>());
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_vaccination);

        Spinner vaccinationList = findViewById(R.id.spinner);
        final Spinner vaccinationsubList = findViewById(R.id.spinnersub);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Adenovirus));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vaccinationsubList.setAdapter(adapter);

        Button finishButton = findViewById(R.id.finish);

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextInputEditText yearView = findViewById(R.id.year);
                TextInputEditText monthView = findViewById(R.id.month);
                String yearInput = yearView.getText().toString();
                int yearNumber = Integer.valueOf(yearInput);
                String monthInput = monthView.getText().toString();
                int monthNumber = Integer.valueOf(monthInput);
                Vaccines vac = new Vaccines("To - Do", "subthing", monthNumber, yearNumber);
                u.addVaccinations(vac);
                db.collection("users").document(authUser.getUid()).set(u);
                finish();
            }
        });

        vaccinationList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter<String> adapter;
                switch(position){

                    case 1:
                        adapter = new ArrayAdapter<String>(getApplication(),
                                android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Anthrax));
                    default:
                        adapter = new ArrayAdapter<String>(getApplication(),
                                android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.Adenovirus));

                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    vaccinationsubList.setAdapter(adapter);
                }




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
