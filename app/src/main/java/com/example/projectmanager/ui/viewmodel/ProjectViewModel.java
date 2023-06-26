package com.example.projectmanager.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.projectmanager.data.models.Employee;
import com.example.projectmanager.data.models.EmployeeProject;
import com.example.projectmanager.data.models.Project;
import com.example.projectmanager.data.repository.ProjectRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ProjectViewModel extends ViewModel {

    private ProjectRepository repository;

    @Inject
    public ProjectViewModel(ProjectRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<Project>> getAllProject() {

        return repository.getAllProjects();
    }

    public LiveData<Project> getProject(Integer projectId) {
        return repository.getProject(projectId);
    }
    public LiveData<List<EmployeeProject>> getEmployeesForProject(Integer projectId) {
        return repository.getEmployeesForProject(projectId);
    }

}
