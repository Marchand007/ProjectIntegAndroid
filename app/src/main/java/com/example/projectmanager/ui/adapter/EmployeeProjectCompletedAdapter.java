package com.example.projectmanager.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectmanager.R;
import com.example.projectmanager.RecyclerCallback;
import com.example.projectmanager.data.models.EmployeeProject;

public class EmployeeProjectCompletedAdapter extends ListAdapter<EmployeeProject, EmployeeProjectCompletedAdapter.ViewHolder> {

    public RecyclerCallback<EmployeeProject> callbackView = (U) -> { };
    public RecyclerCallback<EmployeeProject> callbackDelete = (U) -> { };
    public RecyclerCallback<EmployeeProject> callbackReActive = (U) -> { };


    public EmployeeProjectCompletedAdapter() {
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
    public EmployeeProjectCompletedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employeeprojectcompleted_item, parent, false);
        return new EmployeeProjectCompletedAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeProjectCompletedAdapter.ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView project_name;
        private EmployeeProject employeeProject;
        private ImageButton btnViewProjectDetail;
        private ImageButton btnDeleteEmployeeProject;
        private ImageButton btnReActiveProject;

        public ViewHolder(@NonNull View actualView) {
            super(actualView);
            project_name = actualView.findViewById(R.id.textViewEmployeeProjectCompletedName);

            btnViewProjectDetail = actualView.findViewById(R.id.button_view_project_detail_in_employeeprojectcompleted);
            btnDeleteEmployeeProject = actualView.findViewById(R.id.button_delete_employeeprojectcompleted);
            btnReActiveProject = actualView.findViewById(R.id.button_remettre_actif);

            btnViewProjectDetail.setOnClickListener(v -> {
                callbackView.returnValue(employeeProject);
            });
            btnDeleteEmployeeProject.setOnClickListener(v -> {
                callbackDelete.returnValue(employeeProject);
            });
            btnReActiveProject.setOnClickListener(v -> {
                callbackReActive.returnValue(employeeProject);
            });
        }

        public void bind(EmployeeProject employeeProject) {
            project_name.setText(String.valueOf(employeeProject.getProjectName()));
            this.employeeProject = employeeProject;
        }
    }

}