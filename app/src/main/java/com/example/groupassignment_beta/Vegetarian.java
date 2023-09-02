package com.example.groupassignment_beta;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Set;

public class Vegetarian extends AppCompatActivity {

    private RadioButton RB_YES, RB_NO;
    private RadioGroup options;
    private Button backButton, nextButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetarian);

        getSupportActionBar().hide();

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
                showToast("Next button clicked in vege activity");
                Intent intent = new Intent(Vegetarian.this, SetupDone.class);
                startActivity(intent);
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}