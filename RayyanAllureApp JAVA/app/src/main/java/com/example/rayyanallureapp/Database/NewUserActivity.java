package com.example.rayyanallureapp.Database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rayyanallureapp.R;

public class NewUserActivity extends AppCompatActivity {

    public static final String USER_ADDED = "new user";
    public static final String PLACE_ADDED = "new place";
    public static final String FRIEND_ADDED = "new friend";

    private EditText edtNewName;
    private EditText edtNewPlace;
    private EditText edtNewFriend;
    private Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        edtNewName = findViewById(R.id.edtNewName);
        edtNewPlace = findViewById(R.id.edtNewPlace);
        edtNewFriend = findViewById(R.id.edtNewFriend);

        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();

                if(TextUtils.isEmpty(edtNewName.getText()) || TextUtils.isEmpty(edtNewPlace.getText()) || TextUtils.isEmpty(edtNewFriend.getText())) {
                    setResult(RESULT_CANCELED, resultIntent);
                } else {
                    String userName = edtNewName.getText().toString();
                    String userPlace = edtNewPlace.getText().toString();
                    String userFriend = edtNewFriend.getText().toString();

                    resultIntent.putExtra(USER_ADDED, userName);
                    resultIntent.putExtra(PLACE_ADDED, userPlace);
                    resultIntent.putExtra(FRIEND_ADDED, userFriend);

                    setResult(RESULT_OK, resultIntent);

                    finish();
                }
            }
        });
    }
}