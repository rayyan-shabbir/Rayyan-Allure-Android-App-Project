package com.example.rayyanallureapp.Database;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;

import com.example.rayyanallureapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import java.util.UUID;
import android.app.AlertDialog;
import android.content.DialogInterface;


public class DatabaseActivity extends AppCompatActivity implements UserListAdapter.OnDeleteClickListener {

    private static final int NEW_USER_ACTIVITY_REQUEST_CODE = 1;
    public static final int UPDATE_USER_ACTIVITY_REQUEST_CODE = 2;
    private UserViewModel userViewModel;
    private UserListAdapter userListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        // Setting Recycler View
        RecyclerView RV = findViewById(R.id.RV);
        // 1 this for layout inflater, 2nd this as onDelete listener
        userListAdapter = new UserListAdapter(this, this);
        RV.setAdapter(userListAdapter);
        RV.setLayoutManager(new LinearLayoutManager(this));

        // For Inserting new User
        FloatingActionButton btnGoUserForm = findViewById(R.id.btnGoUserForm);
        btnGoUserForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DatabaseActivity.this, NewUserActivity.class);
                startActivityForResult(intent, NEW_USER_ACTIVITY_REQUEST_CODE);
            }
        });

        // View Model
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        // Fetching and Displaying users
        userViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                userListAdapter.setUsers(users);
            }
        });

        // Deleting all users
        Button btnDeleteAll = findViewById(R.id.btnDeleteAll);

        btnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteAllConfirmationDialog();
            }
        });
    }

    private void showDeleteAllConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All Users");
        builder.setMessage("Are you sure you want to delete all users?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User clicked Yes, delete all users
                userViewModel.deleteAllUsers();
                Toast.makeText(DatabaseActivity.this, "All users deleted", Toast.LENGTH_LONG).show();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User clicked No, do nothing
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Inserting New User
        if (requestCode == NEW_USER_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            // Insert a User
            final String user_id = UUID.randomUUID().toString();
            User user = new User(user_id, data.getStringExtra(NewUserActivity.USER_ADDED), data.getStringExtra(NewUserActivity.PLACE_ADDED), data.getStringExtra(NewUserActivity.FRIEND_ADDED));
            userViewModel.insert(user);

            Toast.makeText(getApplicationContext(), "User is added successfully!", Toast.LENGTH_LONG).show();

            // Updating the existing User
        } else if (requestCode == UPDATE_USER_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            // Update a User
            User user = new User(
                    data.getStringExtra(DatabaseEditActivity.USER_ID),
                    data.getStringExtra(DatabaseEditActivity.UPDATED_NAME),
                    data.getStringExtra(DatabaseEditActivity.UPDATED_PLACE),
                    data.getStringExtra(DatabaseEditActivity.UPDATED_FRIEND));
            userViewModel.update(user);

            Toast.makeText(getApplicationContext(), "User Info successfully updated.", Toast.LENGTH_LONG).show();

            // Response Code not OK.
        } else {
            Toast.makeText(getApplicationContext(), "Sorry! operation cannot be performed.", Toast.LENGTH_LONG).show();
        }
    }

    // Implementing the onDeleteClickListener function from adapter->onDeleteClickListener class
    @Override
    public void OnDeleteClickListener(User myUser) {
        // Delete operation
        userViewModel.delete(myUser);
    }
}