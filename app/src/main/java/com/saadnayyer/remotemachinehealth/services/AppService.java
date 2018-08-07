package com.saadnayyer.remotemachinehealth.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.saadnayyer.remotemachinehealth.R;
import com.saadnayyer.remotemachinehealth.helpers.AppConstants;
import com.saadnayyer.remotemachinehealth.helpers.BasePreferenceHelper;
import com.saadnayyer.remotemachinehealth.helpers.DBOperations;
import com.saadnayyer.remotemachinehealth.helpers.UIHelper;
import com.saadnayyer.remotemachinehealth.helpers.Utils;

public class AppService extends Service {
    private Context context;
    private BasePreferenceHelper preferenceHelper;
    private int mInterval = 300000; // 5 seconds by default, can be changed later
    private Handler mHandler;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        preferenceHelper = new BasePreferenceHelper(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mHandler = new Handler();
        startRepeatingTask();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(mStatusChecker);
    }

    public void setMachines() {
        String machine = "";
        preferenceHelper.clearStatuses();
        switch (Utils.getRandomNumber()) {
            case AppConstants.MachineIds.ADDITION:
                preferenceHelper.setBooleanPrefrence(AppConstants.MachineNames.SUBTRACTION, true);
                preferenceHelper.setBooleanPrefrence(AppConstants.MachineNames.MULTIPLICATION, true);
                preferenceHelper.setBooleanPrefrence(AppConstants.MachineNames.DIVISION, true);
                machine = AppConstants.MachineNames.ADDITION;
                DBOperations.getInstance(context).insertRecord(AppConstants.MachineIds.ADDITION, AppConstants.MachineStatuses.DOWN, "", Utils.getCurrentDateTime());
                Utils.sendNotification(context, context.getResources().getString(R.string.alert_machine_down), AppConstants.MachineNames.ADDITION + " " + context.getResources().getString(R.string.has_been_shut_down) + " " + Utils.getCurrentDateTime());
                break;

            case AppConstants.MachineIds.SUBTRACTION:
                preferenceHelper.setBooleanPrefrence(AppConstants.MachineNames.ADDITION, true);
                preferenceHelper.setBooleanPrefrence(AppConstants.MachineNames.MULTIPLICATION, true);
                preferenceHelper.setBooleanPrefrence(AppConstants.MachineNames.DIVISION, true);
                machine = AppConstants.MachineNames.SUBTRACTION;
                DBOperations.getInstance(context).insertRecord(AppConstants.MachineIds.SUBTRACTION, AppConstants.MachineStatuses.DOWN, "", Utils.getCurrentDateTime());
                Utils.sendNotification(context, context.getResources().getString(R.string.alert_machine_down), AppConstants.MachineNames.SUBTRACTION + " " + context.getResources().getString(R.string.has_been_shut_down) + " " + Utils.getCurrentDateTime());
                break;

            case AppConstants.MachineIds.MULTIPLICATION:
                preferenceHelper.setBooleanPrefrence(AppConstants.MachineNames.SUBTRACTION, true);
                preferenceHelper.setBooleanPrefrence(AppConstants.MachineNames.ADDITION, true);
                preferenceHelper.setBooleanPrefrence(AppConstants.MachineNames.DIVISION, true);
                machine = AppConstants.MachineNames.MULTIPLICATION;
                DBOperations.getInstance(context).insertRecord(AppConstants.MachineIds.MULTIPLICATION, AppConstants.MachineStatuses.DOWN, "", Utils.getCurrentDateTime());
                Utils.sendNotification(context, context.getResources().getString(R.string.alert_machine_down), AppConstants.MachineNames.MULTIPLICATION + " " + context.getResources().getString(R.string.has_been_shut_down) + " " + Utils.getCurrentDateTime());
                break;

            case AppConstants.MachineIds.DIVISION:
                preferenceHelper.setBooleanPrefrence(AppConstants.MachineNames.SUBTRACTION, true);
                preferenceHelper.setBooleanPrefrence(AppConstants.MachineNames.MULTIPLICATION, true);
                preferenceHelper.setBooleanPrefrence(AppConstants.MachineNames.ADDITION, true);
                machine = AppConstants.MachineNames.DIVISION;
                DBOperations.getInstance(context).insertRecord(AppConstants.MachineIds.DIVISION, AppConstants.MachineStatuses.DOWN, "", Utils.getCurrentDateTime());
                Utils.sendNotification(context, context.getResources().getString(R.string.alert_machine_down), AppConstants.MachineNames.DIVISION + " " + context.getResources().getString(R.string.has_been_shut_down) + " " + Utils.getCurrentDateTime());
                break;
        }
    }

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                setMachines();
            } finally {
                mHandler.postDelayed(mStatusChecker, mInterval);
            }
        }
    };

    void startRepeatingTask() {
        mStatusChecker.run();
    }
}
