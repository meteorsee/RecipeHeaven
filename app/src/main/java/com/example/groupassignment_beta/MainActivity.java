package com.example.groupassignment_beta;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNav;

    private Button logoutButton;
    private ImageView peopleLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logoutButton = findViewById(R.id.logoutButton);

        getSupportActionBar().hide();

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String displayName = user != null ? user.getDisplayName() : "Unknown User";

                // Sign out the user
                FirebaseAuth.getInstance().signOut();

                // Display a toast message indicating the user has logged out
                Toast.makeText(MainActivity.this, "Logged out: " + displayName, Toast.LENGTH_SHORT).show();

                // Redirect to the login page
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
                finish(); // Optional, to close the current activity
            }
        });

        peopleLogo = findViewById(R.id.peopleLogo);

        peopleLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirect to the login page
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
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
                Log.d("MainActivity", "Search button pressed");
                try {
                    Intent intent = new Intent(getApplicationContext(), SearchPage.class);
                    startActivity(intent);
                } catch (Exception e){
                    Log.d("MainActivity", "Search button pressed");
                }
            }
        });


        bottomNav = findViewById(R.id.bottom_navigation);

        bottomNav.setSelectedItemId(R.id.home_icon);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.search_new_icon:
                        // Start the ProfileActivity
                        startActivity(new Intent(MainActivity.this, SearchPage.class));
                        break;
                    case R.id.bookmark_icon:
                        // Start the ProfileActivity
                        startActivity(new Intent(MainActivity.this, Bookmark.class));
                        break;
                    case R.id.cart_icon:
                        // Start the ProfileActivity
                        startActivity(new Intent(MainActivity.this, Shopping_cart.class));
                        break;
                    case R.id.settings_icon:
                        // Start the ProfileActivity
                        startActivity(new Intent(MainActivity.this, Settings.class));
                        break;
                }
                return true;
            }
        });
    }

}
