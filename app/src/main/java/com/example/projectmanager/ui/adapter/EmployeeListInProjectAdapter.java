package com.example.projectmanager.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmanager.R;
import com.example.projectmanager.data.models.EmployeeProject;

public class EmployeeListInProjectAdapter extends ListAdapter<EmployeeProject, EmployeeListInProjectAdapter.ViewHolder> {


    public EmployeeListInProjectAdapter() {
        super(new DiffUtil.ItemCallback<EmployeeProject>() {
            @Override
            public boolean areItemsTheSame(@NonNull EmployeeProject oldItem, @NonNull EmployeeProject newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull EmployeeProject oldItem, @NonNull EmployeeProject newItem) {
                return oldItem.getEmployeeEid().equals(newItem.getEmployeeEid()) && oldItem.getProjectId().equals(newItem.getProjectId());
            }
        });

    }

    @NonNull
    @Override
    public EmployeeListInProjectAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_in_project_item, parent, false);
        return new EmployeeListInProjectAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeListInProjectAdapter.ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView employee_name;



        public ViewHolder(@NonNull View actualView) {
            super(actualView);
            employee_name = actualView.findViewById(R.id.employee_name_for_project);

        }

        public void bind(EmployeeProject employeeProject) {
            if (employeeProject.isActive()) {
                employee_name.setText(employeeProject.getEmployeeName().concat(" (en cours)"));
            } else {
                employee_name.setText(employeeProject.getEmployeeName().concat(" (complété)"));
            }

        }

    }

}