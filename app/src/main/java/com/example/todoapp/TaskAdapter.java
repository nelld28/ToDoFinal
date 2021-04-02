package com.example.todoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.data.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {


    //callback
    private final TaskCallback callback;


    public Task getTodoAt;
    private List<Task> taskList;

    public TaskAdapter(todoFragment context, TaskCallback callback) {
        mInflater = LayoutInflater.from(context.getContext());
        this.callback = callback;

    }

    public void setData(List<Task> data){
        taskList = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.onBind(task);


    }

    @Override
    public int getItemCount() {
        if(taskList != null)
            return taskList.size();
        return 0;
    }

    public Task getTodoAt(int position) {
        return taskList.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder{


        private TextView titleTextView;
        private  TextView descTextView;
        private TextView dueDate;
        private RadioGroup priority;
        private ImageButton update_img_btn;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_task, parent, false));

            titleTextView = itemView.findViewById(R.id.title_tv);
            descTextView = itemView.findViewById(R.id.descripttion_tv);
            dueDate = itemView.findViewById(R.id.date_due);
            update_img_btn = itemView.findViewById(R.id.imageButton);

            update_img_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    callback.update(taskList.get(position));
                }
            });

        }

        public void onBind(Task task) {
            titleTextView.setText(task.getTitle());
            descTextView.setText(task.getDescription());

            //dueDate.setText(task.getCreatedDate());
        }


    }//extends

    private final LayoutInflater mInflater;

    public interface TaskCallback{
        void update(Task task);
    }
}
