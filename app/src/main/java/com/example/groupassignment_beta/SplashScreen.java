package com.example.groupassignment_beta;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    private static final String TAG = "SplashActivity";
    private static final long SPLASH_DELAY = 3000;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();

        Log.d(TAG, "onCreate: Activity started");

        textView = findViewById(R.id.splashTextView);

        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: Delaying Animation");
                Intent intent = new Intent(SplashScreen.this, CookingLevel.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DELAY);
    }
}
