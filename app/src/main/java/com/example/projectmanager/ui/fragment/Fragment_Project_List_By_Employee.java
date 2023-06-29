package com.example.projectmanager.ui.fragment;

import android.annotation.SuppressLint;
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
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.projectmanager.R;
import com.example.projectmanager.RecyclerCallback;
import com.example.projectmanager.data.models.EmployeeProject;
import com.example.projectmanager.ui.adapter.EmployeeProjectActiveAdapter;
import com.example.projectmanager.ui.adapter.EmployeeProjectCompletedAdapter;
import com.example.projectmanager.ui.viewmodel.EmployeeProjectViewModel;
import com.example.projectmanager.ui.viewmodel.ProjectViewModel;

import java.util.List;

public class Fragment_Project_List_By_Employee extends Fragment {

    private EmployeeProjectActiveAdapter adapterActive = new EmployeeProjectActiveAdapter();
    private EmployeeProjectCompletedAdapter adapterCompleted = new EmployeeProjectCompletedAdapter();
    private String employeeName;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_project_list_by_employee, container, false);

    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EmployeeProjectViewModel employeeProjectViewModel = new ViewModelProvider(requireActivity()).get(EmployeeProjectViewModel.class);
        ProjectViewModel projectViewModel = new ViewModelProvider(requireActivity()).get(ProjectViewModel.class);

        RecyclerView recyclerViewActive = view.findViewById(R.id.recyclerViewEmployeeProjectActive);
        recyclerViewActive.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewActive.setAdapter(adapterActive);
        RecyclerView recyclerViewCompleted = view.findViewById(R.id.recyclerViewEmployeeProjectCompleted);
        recyclerViewCompleted.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewCompleted.setAdapter(adapterCompleted);
        recyclerViewCompleted.setVisibility(View.INVISIBLE);

        Bundle arguments = getArguments();
        if (arguments == null) {
            Navigation.findNavController(view).navigateUp();
            return;
        }

        employeeName = arguments.getString("employeeName");
        TextView textViewEmployeeName = view.findViewById(R.id.textViewEmployeeName_in_employeeproject);
        textViewEmployeeName.setText(employeeName);


        employeeProjectViewModel.getActiveProjectForEmployee(employeeName).observe(getViewLifecycleOwner(), new Observer<List<EmployeeProject>>() {
            @Override
            public void onChanged(List<EmployeeProject> employeeProjects) {
                adapterActive.submitList(employeeProjects);
            }
        });
        employeeProjectViewModel.getCompletedProjectForEmployee(employeeName).observe(getViewLifecycleOwner(), new Observer<List<EmployeeProject>>() {
            @Override
            public void onChanged(List<EmployeeProject> employeeProjects) {
                adapterCompleted.submitList(employeeProjects);
            }
        });

        Button btnAddProjectToEmployee = view.findViewById(R.id.buttonAddProjectToEmployee);
        btnAddProjectToEmployee.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("employeeName", employeeName);
            Navigation.findNavController(view).navigate(R.id.action_fragment_Project_List_By_Employee_to_fragment_Project_List, bundle);
        });

        adapterActive.callbackDelete = new RecyclerCallback<EmployeeProject>() {
            @Override
            public void returnValue(EmployeeProject employeeProject) {
                new AlertDialog.Builder(view.getContext()).setTitle("Confirmation de suppresion").setMessage("Êtes-vous vraiment sur de vouloir supprimer " + employeeProject.getProjectName())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(R.string.confirmersupression, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                employeeProjectViewModel.deleteProject(employeeProject);
                            }
                        }).setNegativeButton(R.string.annuler, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }).show();
            }
        };
        adapterCompleted.callbackDelete = new RecyclerCallback<EmployeeProject>() {
            @Override
            public void returnValue(EmployeeProject employeeProject) {
                new AlertDialog.Builder(view.getContext()).setTitle("Confirmation de suppresion").setMessage("Êtes-vous vraiment sur de vouloir supprimer " + employeeProject.getProjectName())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(R.string.confirmersupression, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                employeeProjectViewModel.deleteProject(employeeProject);
                            }
                        }).setNegativeButton(R.string.annuler, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }).show();
            }
        };
        adapterActive.callbackView = new RecyclerCallback<EmployeeProject>() {
            @Override
            public void returnValue(EmployeeProject employeeProject) {
                Bundle bundle = new Bundle();
                bundle.putInt("projectId", employeeProject.getProjectId());
                Navigation.findNavController(view).navigate(R.id.action_fragment_Project_List_By_Employee_to_fragment_Project_Info, bundle);
            }
        };
        adapterCompleted.callbackView = new RecyclerCallback<EmployeeProject>() {
            @Override
            public void returnValue(EmployeeProject employeeProject) {
                Bundle bundle = new Bundle();
                bundle.putInt("projectId", employeeProject.getProjectId());
                Navigation.findNavController(view).navigate(R.id.action_fragment_Project_List_By_Employee_to_fragment_Project_Info, bundle);
            }
        };
        adapterActive.callbackCompleted = new RecyclerCallback<EmployeeProject>() {
            @Override
            public void returnValue(EmployeeProject employeeProject) {
                Log.i("ICI", "COMPLETE ");
                employeeProject.setActive(false);
                employeeProjectViewModel.updateEmployeeProject(employeeProject);
            }
        };
        adapterCompleted.callbackReActive = new RecyclerCallback<EmployeeProject>() {
            @Override
            public void returnValue(EmployeeProject employeeProject) {
                Log.i("ICI", "REACTIVE ");
                employeeProject.setActive(true);
                employeeProjectViewModel.updateEmployeeProject(employeeProject);
            }
        };

        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch switchStatusProjectView = view.findViewById(R.id.switch1);

        switchStatusProjectView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    recyclerViewActive.setVisibility(View.INVISIBLE);
                    recyclerViewCompleted.setVisibility(View.VISIBLE);
                }
                else {
                    recyclerViewActive.setVisibility(View.VISIBLE);
                    recyclerViewCompleted.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}