package com.example.projectmanager.data.models;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;

import java.io.Serializable;

@Entity(
        primaryKeys = {
                "employee_eid", "project_id"
        },
        foreignKeys = {
                @ForeignKey(
                        entity = Employee.class,
                        parentColumns = "eid",
                        childColumns = "employee_eid",
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

    @ColumnInfo(name = "employee_eid")
    @NonNull
    private Integer employeeEid;

    @ColumnInfo(name = "project_id")
    @NonNull
    private Integer projectId;

    @ColumnInfo(name = "is_active")
    @NonNull
    private Boolean isActive;

    @NonNull
    private Integer priority;

    @ColumnInfo(name = "project_name")
    private String projectName;

    @ColumnInfo(name = "employee_name")
    @NonNull
    private String employeeName;

    public EmployeeProject(@NonNull Integer employeeEid, @NonNull Integer projectId, @NonNull Boolean isActive, @NonNull Integer priority, @NonNull String projectName, @NonNull String employeeName) {
        this.employeeEid = employeeEid;
        this.projectId = projectId;
        this.projectName = projectName;
        this.isActive = isActive;
        this.priority = priority;
        this.employeeName = employeeName;
    }

    @NonNull
    public Integer getEmployeeEid() {
        return employeeEid;
    }

    public void setEmployeeEid(@NonNull Integer employeeEid) {
        this.employeeEid = employeeEid;
    }

    @NonNull
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(@NonNull Integer projectId) {
        this.projectId = projectId;
    }

    @NonNull
    public Boolean isActive() {
        return isActive;
    }

    public void setActive(@NonNull Boolean active) {
        isActive = active;
    }

    @NonNull
    public Integer getPriority() {
        return priority;
    }

    public void setPriority(@NonNull Integer priority) {
        this.priority = priority;
    }


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @NonNull
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(@NonNull String employeeName) {
        this.employeeName = employeeName;
    }
}
