package com.example.todoapp;

import android.app.Application;

public class Repository {
    private static Repository INSTANCE;
    AppDatabase db;
    TodoDao dao;

    private Repository(Application application){
        db = AppDatabase.getDatabase(application);
        dao = db.todoDao();
    }
}
