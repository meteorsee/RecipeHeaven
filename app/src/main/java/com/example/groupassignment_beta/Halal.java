package com.example.groupassignment_beta;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class Halal extends AppCompatActivity {

    private static final String TAG = "HalalActivity"; // Define a tag for logging
    private Button backButton, nextButton;
    private RadioButton RB_HALAL, RB_NON_HALAL;
    private RadioGroup options;

    private FirebaseAuth mAuth; // Initialize Firebase Authentication

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halal);

        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance(); // Initialize Firebase Authentication

        Log.d(TAG, "onCreate: Halal activity started");

        backButton = findViewById(R.id.back);
        nextButton = findViewById(R.id.next);
        RB_HALAL = findViewById(R.id.halal);
        RB_NON_HALAL = findViewById(R.id.non_halal);
        options = findViewById(R.id.options); // Initialize the RadioGroup

        RB_HALAL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("YES selected");
                Log.d(TAG, "onClick: YES selected");
            }
        });

        RB_NON_HALAL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("NO selected");
                Log.d(TAG, "onClick: NO selected");
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Back button clicked");
                Log.d(TAG, "onClick: Back button clicked");
                Intent intent = new Intent(Halal.this, CookingLevel.class);
                startActivity(intent);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = mAuth.getCurrentUser();

                String selectedHalal = getSelectedHalal();
                updateHalalInFirebase(selectedHalal);

                showToast("Next button clicked");
                Log.d(TAG, "onClick: Next button clicked");
                Intent intent = new Intent(Halal.this, Vegetarian.class);
                startActivity(intent);
            }
        });
    }

    private String getSelectedHalal() {
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

    private void updateHalalInFirebase(String selectedHalal) {
        // Get the current user
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            // Get the user's unique ID
            String userId = user.getUid();

            // Reference to the Firebase database
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

            // Reference to the user's data
            DatabaseReference userRef = databaseReference.child("users").child(userId);

            // Update the halalPreferences field in the user's data
            userRef.child("halalPreferences").setValue(selectedHalal);

            showToast("Halal Preferences updated in Firebase");

        } else {
            showToast("User not logged in");
        }
    }
}
