package com.example.projectmanager.ui.adapter;

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
import com.example.projectmanager.data.models.Project;

public class ProjectListForAddAdapter extends ListAdapter<Project, ProjectListForAddAdapter.ViewHolder> {

    public RecyclerCallback<Project> callbackView = (U) -> { };
    public RecyclerCallback<Project> callbackAdd= (U) -> { };

    public ProjectListForAddAdapter() {
        super(new DiffUtil.ItemCallback<Project>() {
            @Override
            public boolean areItemsTheSame(@NonNull Project oldItem, @NonNull Project newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Project oldItem, @NonNull Project newItem) {
                return oldItem.getName().equals(newItem.getName());
            }
        });
    }

    @NonNull
    @Override
    public ProjectListForAddAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_item, parent, false);
        return new ProjectListForAddAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectListForAddAdapter.ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView project_name;
        private Project project;
        private ImageButton btnViewProjectDetail;
        private ImageButton btnAddProjectToEmployee;


        public ViewHolder(@NonNull View actualView) {
            super(actualView);
            project_name = actualView.findViewById(R.id.textViewProjectName);
            btnViewProjectDetail = actualView.findViewById(R.id.button_view_project_detail_in_projectlist);
            btnAddProjectToEmployee = actualView.findViewById(R.id.button_add_project_to_employee);

            btnViewProjectDetail.setOnClickListener(v -> {
                callbackView.returnValue(project);
            });
            btnAddProjectToEmployee.setOnClickListener(v -> {
                callbackAdd.returnValue(project);
            });

        }

        public void bind(Project project) {
            project_name.setText(project.getName());
            this.project = project;
        }
    }
}