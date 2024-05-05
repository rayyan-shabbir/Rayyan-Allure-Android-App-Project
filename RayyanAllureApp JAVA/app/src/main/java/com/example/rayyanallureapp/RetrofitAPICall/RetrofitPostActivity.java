package com.example.rayyanallureapp.RetrofitAPICall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rayyanallureapp.R;

public class RetrofitPostActivity extends AppCompatActivity {

    private EmployeePostViewModel employeePostViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_post);

        EditText edtName = findViewById(R.id.edtName);
        EditText edtJob = findViewById(R.id.edtJob);

        Button btnSend = findViewById(R.id.btnSend);

        employeePostViewModel = new ViewModelProvider(this).get(EmployeePostViewModel.class);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String job = edtJob.getText().toString();

                // Update the LiveData with the new user data
                employeePostViewModel.createNewUser(name, job);
            }
        });

// Observe the LiveData for the response
        employeePostViewModel.getEmployeePostLiveData().observe(RetrofitPostActivity.this, employeePost -> {
            if (employeePost != null) {
                String userName = employeePost.getName();
                String userJob = employeePost.getJob();
                String createdAt = employeePost.getCreatedAt();

                TextView txtName = findViewById(R.id.txtN);
                TextView txtJob = findViewById(R.id.txtJ);
                TextView txtCreated = findViewById(R.id.txtC);

                txtName.setText(userName);
                txtJob.setText(userJob);
                txtCreated.setText(createdAt);
            } else {
                Toast.makeText(getApplicationContext(), "Unsuccessful request", Toast.LENGTH_LONG).show();
            }
        });
    }
}
