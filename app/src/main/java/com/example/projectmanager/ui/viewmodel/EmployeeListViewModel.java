package com.example.projectmanager.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.projectmanager.data.models.Employee;
import com.example.projectmanager.data.repository.EmployeeRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class EmployeeListViewModel extends ViewModel {

    private EmployeeRepository repository;

    @Inject
    public EmployeeListViewModel(EmployeeRepository repository) { this.repository = repository; }

    public LiveData<List<Employee>> getAllEmployee() {
        return repository.getAllEmployee();
    }
    public void addEmployee(Integer employeeEid, String name, String role, String manager) {
        repository.addEmployee(new Employee(employeeEid, name, role, manager));
    }
    public void deleteEmployee(Employee employee) {
        repository.deleteEmployee(employee);
    }
}
