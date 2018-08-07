package com.saadnayyer.remotemachinehealth.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.saadnayyer.remotemachinehealth.helpers.DBHelper;
import com.saadnayyer.remotemachinehealth.models.Machine;
import com.saadnayyer.remotemachinehealth.models.MachineStatus;

import java.util.ArrayList;
import java.util.List;

public class MachineStatusDataSource {
    SQLiteOpenHelper dbhelper;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            DBHelper.MACHINE_STATUS_ID, DBHelper.MACHINE_STATUS_MACHINE_ID,
            DBHelper.MACHINE_STATUS_STATUS_ID, DBHelper.MACHINE_STATUS_UP_TIME,
            DBHelper.MACHINE_STATUS_DOWN_TIME
    };

    public MachineStatusDataSource(Context context){
        dbhelper = new DBOpenHelper(context);
    }

    public void open(){
        database = dbhelper.getWritableDatabase();
    }

    public void close(){
        dbhelper.close();
    }

    public MachineStatus create(MachineStatus object){
        ContentValues values = new ContentValues();
        values.put(DBHelper.MACHINE_STATUS_MACHINE_ID, object.getMachineId());
        values.put(DBHelper.MACHINE_STATUS_STATUS_ID, object.getStatus());
        values.put(DBHelper.MACHINE_STATUS_UP_TIME, object.getUptime());
        values.put(DBHelper.MACHINE_STATUS_DOWN_TIME, object.getDowntime());
        long insertID = database.insert(DBHelper.TABLE_MACHINE_STATUS, null, values);
        object.setId(insertID);
        return object;
    }

    public List<MachineStatus> findAll(){
        List<MachineStatus> machineStatuses = new ArrayList<MachineStatus>();
        Cursor cursor = database.query(DBHelper.TABLE_MACHINE_STATUS, allColumns, null, null, null, null, null);

        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                MachineStatus machineStatus = new MachineStatus();
                machineStatus.setId(cursor.getLong(cursor.getColumnIndex(DBHelper.MACHINE_STATUS_ID)));
                machineStatus.setMachineId(cursor.getLong(cursor.getColumnIndex(DBHelper.MACHINE_STATUS_ID)));
                machineStatus.setStatus(cursor.getLong(cursor.getColumnIndex(DBHelper.MACHINE_STATUS_STATUS_ID)));
                machineStatus.setUptime(cursor.getString(cursor.getColumnIndex(DBHelper.MACHINE_STATUS_UP_TIME)));
                machineStatus.setDowntime(cursor.getString(cursor.getColumnIndex(DBHelper.MACHINE_STATUS_DOWN_TIME)));
                machineStatuses.add(machineStatus);
            }
        }

        cursor.close();
        return machineStatuses;
    }
}
