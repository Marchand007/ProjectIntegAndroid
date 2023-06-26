package com.example.projectmanager.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.projectmanager.data.ProjectManagerDatabase;
import com.example.projectmanager.data.dao.EmployeeProjectDAO;
import com.example.projectmanager.data.models.EmployeeProject;
import com.example.projectmanager.data.models.Project;

import java.util.List;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class EmployeeProjectRepository {


    private final EmployeeProjectDAO employeePRojectsDAO;


    @Inject
    public EmployeeProjectRepository(ProjectManagerDatabase database) { employeePRojectsDAO = database.getEmployeeProjectDao(); }


    public LiveData<List<EmployeeProject>> getActiveProjectForEmployee(String employeeName) {
        return employeePRojectsDAO.getActiveProjectForEmployee(employeeName);
    }

    public LiveData<List<EmployeeProject>> getCompletedProjectForEmployee(String employeeName) {
        return employeePRojectsDAO.getCompletedProjectForEmployee(employeeName);
    }

    public void addProject(EmployeeProject employeeProject) {
        Executors.newSingleThreadExecutor().execute(() -> {
            employeePRojectsDAO.addProject(employeeProject);
        });
    }

    public void deleteProject(EmployeeProject employeeProject) {
        Executors.newSingleThreadExecutor().execute(() -> {
            employeePRojectsDAO.deleteProject(employeeProject);
        });
    }
    public void updateEmployeeProject(EmployeeProject employeeProject) {
        Executors.newSingleThreadExecutor().execute(() -> {
            employeePRojectsDAO.updateEmployeeProject(employeeProject);
        });
    }

}
