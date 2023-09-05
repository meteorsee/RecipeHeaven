package com.example.groupassignment_beta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    //private FirebaseAuth mAuth;
    private EditText emailRegister, passwordRegister, nameRegister, usernameRegister;
    private Button registerButton;
    private TextView loginRedirect;

    FirebaseDatabase database;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().hide();

        nameRegister = findViewById(R.id.nameRegister);
        usernameRegister = findViewById(R.id.usernameRegister);
        emailRegister = findViewById(R.id.emailRegister);
        passwordRegister = findViewById(R.id.passwordRegister);
        registerButton = findViewById(R.id.buttonRegister);
        loginRedirect = findViewById(R.id.loginRedirect);


                registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://mad-login-register-5dfe4-default-rtdb.firebaseio.com/");


                String name = nameRegister.getText().toString();
                String username = usernameRegister.getText().toString();
                String email = emailRegister.getText().toString();
                String password = passwordRegister.getText().toString();

                HelperClass helperClass = new HelperClass(name, username, email, password);
                reference.child(username).setValue(helperClass);

                Toast.makeText(Register.this, "Registration successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);

            }
        });

        loginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });
    }
}
