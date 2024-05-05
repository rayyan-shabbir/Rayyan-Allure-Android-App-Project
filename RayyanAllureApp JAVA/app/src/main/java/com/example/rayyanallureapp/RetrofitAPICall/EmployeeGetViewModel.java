// File: EmployeeGetViewModel.java
package com.example.rayyanallureapp.RetrofitAPICall;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class EmployeeGetViewModel extends AndroidViewModel {

    private EmployeeRepository employeeRepository;
    private LiveData<EmployeeGet> employeeResponseLiveData;

    public EmployeeGetViewModel(@NonNull Application application) {
        super(application);
        employeeRepository = new EmployeeRepository();
        employeeResponseLiveData = employeeRepository.getAllEmployeeData(0); // Corrected method name
    }

    public LiveData<EmployeeGet> getAllEmployeeLiveData() {
        return employeeResponseLiveData;
    }
}
