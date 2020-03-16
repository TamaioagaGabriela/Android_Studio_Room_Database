package com.example.tema2;

import android.content.Context;
import android.os.AsyncTask;

public class UserRepository {
    private AppDatabase appDatabase;

    public UserRepository(Context context) {
        appDatabase = ApplicationController.getAppDatabase();
    }
    public void insertTask(final User user,
                           final OnUserRepositoryActionListener listener) {
        new InsertTask(listener).execute(user);
    }
    public void deleteTask(final User user,
                           final OnUserRepositoryActionListener listener) {
        new DeleteTask(listener).execute(user);
    }
    public User getUserByName(String firstName){
        return appDatabase.userDao().findByName(firstName);
    }




}
