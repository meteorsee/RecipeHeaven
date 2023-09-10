package com.example.groupassignment_beta;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.yariksoffice.lingver.Lingver;

public class Settings extends AppCompatActivity {
    private BottomNavigationView bottomNav;
    private SwitchCompat switchMode;
    boolean nightMode;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private TextView change_language;
    private Button logoutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().hide();
        logoutButton = findViewById(R.id.logoutButton);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String displayName = user != null ? user.getDisplayName() : "Unknown User";

                // Sign out the user
                FirebaseAuth.getInstance().signOut();

                // Display a toast message indicating the user has logged out
                Toast.makeText(Settings.this, "Logged out: " + displayName, Toast.LENGTH_SHORT).show();

                // Redirect to the login page
                Intent intent = new Intent(Settings.this, Login.class);
                startActivity(intent);
                finish(); // Optional, to close the current activity
            }
        });

        switchMode = findViewById(R.id.switchMode);
        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nightMode = sharedPreferences.getBoolean("nightMode", false);
        if (nightMode) {
            switchMode.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        switchMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nightMode) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("nightMode", false);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("nightMode", true);
                }
                editor.apply();
            }
        });

        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.settings_icon);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home_icon:
                        startActivity(new Intent(Settings.this, MainActivity.class));
                        break;
                    case R.id.search_new_icon:
                        startActivity(new Intent(Settings.this, SearchPage.class));
                        break;
                    case R.id.bookmark_icon:
                        startActivity(new Intent(Settings.this, Bookmark.class));
                        break;
                    case R.id.cart_icon:
                        startActivity(new Intent(Settings.this, Shopping_cart.class));
                        break;
                }
                return true;
            }
        });

        // Initialize the TextView for changing the language
        change_language = findViewById(R.id.change_language);
        change_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLanguageSelectionDialog();
            }
        });
    }

    private void changeLanguage(String language) {
        // Change the app's language using Lingver
        Lingver.getInstance().setLocale(this, language);
        recreate();
    }

    private void showLanguageSelectionDialog() {
        // Create and show the language selection dialog
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Language");

        // Inflate the language selection dialog layout
        View dialogView = getLayoutInflater().inflate(R.layout.language_selection_dialog, null);
        builder.setView(dialogView);

        // Get RadioButtons and Button from the dialog layout
        RadioButton radioEnglish = dialogView.findViewById(R.id.radioEnglish);
        RadioButton radioChinese = dialogView.findViewById(R.id.radioChinese);
        RadioButton radioMalay = dialogView.findViewById(R.id.radioMalay);
        RadioButton radioTamil = dialogView.findViewById(R.id.radioTamil);
        final AlertDialog dialog = builder.create();

        // Set a listener for the Apply button in the dialog
        dialogView.findViewById(R.id.btnApplyLanguage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radioEnglish.isChecked()) {
                    changeLanguage("en");
                } else if (radioChinese.isChecked()) {
                    changeLanguage("zh");
                } else if (radioMalay.isChecked()) {
                    changeLanguage("ms");
                } else if (radioTamil.isChecked()) {
                    changeLanguage("ta");
                }
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
