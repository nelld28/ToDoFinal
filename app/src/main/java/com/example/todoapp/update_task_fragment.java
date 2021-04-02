package com.example.todoapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.todoapp.data.Repository;
import com.example.todoapp.data.Task;

import java.util.Date;


public class update_task_fragment extends Fragment {

    private EditText titleEditText;
    private EditText descEditText;
    private EditText dueDate, updateDate;
    RadioGroup radioGroup;
    private Repository repository;
    private Button upDateButton, cancelButton;
    private Long ID;

    private Task task;

    public static final int PRIORITY_HIGH =1;
    public static final int PRIORITY_MEDIUM =2;
    public static final int PRIORITY_LOW= 3;


    public update_task_fragment() {
        // Required empty public constructor
    }

    public static update_task_fragment newInstance() {
        return new update_task_fragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=this.getArguments();
        assert bundle != null;
        task = (Task) bundle.getSerializable("todo");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView;
        rootView =  inflater.inflate(R.layout.fragment_update_task_fragment,
                container, false);
        titleEditText = (EditText)rootView.findViewById(R.id.title_update);
        descEditText = (EditText)rootView.findViewById(R.id.descripttion_update);

        dueDate = (EditText)rootView.findViewById(R.id.editTextDate_update);
        updateDate = (EditText)rootView.findViewById(R.id.notTextDate);

        radioGroup = (RadioGroup) rootView.findViewById(R.id.radio_group_update);

        upDateButton = (Button) rootView.findViewById(R.id.update_btn);
        cancelButton = (Button) rootView.findViewById(R.id.cancel_btn_update);

        repository =new Repository(getActivity().getApplication());

//        titleEditText.setText(Editable.Factory.getInstance().newEditable(task.getTitle()));
//        descEditText.setText(Editable.Factory.getInstance().newEditable(task.getDescription()));

        upDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String desc = descEditText.getText().toString();
//                String dateD = dueDate.getText().toString();
//                String dateC = updateDate.getText().toString();

                int priority = getPriorityFromViews();

                Task task = new Task(title, desc, new Date(), new Date(), priority);

                titleEditText.setText(Editable.Factory.getInstance().newEditable(task.getTitle()));
                descEditText.setText(Editable.Factory.getInstance().newEditable(task.getDescription()));

                task.setTitle(title);
                task.setDescription(desc);
                //task.setCreatedDate(dateD);
                task.setPriority(priority);


                repository.update(task);

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_container, todoFragment.newInstance())
                        .commitNow();


            }
            private int getPriorityFromViews(){
                int priority = 1;
                int checkID = ((RadioGroup)
                        rootView.findViewById(R.id.radio_group_update))
                        .getCheckedRadioButtonId();

                switch (checkID){
                    case R.id.priorityHigh_update:
                        priority = PRIORITY_HIGH;
                        break;
                    case R.id.priorityMed_update:
                        priority = PRIORITY_MEDIUM;
                        break;
                    case R.id.priorityLow_update:
                        priority = PRIORITY_LOW;
                        break;

                }
                return priority;
            }

            private void setPriorityViews(int priority){
                switch (priority){
                    case PRIORITY_HIGH:
                        ((RadioGroup) rootView.findViewById(R.id.radio_group_update)).check(R.id.priorityHigh_update);
                        break;
                    case PRIORITY_MEDIUM:
                        ((RadioGroup) rootView.findViewById(R.id.radio_group_update)).check(R.id.priorityMed_update);
                        break;
                    case PRIORITY_LOW:
                        ((RadioGroup) rootView.findViewById(R.id.radio_group_update)).check(R.id.priorityLow_update);
                }
                }
        });//update end

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