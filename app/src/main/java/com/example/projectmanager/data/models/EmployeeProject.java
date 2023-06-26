package com.example.projectmanager.data.models;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;

import com.example.projectmanager.data.models.Employee;
import com.example.projectmanager.data.models.Project;

import java.io.Serializable;

@Entity(
        primaryKeys = {
                "employee_name", "project_id"
        },
        foreignKeys = {
                @ForeignKey(
                        entity = Employee.class,
                        parentColumns = "name",
                        childColumns = "employee_name",
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = Project.class,
                        parentColumns = "id",
                        childColumns = "project_id",
                        onDelete = ForeignKey.CASCADE
                )
        }
)
public class EmployeeProject implements Serializable {

@ColumnInfo(name = "employee_name")
@NonNull
    private String employeeName;

@ColumnInfo(name = "project_id")
@NonNull
    private Integer projectId;

@ColumnInfo(name = "project_name")
private String projectName;
@ColumnInfo(name = "is_active")
    private boolean isActive;

    private int priority;

    public EmployeeProject(@NonNull  String employeeName, @NonNull Integer projectId, @NonNull String projectName) {
        this.employeeName = employeeName;
        this.projectId = projectId;
        this.projectName = projectName;
        this.isActive = true;
        this.priority = 2;
    }

    @NonNull
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(@NonNull String employeeName) {
        this.employeeName = employeeName;
    }

    @NonNull
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(@NonNull Integer projectId) {
        this.projectId = projectId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
