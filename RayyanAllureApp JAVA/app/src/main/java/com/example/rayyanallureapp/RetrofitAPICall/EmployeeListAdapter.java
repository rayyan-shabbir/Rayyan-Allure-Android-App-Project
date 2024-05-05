package com.example.rayyanallureapp.RetrofitAPICall;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.rayyanallureapp.R;
import java.util.ArrayList;
import java.util.List;

public class EmployeeListAdapter extends RecyclerView.Adapter<EmployeeListAdapter.EmployeeViewHolder> {

    private List<EmployeeGet.Data> employeeList = new ArrayList<>();

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_retro, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        EmployeeGet.Data employee = employeeList.get(position);
        holder.txtId.setText(employee.getId());
        holder.txtEmail.setText(employee.getEmail());
        holder.txtFirstName.setText(employee.getFirst_name());
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public void setEmployeeList(List<EmployeeGet.Data> employeeList) {
        this.employeeList = employeeList;
        notifyDataSetChanged();
    }

    static class EmployeeViewHolder extends RecyclerView.ViewHolder {
        TextView txtId;
        TextView txtEmail;
        TextView txtFirstName;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txtId);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            txtFirstName = itemView.findViewById(R.id.txtFirstName);
        }
    }
}
