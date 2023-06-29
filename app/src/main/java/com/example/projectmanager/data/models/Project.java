package com.example.projectmanager.data.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

// FACTORY DESIGN PATTERN POSSIBLE
@Entity
public class Project implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Integer id;
    @NonNull
    private String name;
    private String client;
    @ColumnInfo(name = "deadline_date")
    private String deadlineDate;

    public Project(@NonNull String name, String deadlineDate) {
        this.name = name;
        this.deadlineDate = deadlineDate;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(String deadlineDate) {
        this.deadlineDate = deadlineDate;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
