package com.example.projectmanager.ui.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmanager.R;
import com.example.projectmanager.RecyclerCallback;
import com.example.projectmanager.data.models.Employee;

public class EmployeeAdapter extends ListAdapter<Employee, EmployeeAdapter.ViewHolder> {


    public RecyclerCallback<Employee> callbackView = (U) -> { };
    public RecyclerCallback<Employee> callbackDelete = (U) -> { };

    public EmployeeAdapter() {
        super(new DiffUtil.ItemCallback<Employee>() {
            @Override
            public boolean areItemsTheSame(@NonNull Employee oldItem, @NonNull Employee newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Employee oldItem, @NonNull Employee newItem) {
                return oldItem.getName().equals(newItem.getName());
            }
        });

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView employee_name;
        private ImageButton btnViewEmployeeProject;
        private ImageButton btnDeleteEmployee;

        private Employee employee;


        public ViewHolder(@NonNull View actualView) {
            super(actualView);
            employee_name = actualView.findViewById(R.id.textViewEmployeeName);
            btnViewEmployeeProject = actualView.findViewById(R.id.button_view_employee_project);
            btnDeleteEmployee = actualView.findViewById(R.id.button_delete_employee);

            btnViewEmployeeProject.setOnClickListener(v -> {
                callbackView.returnValue(employee);
            });
            btnDeleteEmployee.setOnClickListener(v -> {
                callbackDelete.returnValue(employee);
            });
        }

        public void bind(Employee employee) {
            employee_name.setText(employee.getName());
            this.employee = employee;
        }

    }

}
