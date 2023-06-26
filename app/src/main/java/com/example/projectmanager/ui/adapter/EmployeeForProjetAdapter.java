package com.example.projectmanager.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmanager.R;
import com.example.projectmanager.RecyclerCallback;
import com.example.projectmanager.data.models.Employee;
import com.example.projectmanager.data.models.EmployeeProject;

public class EmployeeForProjetAdapter extends ListAdapter<EmployeeProject, EmployeeForProjetAdapter.ViewHolder> {


    public EmployeeForProjetAdapter() {
        super(new DiffUtil.ItemCallback<EmployeeProject>() {
            @Override
            public boolean areItemsTheSame(@NonNull EmployeeProject oldItem, @NonNull EmployeeProject newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull EmployeeProject oldItem, @NonNull EmployeeProject newItem) {
                return oldItem.getEmployeeName().equals(newItem.getEmployeeName()) && oldItem.getProjectId().equals(newItem.getProjectId());
            }
        });

    }

    @NonNull
    @Override
    public EmployeeForProjetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_in_project_item, parent, false);
        return new EmployeeForProjetAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeForProjetAdapter.ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView employee_name;



        public ViewHolder(@NonNull View actualView) {
            super(actualView);
            employee_name = actualView.findViewById(R.id.employee_name_for_project);

        }

        public void bind(EmployeeProject employeeProject) {
            Log.i("IsACtive", String.valueOf(employeeProject.isActive()));
            if (employeeProject.isActive()) {
                employee_name.setText(employeeProject.getEmployeeName().concat(" (en cours)"));
            } else {
                employee_name.setText(employeeProject.getEmployeeName().concat(" (complété)"));
            }

        }

    }

}