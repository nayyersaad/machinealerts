package com.saadnayyer.remotemachinehealth.helpers;

public class DBHelper {
    /* Constant DB Name */
    public static final String DATABASE_NAME = "machinehealth.db";
    /* Constant DB Version code */
    public static final int DATABASE_VERSION = 1;

    /* Constants to Create TABLE: MachineStatus :: CFM */
    public static final String TABLE_MACHINE_STATUS = "MachineStatus";
    public static final String MACHINE_STATUS_ID = "id";
    public static final String MACHINE_STATUS_MACHINE_ID = "machineId";
    public static final String MACHINE_STATUS_STATUS_ID = "statusId";
    public static final String MACHINE_STATUS_UP_TIME = "uptime";
    public static final String MACHINE_STATUS_DOWN_TIME = "downtime";

    public static final String CREATE_TABLE_MACHINE_STATUS =
            "CREATE TABLE " + TABLE_MACHINE_STATUS + "(" +
                    MACHINE_STATUS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    MACHINE_STATUS_MACHINE_ID + " INTEGER, " +
                    MACHINE_STATUS_STATUS_ID + " INTEGER, " +
                    MACHINE_STATUS_UP_TIME + " TEXT, " +
                    MACHINE_STATUS_DOWN_TIME + " TEXT" +
                    ")";
}
