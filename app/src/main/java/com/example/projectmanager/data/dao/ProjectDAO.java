package com.example.projectmanager.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.projectmanager.data.models.Employee;
import com.example.projectmanager.data.models.EmployeeProject;
import com.example.projectmanager.data.models.Project;

import java.util.List;

@Dao
public interface ProjectDAO {

    @Query("SELECT * FROM Project")
    public LiveData<List<Project>> getAllProjects();

    @Query("SELECT * FROM Project WHERE id = :projectId")
    public LiveData<Project> getProject(Integer projectId);

    @Query("SELECT * FROM EmployeeProject WHERE EmployeeProject.project_id = :projectId")
    public LiveData<List<EmployeeProject>> getEmployeesForProject(Integer projectId);

}
