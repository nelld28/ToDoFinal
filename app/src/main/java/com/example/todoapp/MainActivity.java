package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    //private Repository repository;
    private List<Task> taskList;
    private TaskAdapter adapter;
    private FloatingActionButton fab;
    private MainViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.task_list);
        fab = findViewById(R.id.floatingActionButton);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        //repository = Repository.getRepository(this.getApplication());
        //taskList = repository.getAllTask();
        adapter = new TaskAdapter();
        //adapter.setDate(taskList);
        recyclerView.setAdapter(adapter);

        viewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                if(tasks != null)
                    adapter.setData(taskList);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(intent);
            }
        });

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        taskList = repository.getAllTask();
//        adapter.setDate(taskList);
//    }
}