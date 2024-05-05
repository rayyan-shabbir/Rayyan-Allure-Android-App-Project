package com.example.rayyanallureapp.Database;

import androidx.lifecycle.Observer;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

// User Schema
@Entity(tableName = "users")
public class User {
    // Columns
    @PrimaryKey
    @NonNull
    private String id;
    @NonNull
    @ColumnInfo(name = "user")
    private String name;
    private String favPlace;
    private String friend;

    // Observer to observe this table (What changes comes from editing the user, will be observed here)
    public static void observe(DatabaseEditActivity databaseEditActivity, Observer<User> userObserver) {}

    // Constructor
    public User(String id, String name, String favPlace, String friend) {
        this.id = id;
        this.name = name;
        this.favPlace = favPlace;
        this.friend = friend;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return this.name;
    }

    public String getFavPlace() {
        return this.favPlace;
    }

    public String getFriend() {
        return this.friend;
    }
}