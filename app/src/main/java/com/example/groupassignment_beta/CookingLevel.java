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

public class CookingLevel extends AppCompatActivity {

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
                Intent intent = new Intent(CookingLevel.this, Halal.class);
                startActivity(intent);
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}