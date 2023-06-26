package com.example.projectmanager.data.repository;

import androidx.lifecycle.LiveData;

import com.example.projectmanager.data.ProjectManagerDatabase;
import com.example.projectmanager.data.dao.ProjectDAO;
import com.example.projectmanager.data.models.Employee;
import com.example.projectmanager.data.models.EmployeeProject;
import com.example.projectmanager.data.models.Project;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ProjectRepository {

    private final ProjectDAO projectDAO;

    @Inject
    public ProjectRepository(ProjectManagerDatabase database) { projectDAO = database.getProjectDao(); }

    public LiveData<List<Project>> getAllProjects() {
        return projectDAO.getAllProjects();
    }

    public LiveData<Project> getProject(Integer projectId) {
        return projectDAO.getProject(projectId);
    }
    public LiveData<List<EmployeeProject>> getEmployeesForProject(Integer projectId) {
        return projectDAO.getEmployeesForProject(projectId);
    }
}
