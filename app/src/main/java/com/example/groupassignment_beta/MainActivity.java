package com.example.groupassignment_beta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button login, register;
    /*private FirebaseAuth auth;
    private Button logout, login;
    private TextView user_details;*/
    private TextView usernameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameTextView = findViewById(R.id.textViewUsername);

        // Retrieve the username passed from LoginActivity
        String username = getIntent().getStringExtra("username");

        // Display the username on the TextView
        usernameTextView.setText("Welcome, " + username);

        login = findViewById(R.id.loginpage);
        register = findViewById(R.id.registerpage);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirect to the login page
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirect to the login page
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);            }
        });

        /*auth = FirebaseAuth.getInstance();
        logout = findViewById(R.id.logout);
        login = findViewById(R.id.login);
        user_details = findViewById(R.id.user_details);

        // Check if the user is authenticated
        FirebaseUser user = auth.getCurrentUser();
        *//*if (user == null) {
            // User is not authenticated, redirect to the login page
            navigateToLogin();
        } else {
            // User is authenticated, display user details
            user_details.setText(user.getEmail());
        }*//*

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Sign out the user
                FirebaseAuth.getInstance().signOut();
                // Clear user details
                user_details.setText("");
                // Redirect to the login page
                navigateToLogin();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirect to the login page
                navigateToLogin();
            }
        });*/

        FrameLayout searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to another page or perform search functionality
                Intent intent = new Intent(MainActivity.this, SearchPage.class);
                startActivity(intent);
            }
        });
    }

}
