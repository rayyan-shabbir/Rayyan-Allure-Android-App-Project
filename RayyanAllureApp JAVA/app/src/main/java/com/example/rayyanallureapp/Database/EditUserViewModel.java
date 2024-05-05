package com.example.rayyanallureapp.Database;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

public class EditUserViewModel extends AndroidViewModel {
    private UserDatabase dbInstance;
    private UserDao userDao;

    String userId;

    public EditUserViewModel(@NonNull Application application) {
        super(application);
        dbInstance = UserDatabase.getDatabase(application);
        userDao = dbInstance.userDao();
    }

    public LiveData<User> getUser(String userId) {
        return userDao.getUser(userId);
    }
}