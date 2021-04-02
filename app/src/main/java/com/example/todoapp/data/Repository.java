package com.example.todoapp.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {

    public static Repository INSTANCE;
    AppDatabase db;
    TodoDao dao;

    public Repository(Application application) {
        db = AppDatabase.getDatabase(application);
        dao = db.todoDao();
    }

    public static Repository getRepository(Application application){
        if(INSTANCE == null){
            INSTANCE = new Repository(application);
        }
        return INSTANCE;
    }

    public LiveData<List<Task>> getAllTasks(){
        return dao.getAllTasks();
    }

//
//    public void deleteAll(){
//        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                dao.deleteAll();
//            }
//        });
//
//    }

    public void update(Task task){

        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.update(task);
            }
        });


    }

    public void addTask(final Task task){

        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.insert(task);
            }
        });

    }


    public void delete(Task task) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.delete(task);
            }
        });
    }


    public void deleteAll() {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.deleteAll();
            }
        });
    }
}
