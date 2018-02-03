package com.iter.ivory;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class AddVaccination extends AppCompatActivity {

    FirebaseAuth authUser = FirebaseAuth.getInstance();
    User u = new User(authUser.getCurrentUser().getDisplayName(),new ArrayList<Vaccines>());
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_vaccination);

        Spinner vaccinationList = findViewById(R.id.spinner);

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
                Vaccines vac = new Vaccines("To - Do", monthNumber, yearNumber);
                u.addVaccinations(vac);
                db.collection("users").document(authUser.getUid()).set(u);
            }
        });
    }

}
