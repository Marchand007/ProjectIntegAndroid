package com.example.projectmanager.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.projectmanager.data.models.EmployeeProject;
import com.example.projectmanager.data.models.Project;

import java.util.List;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

@Dao
public interface EmployeeProjectDAO {

        @Query("SELECT EmployeeProject.* FROM EmployeeProject " +
                "JOIN Project on EmployeeProject.project_id = Project.id " +
                "WHERE EmployeeProject.employee_name = :employeeName AND EmployeeProject.is_active = 1 ORDER BY EmployeeProject.priority ASC")
                public LiveData<List<EmployeeProject>> getActiveProjectForEmployee(String employeeName);

        @Query("SELECT EmployeeProject.* FROM EmployeeProject " +
                "JOIN Project on EmployeeProject.project_id = Project.id " +
                "WHERE EmployeeProject.employee_name = :employeeName AND EmployeeProject.is_active = 0 ORDER BY EmployeeProject.priority ASC")
        public LiveData<List<EmployeeProject>> getCompletedProjectForEmployee(String employeeName);

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        public void addProject(EmployeeProject employeeProject);

        @Delete
        public void deleteProject(EmployeeProject employeeProject);

        @Update
        public void updateEmployeeProject(EmployeeProject employeeProject);
}
