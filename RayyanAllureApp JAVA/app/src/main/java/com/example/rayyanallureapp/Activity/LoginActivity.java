package com.example.rayyanallureapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rayyanallureapp.R;

public class LoginActivity extends AppCompatActivity {

    private static final String PREF_NAME = "MyPrefs";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // ActivityMainBinding binder = getLayoutInflater();
        //setContentView(binder.getRoot());

        //binder.txtGoSignup.setText("Rayan");

        TextView txtGoSignup;
        Button btnGoHome;
        EditText editLoginEmail;
        EditText editLoginPassword;

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        btnGoHome = findViewById(R.id.btnGoHome);
        txtGoSignup = findViewById(R.id.txtGoSignup);
        editLoginEmail = findViewById(R.id.editLoginEmail);
        editLoginPassword = findViewById(R.id.editLoginPassword);

        // Restore saved username and password
        String savedUsername = sharedPreferences.getString(KEY_USERNAME, "");
        String savedPassword = sharedPreferences.getString(KEY_PASSWORD, "");
        editLoginEmail.setText(savedUsername);
        editLoginPassword.setText(savedPassword);

        // Go to Sign Up
        txtGoSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGoSignup = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(iGoSignup);
            }
        });

        // Go to Home
        btnGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGoHome = new Intent(LoginActivity.this, HomeActivity.class);

                String password = editLoginPassword.getText().toString();
                String email = editLoginEmail.getText().toString();
                String[] parts = email.split("@");
                String name = parts[0];

                if (email.isEmpty() || email == null || password.length() < 6) {
                    Toast.makeText(LoginActivity.this, "Login Failed: Please check your credentials!", Toast.LENGTH_SHORT).show();
                } else {
                    // Save username and password in shared preferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_USERNAME, email);
                    editor.putString(KEY_PASSWORD, password);
                    editor.apply();

                    Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    iGoHome.putExtra("Name", name);
                    iGoHome.putExtra("title", "Home");
                    startActivity(iGoHome);
                }
            }
        });
    }
}
