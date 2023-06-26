package com.example.projectmanager.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.projectmanager.R;
import com.example.projectmanager.RecyclerCallback;
import com.example.projectmanager.data.models.Employee;
import com.example.projectmanager.data.models.EmployeeProject;
import com.example.projectmanager.data.models.Project;
import com.example.projectmanager.ui.adapter.EmployeeAdapter;
import com.example.projectmanager.ui.adapter.EmployeeForProjetAdapter;
import com.example.projectmanager.ui.adapter.ProjectAdapter;
import com.example.projectmanager.ui.viewmodel.EmployeeListViewModel;
import com.example.projectmanager.ui.viewmodel.ProjectViewModel;

import java.util.List;

public class Fragment_Project_Info extends Fragment {

    private EmployeeForProjetAdapter adapter = new EmployeeForProjetAdapter();
    private String projectName;
    private Integer projectId;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__project__info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ProjectViewModel viewModel = new ViewModelProvider(requireActivity()).get(ProjectViewModel.class);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_employee_for_project);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        TextView textViewProjectName = view.findViewById(R.id.project_name_info);
        TextView textViewProjectClientName = view.findViewById(R.id.project_client_name_info);
        TextView textViewProjectDeadlineDate = view.findViewById(R.id.project_deadlinedate_info);

        Bundle arguments = getArguments();
        if (arguments == null) {
            Navigation.findNavController(view).navigateUp();
            return;
        }
        projectId = arguments.getInt("projectId");


        textViewProjectName.setText(projectName);
        viewModel.getProject(projectId).observe(getViewLifecycleOwner(), new Observer<Project>() {
            @Override
            public void onChanged(Project project) {
                textViewProjectName.setText(project.getName());
                textViewProjectClientName.setText(project.getClient());
                textViewProjectDeadlineDate.setText(project.getDeadlineDate());
            }
        });
        viewModel.getEmployeesForProject(projectId).observe(getViewLifecycleOwner(), new Observer<List<EmployeeProject>>() {
            @Override
            public void onChanged(List<EmployeeProject> employeesProject) {
                adapter.submitList(employeesProject);
            }
        });
    }
}