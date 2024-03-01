package com.cmsh.greendzine.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.cmsh.greendzine.R;
import com.cmsh.greendzine.databinding.RvEmployeeBinding;
import com.cmsh.greendzine.response.EmployeeResponse;

import java.util.List;

public class RvEmployeeAdapter extends RecyclerView.Adapter<RvEmployeeAdapter.ViewHolder> {

    private List<EmployeeResponse> list;
    private LayoutInflater layoutInflater;

    public RvEmployeeAdapter(List<EmployeeResponse> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        RvEmployeeBinding rvEmployeeBinding = DataBindingUtil.inflate(
                layoutInflater, R.layout.rv_employee, parent,false
        );
        return new ViewHolder(rvEmployeeBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RvEmployeeAdapter.ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private RvEmployeeBinding rvEmployeeBinding;
        public ViewHolder(RvEmployeeBinding rvEmployeeBinding) {
            super(rvEmployeeBinding.getRoot());
            this.rvEmployeeBinding = rvEmployeeBinding;
        }

        public void bind(EmployeeResponse employeeResponse){
            rvEmployeeBinding.name.setText(String.format(": %s", employeeResponse.getName()));
            rvEmployeeBinding.dob.setText(String.format(": %s", employeeResponse.getDob()));
            rvEmployeeBinding.empId.setText(String.format(": %s", employeeResponse.getId()));
            rvEmployeeBinding.number.setText(String.format(employeeResponse.getId()));
            rvEmployeeBinding.role.setText(String.format(": %s", employeeResponse.getRole()));
        }
    }
}
