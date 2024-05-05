package com.example.rayyanallureapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rayyanallureapp.R;

import java.util.ArrayList;

public class SignupActivity extends AppCompatActivity {
    ArrayList<String> arrLang = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button btnGoLogin;
        TextView txtGoLogin;
        Spinner spinnerLang = findViewById(R.id.spinner);

        txtGoLogin = findViewById(R.id.txtGoLogin);
        btnGoLogin = findViewById(R.id.btnGoLogin);

        txtGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGoLogin = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(iGoLogin);
            }
        });

        btnGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGoLogin = new Intent(SignupActivity.this, LoginActivity.class);

                Toast.makeText(SignupActivity.this, "You have Signed Up Successfully!", Toast.LENGTH_SHORT).show();

                startActivity(iGoLogin);
            }
        });

        arrLang.add("Urdu");
        arrLang.add("English");
        arrLang.add("French");

        ArrayAdapter<String> spinnerAdap = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, arrLang);

        spinnerLang.setAdapter(spinnerAdap);

        spinnerLang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedLanguage = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                String selectedLanguage = null;
            }
        });
    }
}