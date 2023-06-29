package com.example.projectmanager.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.projectmanager.data.dao.EmployeeDAO;
import com.example.projectmanager.data.dao.EmployeeProjectDAO;
import com.example.projectmanager.data.dao.ProjectDAO;
import com.example.projectmanager.data.models.Employee;
import com.example.projectmanager.data.models.EmployeeProject;
import com.example.projectmanager.data.models.Project;

@Database(entities = { Employee.class, EmployeeProject.class,Project.class }, version = 6)
public abstract class ProjectManagerDatabase extends RoomDatabase {

    public abstract EmployeeDAO getEmployeeDao();
    public abstract ProjectDAO getProjectDao();
    public abstract EmployeeProjectDAO getEmployeeProjectDao();
}
