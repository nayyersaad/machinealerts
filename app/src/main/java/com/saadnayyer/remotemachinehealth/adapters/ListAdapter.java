package com.saadnayyer.remotemachinehealth.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saadnayyer.remotemachinehealth.R;
import com.saadnayyer.remotemachinehealth.activities.MainActivity;
import com.saadnayyer.remotemachinehealth.databinding.LayoutDetailsItemBinding;
import com.saadnayyer.remotemachinehealth.helpers.AppConstants;
import com.saadnayyer.remotemachinehealth.models.MachineStatus;
import com.saadnayyer.remotemachinehealth.models.Notification;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.Holder>{
    LayoutDetailsItemBinding binding;
    MainActivity activityContext;
    ArrayList<MachineStatus> arrayList;

    public ListAdapter(MainActivity activityContext){
        this.activityContext = activityContext;
        this.arrayList = new ArrayList<>();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(activityContext), R.layout.layout_details_item, parent, false);
        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        String machine = "";
        if(arrayList.get(position).getMachineId() == AppConstants.MachineIds.ADDITION){
            machine = AppConstants.MachineNames.ADDITION;
        }else if(arrayList.get(position).getMachineId() == AppConstants.MachineIds.SUBTRACTION){
            machine = AppConstants.MachineNames.SUBTRACTION;
        }else if(arrayList.get(position).getMachineId() == AppConstants.MachineIds.MULTIPLICATION){
            machine = AppConstants.MachineNames.MULTIPLICATION;
        }else if(arrayList.get(position).getMachineId() == AppConstants.MachineIds.DIVISION){
            machine = AppConstants.MachineNames.DIVISION;
        }

        binding.tvDetails.setText(machine + " " + activityContext.getResources().getString(R.string.has_been_shut_down));
        binding.tvDateTime.setText(arrayList.get(position).getDowntime());
    }

    @Override
    public int getItemCount() {
        return this.arrayList.size();
    }

    public void addAll(ArrayList<MachineStatus> arrayList){
        this.arrayList.clear();
        this.arrayList.addAll(arrayList);
    }

    public static class Holder extends RecyclerView.ViewHolder {
        LayoutDetailsItemBinding binding;

        Holder(LayoutDetailsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public Holder(View itemView) {
            super(itemView);
        }
    }
}
