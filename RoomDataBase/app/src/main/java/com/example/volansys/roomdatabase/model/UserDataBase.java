package com.example.volansys.roomdatabase.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

@Database(entities = {User.class},version = 1)
public abstract class UserDataBase extends RoomDatabase {
    private static UserDataBase INSTANCE;
    public abstract UserDao userDao();

}
