package com.example.projectmanager.data.repository;

import androidx.lifecycle.LiveData;

import com.example.projectmanager.data.ProjectManagerDatabase;
import com.example.projectmanager.data.dao.EmployeeDAO;
import com.example.projectmanager.data.models.Employee;

import java.util.List;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class EmployeeRepository {


    private final EmployeeDAO employeeDAO;


    @Inject
    public EmployeeRepository(ProjectManagerDatabase database) { employeeDAO = database.getEmployeeDao(); }


    public LiveData<List<Employee>> getAllEmployee() {
        return employeeDAO.getAllEmployee();
    }

    public void addEmployee(Employee employee) {
        Executors.newSingleThreadExecutor().execute(() -> {
            employeeDAO.addEmployee(employee);
        });
    }

    public void deleteEmployee(Employee employee) {
        Executors.newSingleThreadExecutor().execute(() -> {
        employeeDAO.deleteEmployee(employee);
        });
    }

}
