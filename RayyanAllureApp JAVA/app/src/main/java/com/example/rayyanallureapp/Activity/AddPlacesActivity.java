package com.example.rayyanallureapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rayyanallureapp.R;

import java.util.ArrayList;

public class AddPlacesActivity extends AppCompatActivity {
    ArrayList<PlaceModal> arr = new ArrayList<>();
    EditText edtName;
    EditText edtAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_places);

        edtName = findViewById(R.id.edtName);
        edtAddress = findViewById(R.id.edtAddress);
        Button btnAction = findViewById(R.id.btnAction);

        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the name and address
                String name = edtName.getText().toString();
                String address = edtAddress.getText().toString();

                if (!name.isEmpty() && !address.isEmpty()) {
                    // Create an Intent to send data back to ParcelablePlacesActivity
                    Toast.makeText(AddPlacesActivity.this, "Item added successfully!", Toast.LENGTH_SHORT).show();
                    arr.add(new PlaceModal(name, address));
                } else {
                    Toast.makeText(AddPlacesActivity.this, "Name and Address fields cannot be empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button btnGoShowPlaces = findViewById(R.id.btnGoShowPlaces);

        btnGoShowPlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGoPlaces = new Intent(AddPlacesActivity.this, ParcelablePlacesActivity.class);

                PlaceModal p1 = new PlaceModal("Sydney Gautium", "Near Place Bar", -1);
                PlaceModal p2 = new PlaceModal("Halwa Gautium", "Club", -1);

                arr.add(p1);
                arr.add(p2);

                iGoPlaces.putExtra("Item Place1", arr.get(arr.size() - 1));
                iGoPlaces.putExtra("Item Place2", arr.get(arr.size() - 2));

                if (arr.size() >= 3) {

                    iGoPlaces.putExtra("Item Place3", arr.get(arr.size() - 3));
                    startActivity(iGoPlaces);
                } else {
                    Toast.makeText(AddPlacesActivity.this, "Please add an item first.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (savedInstanceState != null) {
            String name = savedInstanceState.getString("Name");
            String address = savedInstanceState.getString("Address");

            edtName.setText(String.valueOf(name));
            edtAddress.setText(String.valueOf(address));
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        String name = edtName.getText().toString();
        String address = edtAddress.getText().toString();

        outState.putString("Name", name);
        outState.putString("Address", address);
    }
}
