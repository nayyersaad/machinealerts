package com.saadnayyer.remotemachinehealth.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class BasePreferenceHelper extends PreferenceHelper {
    private Context context;
    private static final String FILENAME = "preferences";

    public BasePreferenceHelper(Context c) {
        this.context = c;
    }

    public SharedPreferences getSharedPreferences() {
        return context.getSharedPreferences(FILENAME, Activity.MODE_PRIVATE);
    }

    public void clearStatuses() {
        removePreference(context, FILENAME, AppConstants.MachineNames.ADDITION);
        removePreference(context, FILENAME, AppConstants.MachineNames.SUBTRACTION);
        removePreference(context, FILENAME, AppConstants.MachineNames.MULTIPLICATION);
        removePreference(context, FILENAME, AppConstants.MachineNames.DIVISION);
    }

    public void setStringPrefrence(String key, String value) {
        putStringPreference(context, FILENAME, key, value);
    }

    public String getStringPrefrence(String key) {
        return getStringPreference(context, FILENAME, key);
    }

    public void setIntegerPrefrence(String key, int value) {
        putIntegerPreference(context, FILENAME, key, value);
    }

    public int getIntegerPrefrence(String key) {
        return getIntegerPreference(context, FILENAME, key);
    }

    public void setBooleanPrefrence(String Key, boolean value) {
        putBooleanPreference(context, FILENAME, Key, value);
    }

    public boolean getBooleanPrefrence(String Key) {
        return getBooleanPreference(context, FILENAME, Key);
    }

}
