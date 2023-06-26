package com.example.projectmanager.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.projectmanager.data.models.EmployeeProject;
import com.example.projectmanager.data.repository.EmployeeProjectRepository;
import com.example.projectmanager.data.models.Project;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class EmployeeProjectViewModel extends ViewModel {

    private EmployeeProjectRepository repository;
    private MutableLiveData<String> listViewOption;

    @Inject
    public EmployeeProjectViewModel(EmployeeProjectRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<EmployeeProject>> getActiveProjectForEmployee(String employeeName) {
        return repository.getActiveProjectForEmployee(employeeName);
    }

    public LiveData<List<EmployeeProject>> getCompletedProjectForEmployee(String employeeName) {
        return repository.getCompletedProjectForEmployee(employeeName);
    }

    public void addProject(String employeeName, Integer projectId, String projectName) {
        repository.addProject(new EmployeeProject(employeeName, projectId, projectName));
    }

    public void deleteProject(EmployeeProject employeeProject) {
        repository.deleteProject(employeeProject);
    }
    public void updateEmployeeProject(EmployeeProject employeeProject) {
        repository.updateEmployeeProject(employeeProject);
    }

    public LiveData<String> getListViewOption() {
        return listViewOption;
    }

    public void setListViewOption(String listViewOption) {
        this.listViewOption.setValue(listViewOption);
    }
}

