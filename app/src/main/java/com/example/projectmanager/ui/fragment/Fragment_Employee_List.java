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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;

import com.example.projectmanager.RecyclerCallback;
import com.example.projectmanager.data.models.Employee;
import com.example.projectmanager.data.models.EmployeeProject;
import com.example.projectmanager.data.models.Project;
import com.example.projectmanager.ui.adapter.EmployeeAdapter;
import com.example.projectmanager.R;
import com.example.projectmanager.ui.viewmodel.EmployeeListViewModel;

import java.util.List;

public class Fragment_Employee_List extends Fragment {

    private EmployeeAdapter adapter = new EmployeeAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_employee_list, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EmployeeListViewModel viewModel = new ViewModelProvider(requireActivity()).get(EmployeeListViewModel.class);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewEmployee);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        viewModel.getAllEmployee().observe(getViewLifecycleOwner(), new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                adapter.submitList(employees);
            }
        });

        Button btnAddEmployee = view.findViewById(R.id.buttonAddEmployee);
        EditText EmployeeNameToAdd = view.findViewById(R.id.editTextAddEmployee_name);

        btnAddEmployee.setOnClickListener(v -> {
            if (!EmployeeNameToAdd.getText().toString().equals("")) {
                viewModel.addEmployee(EmployeeNameToAdd.getText().toString());
                EmployeeNameToAdd.setText("");
            }
        });


        adapter.callbackDelete = new RecyclerCallback<Employee>() {
            @Override
            public void returnValue(Employee emp) {
                new AlertDialog.Builder(view.getContext()).setTitle("Confirmation de suppresion").setMessage("Êtes-vous vraiment sur de vouloir supprimer " + emp.getName())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(R.string.confirmersupression, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                viewModel.deleteEmployee(emp);
                            }
                        }).setNegativeButton(R.string.annuler, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }).show();
            }
        };

        adapter.callbackView = new RecyclerCallback<Employee>() {
            @Override
            public void returnValue(Employee emp) {
                Bundle bundle = new Bundle();
                bundle.putString("employeeName", emp.getName());
                Navigation.findNavController(view).navigate(R.id.action_fragment_Employee_List_to_fragment_Project_List_By_Employee, bundle);
            }
        };
    }
}