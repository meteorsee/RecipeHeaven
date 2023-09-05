package com.example.groupassignment_beta;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserPreferencesManager {
    private HelperClass user;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    public UserPreferencesManager(HelperClass user) {
        this.user = user;
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
    }

    public void updateHalalPreference(String halalPreference) {
        user.setHalalPreference(halalPreference);
        // Update the Firebase database with the updated user data
        updateUserData();
    }

    public void updateVegetarianPreference(String vegetarianPreference) {
        user.setVegetarianPreference(vegetarianPreference);
        // Update the Firebase database with the updated user data
        updateUserData();
    }

    public void updateCookingLevel(String cookingLevel) {
        user.setCookingLevel(cookingLevel);
        // Update the Firebase database with the updated user data
        updateUserData();
    }

    private void updateUserData() {
        // Get the current user's UID (assuming they are already authenticated)
        String userId = mAuth.getCurrentUser().getUid();

        // Update the user's data in Firebase
        databaseReference.child(userId).setValue(user);
    }
}
