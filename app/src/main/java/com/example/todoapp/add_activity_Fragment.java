package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.todoapp.data.Repository;
import com.example.todoapp.data.Task;

import java.util.Date;


public class add_activity_Fragment extends Fragment {


    private EditText titleEditText;
    private EditText descEditText;
    private EditText due_Date, update_Date;
    RadioGroup radioGroup;
    private Repository repository;
    private Button addButton, cancelButton;
    private Long ID;

    public static final int PRIORITY_HIGH =1;
    public static final int PRIORITY_MEDIUM =2;
    public static final int PRIORITY_LOW= 3;

    public static add_activity_Fragment newInstance() {
        return new add_activity_Fragment();
    }

    public add_activity_Fragment(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView;
        rootView =  inflater.inflate(R.layout.fragment_add_activity_, container, false);
        titleEditText = (EditText)rootView.findViewById(R.id.title_add);
        descEditText = (EditText)rootView.findViewById(R.id.desc_add);
        due_Date = (EditText)rootView.findViewById(R.id.editTextDate);
        update_Date = (EditText)rootView.findViewById(R.id.notTextDate);
        radioGroup = (RadioGroup) rootView.findViewById(R.id.radio_group);
        addButton = (Button) rootView.findViewById((R.id.add_btn));

        cancelButton = (Button) rootView.findViewById(R.id.cancel_btn_add);

        repository = new Repository(getActivity().getApplication());


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String desc = descEditText.getText().toString();

                Task task = new Task(title, desc, new Date(), new Date(), 1);
                repository.addTask(task);

//                getActivity()
//                        .getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.main_container, todoFragment.newInstance())
//                        .commitNow();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = titleEditText.getText().toString();
                String desc = descEditText.getText().toString();
                String dateD = due_Date.getText().toString();
                String dateC = update_Date.getText().toString();
                

                int priority = getPriorityFromViews();
                Task task = new Task(title, desc, new Date(), new Date(), 1);
                repository.addTask(task);


                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_container, todoFragment.newInstance())
                        .addToBackStack(null)
                        .commit();


            }

            private int getPriorityFromViews() {
                int priority = 1;
                int checkID = ((RadioGroup) rootView.findViewById(R.id.radio_group)).getCheckedRadioButtonId();

                switch (checkID){
                    case R.id.priorityHigh:
                        priority = PRIORITY_HIGH;
                        break;
                    case R.id.priorityMed:
                        priority = PRIORITY_MEDIUM;
                        break;
                    case R.id.priorityLow:
                        priority = PRIORITY_LOW;
                        break;

                }
                return priority;
            }

            private void sePriorityInViews(int priority){
                switch (priority){
                    case PRIORITY_HIGH:
                        ((RadioGroup) rootView.findViewById(R.id.radio_group)).check(R.id.priorityHigh);
                        break;
                    case PRIORITY_MEDIUM:
                        ((RadioGroup) rootView.findViewById(R.id.radio_group)).check(R.id.priorityMed);
                        break;
                    case PRIORITY_LOW:
                        ((RadioGroup) rootView.findViewById(R.id.radio_group)).check(R.id.priorityLow);

                }

            }

        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_container, todoFragment.newInstance())
                        .commitNow();
            }
        });

        return rootView;
    }


}