package com.example.todoapp.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {

    private static Repository INSTANCE;
    AppDatabase db;
    TodoDao dao;

    private Repository(Application application) {
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

    public void deleteAll(){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.deleteAll();
            }
        });

    }

    public void update(Task task){

        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.update(task);
            }
        });


    }

    public void addTask(Task task){

        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.insert(task);
            }
        });


    }


    public void insert(Task task) {
        new insertTodoAysyncTask(dao).execute(task);
    }

    private class insertTodoAysyncTask extends AsyncTask<Task, Void, Void> {
        private TodoDao mTodoDao;
        private insertTodoAysyncTask(TodoDao dao) {
            mTodoDao = dao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            mTodoDao.insert(tasks[0]);
            return null;
        }
    }
}
