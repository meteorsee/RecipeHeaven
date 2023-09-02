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

public class Halal extends AppCompatActivity {

    private static final String TAG = "HalalActivity"; // Define a tag for logging
    private Button backButton, nextButton;
    private RadioButton RB_HALAL, RB_NON_HALAL;
    private RadioGroup options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halal);

        getSupportActionBar().hide();

        Log.d(TAG, "onCreate: Halal activity started");

        backButton = findViewById(R.id.back);
        nextButton = findViewById(R.id.next);
        RB_HALAL = findViewById(R.id.halal);
        RB_NON_HALAL = findViewById(R.id.non_halal);

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
                showToast("Next button clicked");
                Log.d(TAG, "onClick: Next button clicked");
                Intent intent = new Intent(Halal.this, Vegetarian.class);
                startActivity(intent);
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}