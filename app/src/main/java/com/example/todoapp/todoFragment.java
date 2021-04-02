package com.example.todoapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.todoapp.data.Repository;
import com.example.todoapp.data.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class todoFragment extends Fragment {

    private static final String TAG = todoFragment.class.getSimpleName();
    View rootView;

    public static todoFragment newInstance() {
        return new todoFragment();
    }

    private TaskAdapter adapter;

    private FloatingActionButton fab;

    MainViewModel viewModel;

    RecyclerView recyclerView;

    private ImageButton updateBtn;

    Repository repository;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        //rootview
        rootView = inflater.inflate(R.layout.fragment_todo, container, false);

        //recyclerview
        recyclerView = rootView.findViewById(R.id.main_recyclerView);

        //adapter
        this.adapter = new TaskAdapter(this, new TaskAdapter.TaskCallback(){

            @Override
            public void update(Task task) {
                ((MainActivity)getActivity()).moveToUpdate(task);
            }

        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //update button
        updateBtn = (ImageButton) rootView.findViewById(R.id.imageButton);

        //viewModel
        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        //floating action button
        fab = rootView.findViewById(R.id.fab_btn);

        /*on touch activators*/
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                List<Task> taskList = viewModel.getAllTasks().getValue();
                Task task = adapter.getTodoAt(viewHolder.getAdapterPosition());
                viewModel.deleteByID(task);

            }

        }).attachToRecyclerView(recyclerView);



        //return
        return rootView;
    }//end of onCreatView

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.mainz_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_deleteAll: {
                repository.deleteAll();

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_container, todoFragment.newInstance())
                        .commitNow();

                return true;

            }

            case R.id.item_help:
                Toast.makeText(getContext(),"Asked for Help", Toast.LENGTH_SHORT).show();
            default:
                return super.onOptionsItemSelected(item);

        }

    }//end of menu options


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);


        viewModel.getAllTasks().observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable final List<Task> tasks) {
                if (tasks != null) {
                    adapter.setData(tasks);
                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.todo_container, add_activity_Fragment.newInstance())
                        .commitNow();

            }
        });

//        updateBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getActivity().getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.todo_container, update_task_fragment.newInstance())
//                        .commit();
//            }
//        });


    }

}