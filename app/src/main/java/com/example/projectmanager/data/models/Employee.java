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
    private Integer eid;

    @NonNull
    private String name;
    @NonNull
    private String role;

    @ColumnInfo(name = "manager_name")
    private String managerName;

    public Employee(@NonNull Integer eid, @NonNull String name, @NonNull String role, String managerName) {
        this.eid = eid;
        this.name = name;
        this.role = role;
        this.managerName = managerName;
    }

    @NonNull
    public String getName() { return name; }

    public void setName(@NonNull String name) { this.name = name; }

    @NonNull
    public String getRole() { return role; }

    public void setRole(@NonNull String role) { this.role = role; }

    @NonNull
    public Integer getEid() { return eid; }

    public void setEid(@NonNull Integer eid) { this.eid = eid; }

    public String getManagerName() { return managerName; }

    public void setManagerName(String managerName) { this.managerName = managerName; }
}
