package com.example.projectmanager.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;
import androidx.room.Query;

import com.example.projectmanager.R;
import com.example.projectmanager.RecyclerCallback;
import com.example.projectmanager.data.ProjectManagerDatabase;
import com.example.projectmanager.data.models.EmployeeProject;
import com.example.projectmanager.data.models.Project;
import com.example.projectmanager.ui.viewmodel.ProjectViewModel;

public class EmployeeProjectActiveAdapter extends ListAdapter<EmployeeProject, EmployeeProjectActiveAdapter.ViewHolder> {

    public RecyclerCallback<EmployeeProject> callbackView = (U) -> {
    };
    public RecyclerCallback<EmployeeProject> callbackDelete = (U) -> {
    };
    public RecyclerCallback<EmployeeProject> callbackCompleted = (U) -> {
    };

    public EmployeeProjectActiveAdapter() {
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
    public EmployeeProjectActiveAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employeeprojectactive_item, parent, false);
        return new EmployeeProjectActiveAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeProjectActiveAdapter.ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView project_name;
        private EmployeeProject employeeProject;
        private ImageButton btnViewProjectDetail;
        private ImageButton btnDeleteEmployeeProject;
        private Button btnMarkAsCompleted;

        public ViewHolder(@NonNull View actualView) {
            super(actualView);
            project_name = actualView.findViewById(R.id.textViewEmployeeProjectActiveName);
            btnViewProjectDetail = actualView.findViewById(R.id.button_view_project_detail_in_employeeprojectactive);
            btnDeleteEmployeeProject = actualView.findViewById(R.id.button_delete_employeeprojectactive);
            btnMarkAsCompleted = actualView.findViewById(R.id.button_complete_project);

            btnViewProjectDetail.setOnClickListener(v -> {
                callbackView.returnValue(employeeProject);
            });
            btnDeleteEmployeeProject.setOnClickListener(v -> {
                callbackDelete.returnValue(employeeProject);
            });
            btnMarkAsCompleted.setOnClickListener(v -> {
                callbackCompleted.returnValue(employeeProject);
            });
        }

        public void bind(EmployeeProject employeeProject) {
            
            project_name.setText(String.valueOf(employeeProject.getProjectId()));
            this.employeeProject = employeeProject;
        }
    }

}