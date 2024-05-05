package com.example.rayyanallureapp.Database;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.rayyanallureapp.R;

public class DatabaseEditActivity extends AppCompatActivity {

    public static final String USER_ID = "user_id";
    static final String UPDATED_NAME = "user_name";
    static final String UPDATED_PLACE = "user_place";
    static final String UPDATED_FRIEND = "user_friend";
    private EditText edtNewName;
    private EditText edtNewPlace;
    private EditText edtNewFriend;
    private Bundle bundle;
    private String userId;
    private LiveData<User> user;

    EditUserViewModel userModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_edit);

        edtNewName = findViewById(R.id.edtNewName);
        edtNewPlace = findViewById(R.id.edtNewPlace);
        edtNewFriend = findViewById(R.id.edtNewFriend);

        bundle = getIntent().getExtras();

        if (bundle != null) {
            userId = bundle.getString("user_id");
        }

        userModel = new ViewModelProvider(this).get(EditUserViewModel.class);

        user = userModel.getUser(userId);
        user.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if(user != null) {
                    edtNewName.setText(user.getName());
                    edtNewPlace.setText(user.getFavPlace());
                    edtNewFriend.setText(user.getFriend());
                }

            }
        });
    }

    public void updateUser(View view) {
        Intent resultIntent = new Intent();

        String updatedName = edtNewName.getText().toString();
        String updatedPlace = edtNewPlace.getText().toString();
        String updatedFriend = edtNewFriend.getText().toString();

        resultIntent.putExtra(USER_ID, userId);
        resultIntent.putExtra(UPDATED_NAME, updatedName);
        resultIntent.putExtra(UPDATED_PLACE, updatedPlace);
        resultIntent.putExtra(UPDATED_FRIEND, updatedFriend);

        setResult(RESULT_OK, resultIntent);
        finish();
    }

    public void cancelUpdate(View view) {
        finish();
    }
}
