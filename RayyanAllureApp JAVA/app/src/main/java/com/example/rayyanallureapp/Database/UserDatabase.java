package com.example.rayyanallureapp.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// Database
@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    private static UserDatabase userInstance;

    public abstract UserDao userDao();

    public static synchronized UserDatabase getDatabase(Context context) {
        if(userInstance == null) {
            userInstance = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, "user_database").fallbackToDestructiveMigration().build();

//            userInstance = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, "user_database")
//                    .fallbackToDestructiveMigration()
//                    .addMigrations(MIGRATION_1_2)  // Add this line if needed
//                    .build();

        }

        return userInstance;
    }
}


//package com.example.myapplication.Database;
//
//import android.content.Context;
//
//import androidx.annotation.NonNull;
//import androidx.room.Database;
//import androidx.room.Room;
//import androidx.room.RoomDatabase;
//import androidx.room.migration.Migration;
//import androidx.sqlite.db.SupportSQLiteDatabase;
//
//// Database
//@Database(entities = {User.class}, version = 2)
//public abstract class UserDatabase extends RoomDatabase {
//    private static UserDatabase userInstance;
//
//    public abstract UserDao userDao();
//
//    public static synchronized UserDatabase getDatabase(Context context) {
//        if (userInstance == null) {
//            userInstance = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, "user_database")
//                    .addMigrations(MIGRATION_1_2) // Add your migration strategy
//                    .build();
//        }
//
//        return userInstance;
//    }
//
//    // Your Migration logic
//    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            // Define your migration logic here
//            // For example, you can alter a table by executing SQL queries
//            database.execSQL("ALTER TABLE user_table ADD COLUMN new_column_name TEXT");
//        }
//    };
//}

