package com.example.groupassignment_beta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Vegetarian extends AppCompatActivity {

    private RadioButton RB_YES, RB_NO;
    private RadioGroup options;
    private Button backButton, nextButton;
    private FirebaseAuth mAuth; // Initialize Firebase Authentication

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetarian);

        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance(); // Initialize Firebase Authentication

        RB_YES = findViewById(R.id.vege_yes);
        RB_NO = findViewById(R.id.vege_no);
        options = findViewById(R.id.options);
        backButton = findViewById(R.id.back);
        nextButton = findViewById(R.id.next);

        // Set OnClickListener for RadioButtons
        RB_YES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("YES selected");
            }
        });

        RB_NO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("NO selected");
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Back button clicked in vege activity");
                Intent intent = new Intent(Vegetarian.this, Halal.class);
                startActivity(intent);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = mAuth.getCurrentUser();

                String selectedVegetarian = getSelectedVegetarian();
                updateVegetarianInFirebase(selectedVegetarian);

                showToast("Next button clicked in vege activity");
                Intent intent = new Intent(Vegetarian.this, SetupDone.class);
                startActivity(intent);
            }
        });
    }

    private String getSelectedVegetarian() {
        int selectedRadioButtonId = options.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

        if (selectedRadioButton != null) {
            return selectedRadioButton.getText().toString();
        } else {
            return "";
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void updateVegetarianInFirebase(String selectedVegetarian) {
        // Get the current user
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            // Get the user's unique ID
            String userId = user.getUid();

            // Reference to the Firebase database
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

            // Reference to the user's data
            DatabaseReference userRef = databaseReference.child("users").child(userId);

            // Update the cooking level field in the user's data
            userRef.child("vegetarianPreference").setValue(selectedVegetarian);

            showToast("Vegetarian Preferences updated in Firebase");
        } else {
            showToast("User not logged in");
        }
    }
}