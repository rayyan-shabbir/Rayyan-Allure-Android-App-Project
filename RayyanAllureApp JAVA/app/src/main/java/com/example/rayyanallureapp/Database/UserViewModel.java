package com.example.rayyanallureapp.Database;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import android.os.AsyncTask;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private UserDatabase userDB;
    private UserDao userDao;
    private LiveData<List<User>> allUsers;

    String userId;

    public UserViewModel(Application application) {
        super(application);

        userDB = UserDatabase.getDatabase(application);
        userDao = userDB.userDao();
        allUsers = userDao.getAllUsers();
    }

    public void insert(User user) {
        // Inserting via non UI thread (in background)
        new InsertAsyncTask(userDao).execute(user);
    }

    LiveData<List<User>> getAllUsers() {
        return this.allUsers;
    }

    void deleteAllUsers() {
        // Updating via non UI thread (in background)
        new DeleteAllUsersAsyncTask(userDao).execute();
    }

    public void update(User user) {
        // Updating via non UI thread (in background)
        new UpdateAsyncTask(userDao).execute(user);
    }

    public void delete(User user) {
        // Delete via non UI thread (in background)
        new DeleteAsyncTask(userDao).execute(user);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    private class OperationsAsyncTask extends AsyncTask<User, Void, Void> {

        UserDao mAsyncTaskDao;

        OperationsAsyncTask(UserDao dao) {
            this.mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(User... users) {
            return null;
        }
    }

    private class InsertAsyncTask extends OperationsAsyncTask {

        InsertAsyncTask(UserDao mUserDao) {
            super(mUserDao);
        }

        @Override
        protected Void doInBackground(User... users) {
            mAsyncTaskDao.insert(users[0]);
            return null;
        }
    }

    private class UpdateAsyncTask extends OperationsAsyncTask {

        UpdateAsyncTask(UserDao userDao) {
            super(userDao);
        }

        @Override
        protected Void doInBackground(User... users) {
            mAsyncTaskDao.update(users[0]);
            return null;
        }
    }

    private class DeleteAsyncTask extends OperationsAsyncTask {

        public DeleteAsyncTask(UserDao userDao) {
            super(userDao);
        }

        @Override
        protected Void doInBackground(User... users) {
            mAsyncTaskDao.delete(users[0]);
            return null;
        }
    }

    private class DeleteAllUsersAsyncTask extends OperationsAsyncTask {

        public DeleteAllUsersAsyncTask(UserDao userDao) {
            super(userDao);
        }

        @Override
        protected Void doInBackground(User... users) {
            mAsyncTaskDao.deleteAllUsers();
            return null;
        }
    }
}