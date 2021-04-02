package com.example.todoapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Task task);

    @Query("delete from todos")
    void deleteAll();

    @Delete
    void delete(Task task);

    @Query("select * from todos where id =:id")
    Task getTodoByID(Long id);

    @Update
    void update(Task task);

    @Query("select * from todos order by priority")
    LiveData<List<Task>> getAllTasks();










}
