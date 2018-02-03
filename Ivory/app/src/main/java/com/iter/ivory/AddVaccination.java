package com.iter.ivory;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class AddVaccination extends AppCompatActivity {
    ArrayAdapter<CharSequence> adapter;
    ArrayList<String> sublist = new ArrayList<>();
    FirebaseAuth authUser = FirebaseAuth.getInstance();
    User u = new User(authUser.getCurrentUser().getDisplayName(),new ArrayList<Vaccines>());
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_vaccination);

        DocumentReference userinfo;
        userinfo = db.collection("users").document(authUser.getUid());
        userinfo.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                    u = documentSnapshot.toObject(User.class);
            }
        });
        Spinner vaccinationList = findViewById(R.id.spinner);
        final Spinner vaccinationsubList = findViewById(R.id.spinnersub);
        adapter = ArrayAdapter.createFromResource(getApplication(), R.array.Anthrax, android.R.layout.simple_spinner_item);
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
                Vaccines vac = new Vaccines("Anthrax", "AVA (BioThrax)", monthNumber, yearNumber);
                u.addVaccinations(vac);
                db.collection("users").document(authUser.getUid()).set(u);
                finish();
            }
        });

        vaccinationList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){

                    case 1:
                        adapter = ArrayAdapter.createFromResource(getApplication(), R.array.Anthrax, android.R.layout.simple_spinner_item);
                        adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
