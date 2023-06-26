package com.example.projectmanager.data.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Employee implements Serializable {
@PrimaryKey
@NonNull
private String name;

@Nullable
@ColumnInfo(name = "manager_name")

private String managerName;

private String role;

    public Employee(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @Nullable
    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(@Nullable String managerName) {
        this.managerName = managerName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
