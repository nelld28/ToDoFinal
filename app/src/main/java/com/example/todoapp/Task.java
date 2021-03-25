package com.example.todoapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

import java.util.Date;

@Entity(tableName = "todo")
public class Task {
    private Long id;
    private String title;
    private String description;
    @ColumnInfo(name = "created_date")
    private Date createdDate;
    @ColumnInfo(name = "updated_date")
    private Date updatedDate;
    private int priority;


    public Task(String title, String description, Date createdDate, Date updatedDate, int priority) {
        this.title = title;
        this.description = description;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.priority = priority;
    }

    @Ignore
    public Task(Long id, String title, String description, Date createdDate, int priority, Date updatedDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdDate = createdDate;
        this.priority = priority;
        this.updatedDate = updatedDate;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }


}
