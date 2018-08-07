package com.saadnayyer.remotemachinehealth.helpers;

import android.content.Context;
import android.widget.Toast;

import com.saadnayyer.remotemachinehealth.database.MachineStatusDataSource;
import com.saadnayyer.remotemachinehealth.models.MachineStatus;

import java.util.List;

public class DBOperations {
    private static DBOperations Instance;
    private Context context;
    private MachineStatusDataSource machineStatusDataSource;

    public static DBOperations getInstance(Context context){
        if (Instance == null)
            Instance = new DBOperations(context.getApplicationContext());
        return Instance;
    }

    public DBOperations(Context context){
        this.context = context;
        this.machineStatusDataSource = new MachineStatusDataSource(context);
    }

    public long insertRecord(long machineId, long statusId, String uptime, String downTime){
        machineStatusDataSource.open();
        MachineStatus object = new MachineStatus();
        object.setMachineId(machineId);
        object.setStatus(statusId);
        object.setUptime(uptime);
        object.setDowntime(downTime);
        object = machineStatusDataSource.create(object);
        this.machineStatusDataSource.close();
//        UIHelper.showToast(context, object.getDowntime(), Toast.LENGTH_LONG);
        return object.getId();
    }

    public List<MachineStatus> getMachineStatuses(){
        machineStatusDataSource.open();
        List<MachineStatus> machineStatuses = machineStatusDataSource.findAll();
        machineStatusDataSource.close();
        return machineStatuses;
    }
}
