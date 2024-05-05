package com.example.rayyanallureapp.RetrofitAPICall;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

public class EmployeePostViewModel extends AndroidViewModel {
    private EmployeeRepository employeeRepository;
    private MutableLiveData<EmployeePost> employeePostLiveData;

    public EmployeePostViewModel(@NonNull Application application) {
        super(application);
        this.employeeRepository = new EmployeeRepository();
        this.employeePostLiveData = new MutableLiveData<>(); // Initialize it here
    }

    public LiveData<EmployeePost> getEmployeePostLiveData() {
        return employeePostLiveData;
    }

    public void createNewUser(String name, String job) {
        // Observe the LiveData returned by the repository
        employeeRepository.createNewUser(name, job).observeForever(new Observer<EmployeePost>() {
            @Override
            public void onChanged(EmployeePost employeePost) {
                // Update the ViewModel's MutableLiveData with the new data
                employeePostLiveData.setValue(employeePost);
            }
        });
    }
}
