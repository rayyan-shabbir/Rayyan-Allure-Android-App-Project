package com.example.rayyanallureapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.rayyanallureapp.R;

public class ParcelablePlacesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcelable_places);

        Intent intent = getIntent();

        PlaceModal placeModal1  = intent.getParcelableExtra("Item Place1");
        PlaceModal placeModal2  = intent.getParcelableExtra("Item Place2");
        PlaceModal placeModal3  = intent.getParcelableExtra("Item Place3");

//        PlaceModal p1 = arr.get(0);
//        PlaceModal p2 = arr.get(1);
//        PlaceModal p3 = arr.get(2);
//
        TextView tn1, ta1, tn2, ta2, tn3, ta3;
        tn1 = findViewById(R.id.txtName1);
        tn2 = findViewById(R.id.txtName2);
        tn3 = findViewById(R.id.txtName2);

        ta1 = findViewById(R.id.txtAddress1);
        ta2 = findViewById(R.id.txtAddress2);
        ta3 = findViewById(R.id.txtAddress3);

        String name1 = placeModal1.getName();
        String name2 = placeModal2.getName();
        String name3 = placeModal3.getName();

        String address1 = placeModal1.getAddress();
        String address2 = placeModal2.getAddress();
        String address3 = placeModal3.getAddress();

        tn1.setText(name1);
        tn2.setText(name2);
        tn3.setText(name3);

        ta1.setText(address1);
        ta2.setText(address2);
        ta3.setText(address3);
    }
}