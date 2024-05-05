package com.example.rayyanallureapp.RetrofitAPICall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.rayyanallureapp.R;

public class RetrofitGetActivity extends AppCompatActivity {

    private EmployeeGetViewModel employeeViewModel;
    private EmployeeListAdapter employeeListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        RecyclerView recyclerView = findViewById(R.id.RVretro);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        employeeListAdapter = new EmployeeListAdapter();
        recyclerView.setAdapter(employeeListAdapter);

        employeeViewModel = new ViewModelProvider(this).get(EmployeeGetViewModel.class);
        employeeViewModel.getAllEmployeeLiveData().observe(this, employeeList -> {
            if (employeeList != null) {
                employeeListAdapter.setEmployeeList(employeeList.getData());
            }
        });
    }
}
