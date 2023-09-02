package com.example.groupassignment_beta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SetupDone extends AppCompatActivity {

    private Button btGetStarted;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_done);
        btGetStarted = findViewById(R.id.get_started);

        getSupportActionBar().hide();


        btGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("All done activity");
                Intent intent = new Intent(SetupDone.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}