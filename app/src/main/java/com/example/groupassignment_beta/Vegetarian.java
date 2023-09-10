package com.example.groupassignment_beta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
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

    private Boolean checkProgress = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetarian);

        getProgressBar();
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

                if(!checkProgress){
                    updateProgressBar();
                }
            }
        });

        RB_NO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!checkProgress){
                    updateProgressBar();
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementProgressBar();
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


                if(!checkProgress){
                    updateProgressBar();
                }

                Intent intent = new Intent(Vegetarian.this, SetupDone.class);
                startActivity(intent);
                getProgressBar();
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

    private void getProgressBar(){
        // Access the ProgressManager instance
        ProgressManager progressManager = ProgressManager.getInstance();

        // To get the current progress
        int currentProgress = progressManager.getProgress();
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(currentProgress);
    }

    private void updateProgressBar() {
        ProgressManager progressManager = ProgressManager.getInstance();

        int currentProgress = progressManager.getProgress();

        // To increment the progress
        int newProgress = currentProgress + 1; // Increment by 1
        progressManager.setProgress(newProgress);

        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(newProgress);
        checkProgress = true;
    }

    private void decrementProgressBar() {
        ProgressManager progressManager = ProgressManager.getInstance();

        int currentProgress = progressManager.getProgress();

        // To decrement the progress
        int newProgress = currentProgress - 1; // Decrement by 1
        progressManager.setProgress(newProgress);
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

        } else {
            showToast("User not logged in");
        }
    }
}