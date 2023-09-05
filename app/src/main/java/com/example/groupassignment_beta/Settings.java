package com.example.groupassignment_beta;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Settings extends AppCompatActivity {
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.settings_icon);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.home_icon:
                        // Start the ProfileActivity
                        startActivity(new Intent(Settings.this, MainActivity.class));
                        break;
                    case R.id.search_new_icon:
                        // Start the ProfileActivity
                        startActivity(new Intent(Settings.this, SearchPage.class));
                        break;
                    case R.id.bookmark_icon:
                        // Start the ProfileActivity
                        startActivity(new Intent(Settings.this, Bookmark.class));
                        break;
                    case R.id.cart_icon:
                        // Start the ProfileActivity
                        startActivity(new Intent(Settings.this, Shopping_cart.class));
                        break;

                }
                return true;
            }
        });
    }
}