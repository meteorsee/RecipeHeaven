package com.example.groupassignment_beta;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Shopping_cart extends AppCompatActivity {
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        getSupportActionBar().hide();

        bottomNav = findViewById(R.id.bottom_navigation);

        bottomNav.setSelectedItemId(R.id.cart_icon);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.home_icon:
                        // Start the ProfileActivity
                        startActivity(new Intent(Shopping_cart.this, MainActivity.class));
                        break;
                    case R.id.search_new_icon:
                        // Start the ProfileActivity
                        startActivity(new Intent(Shopping_cart.this, SearchPage.class));
                        break;
                    case R.id.bookmark_icon:
                        // Start the ProfileActivity
                        startActivity(new Intent(Shopping_cart.this, Bookmark.class));
                        break;

                    case R.id.settings_icon:
                        // Start the ProfileActivity
                        startActivity(new Intent(Shopping_cart.this, Settings.class));
                        break;
                }
                return true;
            }
        });
    }
}