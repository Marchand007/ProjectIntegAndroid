package com.example.projectmanager.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.projectmanager.data.models.Employee;
import com.example.projectmanager.data.models.Project;

import java.util.List;

@Dao
public interface EmployeeDAO {


    @Query("SELECT * from Employee ORDER BY name ASC")
    public LiveData<List<Employee>> getAllEmployee();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void addEmployee(Employee employee);

    @Delete
    public void deleteEmployee(Employee employee);



    @Query("SELECT * FROM Project WHERE Project.name = :nameEmployee")
    public LiveData<List<Project>> getProjectsForAEmployee(String nameEmployee);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void addProjectToAEmployee(Employee employee, Project project);

    @Delete
    public void deleteLocation(Project project);
}
