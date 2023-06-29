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

    private final EmployeeProjectRepository repository;
    private MutableLiveData<String> listViewOption;

    @Inject
    public EmployeeProjectViewModel(EmployeeProjectRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<EmployeeProject>> getActiveProjectForEmployee(Integer employeeEid) {
        return repository.getActiveProjectForEmployee(employeeEid);
    }

    public LiveData<List<EmployeeProject>> getCompletedProjectForEmployee(Integer employeeEid) {
        return repository.getCompletedProjectForEmployee(employeeEid);
    }

    public void addProject(Integer employeeEid, Integer projectId, Boolean isActive, Integer priority, String projectName, String employeeName) {
        repository.addProject(new EmployeeProject(employeeEid, projectId, isActive, priority, projectName, employeeName));
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

