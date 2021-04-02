package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todoapp.data.Repository;
import com.example.todoapp.data.Task;

import java.util.Date;


public class AddTaskFragment extends Fragment {

    View rootView;
    private EditText titleEditText;
    private EditText descEditText;
    private Repository repository;
    private Button submitButton;

    long ID;
    public AddTaskFragment() {
        // Required empty public constructor
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView =  inflater.inflate(R.layout.fragment_add_task, container, false);
        titleEditText = rootView.findViewById(R.id.title_et);
        descEditText = rootView.findViewById(R.id.descripttion_tv);
        submitButton = rootView.findViewById((R.id.submit_btn));

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubmitTodo();
            }
        });
        return rootView;
    }

    public void SubmitTodo() {
        Task task = new Task();
        task.setTitle(titleEditText.getText().toString());
        task.setDescription(descEditText.getText().toString());

        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        if (ID != -1){
            task.setId(ID);
            viewModel.update(task);
        }
        else {
            viewModel.insert(task);
        }

        Toast.makeText(getActivity(), "Todo submitted", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }


//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        titleEditText = titleEditText.findViewById(R.id.title_et);
////        descEditText = descEditText.findViewById(R.id.descripttion_tv);
////        submitButton = submitButton.findViewById(R.id.submit_btn);
////        //repository = Repository.getRepository(this.);
////
////        submitButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                String title = titleEditText.getText().toString();
////                String desc = descEditText.getText().toString();
////
////                Task task = new Task(title, desc,  new Date(), new Date(), 1);
////                repository.addTask(task);
////
////            }
////        });
//    }





}