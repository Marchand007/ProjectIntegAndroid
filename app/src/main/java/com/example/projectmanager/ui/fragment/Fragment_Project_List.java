package com.example.projectmanager.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.projectmanager.R;
import com.example.projectmanager.RecyclerCallback;
import com.example.projectmanager.data.models.Project;
import com.example.projectmanager.ui.adapter.ProjectListForAddAdapter;
import com.example.projectmanager.ui.viewmodel.EmployeeProjectViewModel;
import com.example.projectmanager.ui.viewmodel.ProjectViewModel;

import java.util.List;


public class Fragment_Project_List extends Fragment {

    private ProjectListForAddAdapter adapter = new ProjectListForAddAdapter();
    private String employeeName;
    private Integer employeeEid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__project__list__add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ProjectViewModel projectViewModel = new ViewModelProvider(requireActivity()).get(ProjectViewModel.class);
        EmployeeProjectViewModel employeeProjectViewModel = new ViewModelProvider(requireActivity()).get(EmployeeProjectViewModel.class);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewProjectList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        Bundle arguments = getArguments();
        if (arguments == null) {
            Navigation.findNavController(view).navigateUp();
            return;
        }
        employeeName = arguments.getString("employeeName");
        employeeEid = arguments.getInt("employeeEid");

        RadioGroup radioGroupPriority = view.findViewById(R.id.radioGroupPriorityProjectList);

        projectViewModel.getAllProject().observe(getViewLifecycleOwner(), new Observer<List<Project>>() {
            @Override
            public void onChanged(List<Project> projects) {
                adapter.submitList(projects);
            }
        });

        adapter.callbackAdd = new RecyclerCallback<Project>() {
            @Override
            public void returnValue(Project project) {
                new AlertDialog.Builder(view.getContext()).setTitle("Confirmation d'ajout").setMessage("Voulez-vous vraiment ajouter " + project.getName() + " a " + employeeName)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(R.string.confirmerajout, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                int priority = 2;
                                final String value = ((RadioButton)view.findViewById(radioGroupPriority.getCheckedRadioButtonId())).getText().toString();
                                switch (value) {
                                    case "Bas" -> priority = 3;
                                    case "Elevé" -> priority = 1;
                                }
                                employeeProjectViewModel.addProject(employeeEid, project.getId(),true,priority,project.getName(),employeeName);
                                NavController navController = Navigation.findNavController(view);
                                navController.navigateUp();
                            }
                        }).setNegativeButton(R.string.annuler, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }).show();
            }
        };

        adapter.callbackView = new RecyclerCallback<Project>() {
            @Override
            public void returnValue(Project project) {
                Bundle bundle = new Bundle();
                bundle.putInt("projectId", project.getId());
                Navigation.findNavController(view).navigate(R.id.action_fragment_Project_List_to_fragment_Project_Info, bundle);
            }
        };

    }
}