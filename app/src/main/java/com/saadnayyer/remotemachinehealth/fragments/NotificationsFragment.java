package com.saadnayyer.remotemachinehealth.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saadnayyer.remotemachinehealth.R;
import com.saadnayyer.remotemachinehealth.adapters.ListAdapter;
import com.saadnayyer.remotemachinehealth.databinding.FragmentNotificationsBinding;
import com.saadnayyer.remotemachinehealth.helpers.DBOperations;
import com.saadnayyer.remotemachinehealth.helpers.Titlebar;
import com.saadnayyer.remotemachinehealth.models.MachineStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class NotificationsFragment extends MainFragment {
    FragmentNotificationsBinding binding;
    LinearLayoutManager linearLayoutManager;
    ListAdapter adapter;
    ArrayList<MachineStatus> arrayList;

    public NotificationsFragment(){}

    public static NotificationsFragment Instance(){
        return new NotificationsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notifications, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new ListAdapter(mainActivityContext);
        linearLayoutManager = new LinearLayoutManager(mainActivityContext);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setAdapter(adapter);

        setNotitifications();
    }

    @Override
    public void setTitlebar(Titlebar titlebar) {
        titlebar.resetTitlebar();
        titlebar.setTitle(mainActivityContext.getResources().getString(R.string.notifications));
        titlebar.showBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityContext.onBackPressed();
            }
        });
    }

    private void setNotitifications(){
        arrayList = new ArrayList<>();

        arrayList = (ArrayList<MachineStatus>) DBOperations.getInstance(mainActivityContext).getMachineStatuses();
        if(arrayList.size() > 0){
            binding.tvEmptyError.setVisibility(View.GONE);
            binding.recyclerView.setVisibility(View.VISIBLE);
//            Collections.reverse(arrayList);
            adapter.addAll(arrayList);
            adapter.notifyDataSetChanged();
        }
    }
}
