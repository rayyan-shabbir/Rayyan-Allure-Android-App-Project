package com.example.rayyanallureapp.RetrofitAPICall;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeRepository {
    private MutableLiveData<EmployeeGet> employeeGetLiveData;
    private MutableLiveData<EmployeePost> getEmployeePostLiveData;
    private ApiService apiService;

    public EmployeeRepository() {
        this.apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
    }

    public LiveData<EmployeeGet> getAllEmployeeData(int page) {
        employeeGetLiveData = new MutableLiveData<>();
        apiService.getAllEmployeeData(page).enqueue(new Callback<EmployeeGet>() {
            @Override
            public void onResponse(Call<EmployeeGet> call, Response<EmployeeGet> response) {
                if (response.isSuccessful()) {
                    employeeGetLiveData.setValue(response.body());
                } else {
                    // Log the error
                    Log.e("ApiService", "Get Error data: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<EmployeeGet> call, Throwable t) {
                // Handle failure here (e.g., log, show error message)
                // Log the error
                Log.e("ApiService", "Get Error response: " + t.getMessage());

                employeeGetLiveData.setValue(null);
            }
        });

        return employeeGetLiveData;
    }

    public LiveData<EmployeePost> createNewUser(String name, String job) {
        getEmployeePostLiveData = new MutableLiveData<>();
        apiService.createNewUser(name, job).enqueue(new Callback<EmployeePost>() {
            @Override
            public void onResponse(Call<EmployeePost> call, Response<EmployeePost> response) {
                if (response.isSuccessful()) {
                    getEmployeePostLiveData.setValue(response.body());
                } else {
                    // Log the error
                    Log.e("ApiService", "Post Error data: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<EmployeePost> call, Throwable t) {
                // Handle failure here (e.g., log, show error message)
                // Log the error
                Log.e("ApiService", "POST Error response: " + t.getMessage());

                getEmployeePostLiveData.setValue(null);
            }
        });

        return getEmployeePostLiveData;
    }

    public LiveData<EmployeePost> getEmployeePostLiveData() {
        return getEmployeePostLiveData;
    }
}
