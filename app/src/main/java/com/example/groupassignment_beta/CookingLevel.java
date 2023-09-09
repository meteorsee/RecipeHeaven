package com.example.groupassignment_beta;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class CookingLevel extends AppCompatActivity {

    private static final String TAG = "CookingLevelActivity";

    private RadioButton beginner, homecook, professionalChef;
    private RadioGroup options;
    private Button btNext;
    private FirebaseAuth mAuth; // Initialize Firebase Authentication
    private Boolean checkProgress = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooking_level);

        getProgressBar();
        getSupportActionBar().hide();

        Log.d(TAG, "onCreate: Activity started");

        mAuth = FirebaseAuth.getInstance(); // Initialize Firebase Authentication

        beginner = findViewById(R.id.beginner);
        homecook = findViewById(R.id.homecook);
        professionalChef = findViewById(R.id.professional_chef);
        options = findViewById(R.id.options);
        btNext = findViewById(R.id.next);

        // Set OnClickListener for RadioButtons
        beginner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Beginner selected");
                if(!checkProgress){
                    updateProgressBar();
                }
            }
        });

        homecook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Homecook selected");
                if(!checkProgress){
                    updateProgressBar();
                }
            }
        });

        professionalChef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Professional Chef selected");
                if(!checkProgress){
                    updateProgressBar();
                }
            }
        });

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if the user is authenticated
                /*FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    showToast("Next button clicked");
                    String selectedCookingLevel = getSelectedCookingLevel();
                    updateCookingLevelInFirebase(selectedCookingLevel);
*/
                    // Start the next activity
                    Intent intent = new Intent(CookingLevel.this, Halal.class);
                    startActivity(intent);
                /*} else {
                    showToast("User not logged in");*/
                    // You can handle this case by redirecting the user to the login page.
                    // For example:
                    // Intent intent = new Intent(CookingLevel.this, Login.class);
                    // startActivity(intent);
               // }
            }
        });
    }

    private String getSelectedCookingLevel() {
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

    private void updateCookingLevelInFirebase(String cookingLevel) {
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
            userRef.child("cookingLevel").setValue(cookingLevel);

            showToast("Cooking level updated in Firebase");
        } else {
            showToast("User not logged in");
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
        int newProgress = ++currentProgress; // Increment by 1
        progressManager.setProgress(newProgress);

        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(newProgress);
        checkProgress = true;
    }
}


/*
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

public class CookingLevel extends AppCompatActivity {
    private FirebaseAuth mAuth; // Initialize Firebase Authentication

    private static final String TAG = "LevelActivity";

    private RadioButton begineer, homecook, professionalChef;
    private RadioGroup options;
    private Button btNext;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooking_level);

        getSupportActionBar().hide();

        Log.d(TAG, "onCreate: Activity started");

        mAuth = FirebaseAuth.getInstance(); // Initialize Firebase Authentication


        begineer = findViewById(R.id.begineer);
        homecook = findViewById(R.id.homecook);
        professionalChef = findViewById(R.id.professional_chef);
        options = findViewById(R.id.options);
        btNext = findViewById(R.id.next);

        // Set OnClickListener for RadioButtons
        begineer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Beginner selected");
            }
        });

        homecook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Homecook selected");
            }
        });

        professionalChef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Professional Chef selected");
            }
        });

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Next button clicked");
                String selectedCookingLevel = getSelectedCookingLevel();
                updateCookingLevelInFirebase(selectedCookingLevel);

                // Start the next activity
                Intent intent = new Intent(CookingLevel.this, Halal.class);
                startActivity(intent);
            }
        });

    }

    private String getSelectedCookingLevel() {
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

    private void updateCookingLevelInFirebase(String cookingLevel) {
        // Get the current user
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            // Get the user's unique ID
            String userId = user.getUid();

            // Reference to the Firebase database
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

            // Reference to the user's data
            DatabaseReference userRef = databaseReference.child("users").child(userId);

            // Update the cooking level field in the user's data
            userRef.child("cookingLevel").setValue(cookingLevel);

            showToast("Cooking level updated in Firebase");
        } else {
            showToast("User not logged in");
        }
    }
}*/
