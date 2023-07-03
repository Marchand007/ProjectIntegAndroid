package com.example.projectmanager.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

    public RecyclerCallback<EmployeeProject> callbackChangePriority = (U) -> {
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
        private ImageButton btnMarkAsCompleted;

        private RadioGroup radioGroupPriority;
        private RadioButton radioButtonBas;
        private RadioButton radioButtonNormal;
        private RadioButton radioButtonEleve;

        public ViewHolder(@NonNull View actualView) {
            super(actualView);
            project_name = actualView.findViewById(R.id.textViewEmployeeProjectActiveName);
            btnViewProjectDetail = actualView.findViewById(R.id.button_view_project_detail_in_employeeprojectactive);
            btnDeleteEmployeeProject = actualView.findViewById(R.id.button_delete_employeeprojectactive);
            btnMarkAsCompleted = actualView.findViewById(R.id.button_complete_project);
            radioGroupPriority = actualView.findViewById(R.id.radioGroupPriorityActive);
            radioButtonBas = actualView.findViewById(R.id.radio_button_low_priority_active);
            radioButtonNormal = actualView.findViewById(R.id.radioButto_normal_priority_active);
            radioButtonEleve = actualView.findViewById(R.id.radio_button_high_priority_active);

            btnViewProjectDetail.setOnClickListener(v -> {
                callbackView.returnValue(employeeProject);
            });
            btnDeleteEmployeeProject.setOnClickListener(v -> {
                callbackDelete.returnValue(employeeProject);
            });
            btnMarkAsCompleted.setOnClickListener(v -> {
                callbackCompleted.returnValue(employeeProject);
            });

            radioButtonBas.setOnClickListener(v -> {
                employeeProject.setPriority(3);
                callbackChangePriority.returnValue(employeeProject);
            });
            radioButtonNormal.setOnClickListener(v -> {
                employeeProject.setPriority(2);
                callbackChangePriority.returnValue(employeeProject);
            });
            radioButtonEleve.setOnClickListener(v -> {
                employeeProject.setPriority(1);
                callbackChangePriority.returnValue(employeeProject);
            });
        }

        public void bind(EmployeeProject employeeProject) {
            this.employeeProject = employeeProject;
            final Integer priority = employeeProject.getPriority();

            project_name.setText(employeeProject.getProjectName());
            switch (priority) {
                case 1 -> radioButtonEleve.setChecked(true);
                case 2 -> radioButtonNormal.setChecked(true);
                case 3 -> radioButtonBas.setChecked(true);
            }
        }
    }
}