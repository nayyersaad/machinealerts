package com.saadnayyer.remotemachinehealth.fragments;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saadnayyer.remotemachinehealth.R;
import com.saadnayyer.remotemachinehealth.databinding.FragmentDashboardBinding;
import com.saadnayyer.remotemachinehealth.helpers.AppConstants;
import com.saadnayyer.remotemachinehealth.helpers.Titlebar;
import com.saadnayyer.remotemachinehealth.helpers.UIHelper;
import com.saadnayyer.remotemachinehealth.models.Cloud;

import java.util.Random;

public class DashboardFragment extends MainFragment implements View.OnClickListener {
    FragmentDashboardBinding binding;
    Cloud cloud;

    public DashboardFragment() {
    }

    public static DashboardFragment Instance() {
        return new DashboardFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false);
        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cloud = new Cloud();
        setListeners();
    }

    @SuppressLint("NewApi")
    @Override
    public void onResume() {
        super.onResume();
        setMachines();
    }

    @Override
    public void onClick(View view) {
        cloud.setFirstNumber(getRandomNumber());
        cloud.setSecondNumber(getRandomNumber());
        switch (view.getId()) {
            case R.id.btnAdd:
                add();
                break;

            case R.id.btnSubtract:
                subtract();
                break;

            case R.id.btnMultiply:
                multiply();
                break;

            case R.id.btnDivide:
                divide();
                break;
        }
    }

    @Override
    public void setTitlebar(Titlebar titlebar) {
        titlebar.resetTitlebar();
        titlebar.setTitle(mainActivityContext.getResources().getString(R.string.app_name));
        titlebar.showNotification().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityContext.replaceFragment(NotificationsFragment.Instance(), NotificationsFragment.class.getSimpleName(), true, false);
            }
        });
    }

    private void setListeners() {
        binding.btnAdd.setOnClickListener(this);
        binding.btnSubtract.setOnClickListener(this);
        binding.btnMultiply.setOnClickListener(this);
        binding.btnDivide.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setMachines(){
        if(!mainActivityContext.getPreferenceHelper().getBooleanPrefrence(AppConstants.MachineNames.ADDITION)){
            binding.btnAdd.setText(mainActivityContext.getResources().getString(R.string.wait));
            binding.btnAdd.setBackgroundColor(mainActivityContext.getColor(R.color.colorRed));
        }
        if(!mainActivityContext.getPreferenceHelper().getBooleanPrefrence(AppConstants.MachineNames.SUBTRACTION)){
            binding.btnSubtract.setText(mainActivityContext.getResources().getString(R.string.wait));
            binding.btnSubtract.setBackgroundColor(mainActivityContext.getColor(R.color.colorRed));
        }
        if(!mainActivityContext.getPreferenceHelper().getBooleanPrefrence(AppConstants.MachineNames.MULTIPLICATION)){
            binding.btnMultiply.setText(mainActivityContext.getResources().getString(R.string.wait));
            binding.btnMultiply.setBackgroundColor(mainActivityContext.getColor(R.color.colorRed));
        }
        if(!mainActivityContext.getPreferenceHelper().getBooleanPrefrence(AppConstants.MachineNames.DIVISION)){
            binding.btnDivide.setText(mainActivityContext.getResources().getString(R.string.wait));
            binding.btnDivide.setBackgroundColor(mainActivityContext.getColor(R.color.colorRed));
        }
    }

    private void add() {
        if(mainActivityContext.getPreferenceHelper().getBooleanPrefrence(AppConstants.MachineNames.ADDITION)){
            binding.btnAdd.setText(cloud.add() + "");
        }else{
            UIHelper.showSnackbar(mainActivityContext.getMainFrameLayout(), mainActivityContext.getResources().getString(R.string.machine_is_under), Snackbar.LENGTH_SHORT);
        }
    }

    private void subtract(){
        if(mainActivityContext.getPreferenceHelper().getBooleanPrefrence(AppConstants.MachineNames.SUBTRACTION)){
            binding.btnSubtract.setText(cloud.subtract() + "");
        }else{
            UIHelper.showSnackbar(mainActivityContext.getMainFrameLayout(), mainActivityContext.getResources().getString(R.string.machine_is_under), Snackbar.LENGTH_SHORT);
        }
    }

    private void multiply(){
        if(mainActivityContext.getPreferenceHelper().getBooleanPrefrence(AppConstants.MachineNames.MULTIPLICATION)){
            binding.btnMultiply.setText(cloud.multiply() + "");
        }else{
            UIHelper.showSnackbar(mainActivityContext.getMainFrameLayout(), mainActivityContext.getResources().getString(R.string.machine_is_under), Snackbar.LENGTH_SHORT);
        }
    }

    private void divide(){
        if(mainActivityContext.getPreferenceHelper().getBooleanPrefrence(AppConstants.MachineNames.DIVISION)){
            binding.btnDivide.setText(cloud.divide() + "");
        }else{
            UIHelper.showSnackbar(mainActivityContext.getMainFrameLayout(), mainActivityContext.getResources().getString(R.string.machine_is_under), Snackbar.LENGTH_SHORT);
        }
    }

    private int getRandomNumber() {
        Random rand = new Random();
        int n = rand.nextInt(50) + 1;
        return n;
    }
}
