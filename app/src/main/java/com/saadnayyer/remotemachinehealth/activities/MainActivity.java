package com.saadnayyer.remotemachinehealth.activities;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.saadnayyer.remotemachinehealth.R;
import com.saadnayyer.remotemachinehealth.databinding.ActivityMainBinding;
import com.saadnayyer.remotemachinehealth.fragments.DashboardFragment;
import com.saadnayyer.remotemachinehealth.fragments.MainFragment;
import com.saadnayyer.remotemachinehealth.helpers.AppConstants;
import com.saadnayyer.remotemachinehealth.helpers.BasePreferenceHelper;
import com.saadnayyer.remotemachinehealth.helpers.DBOperations;
import com.saadnayyer.remotemachinehealth.helpers.Titlebar;
import com.saadnayyer.remotemachinehealth.helpers.UIHelper;
import com.saadnayyer.remotemachinehealth.helpers.Utils;
import com.saadnayyer.remotemachinehealth.interfaces.SimpleDialogActionListener;
import com.saadnayyer.remotemachinehealth.services.AppService;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MainFragment mainFragment;
    private int mainFrameLayoutID;
    private View mainFrameLayout;
    BasePreferenceHelper preferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        preferenceHelper = new BasePreferenceHelper(this);

        mainFrameLayoutID = binding.mainFrame.getId();
        mainFrameLayout = binding.mainFrame;

        stopService(new Intent(getBaseContext(), AppService.class));
        replaceFragment(DashboardFragment.Instance(), DashboardFragment.class.getSimpleName(), true, false);
//        preferenceHelper.setBooleanPrefrence(AppConstants.MachineNames.DIVISION, true);

//        DBOperations.getInstance(this).insertRecord(1, AppConstants.MachineStatuses.DOWN, "", Utils.getCurrentDateTime());
//        UIHelper.showToast(this, Utils.getCurrentDateTime() + "", Toast.LENGTH_LONG);
}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        startService(new Intent(getBaseContext(), AppService.class));
    }

    @Override
    public void onBackPressed() {
        UIHelper.hideSoftKeyboard(this);
        if (getSupportFragmentManager().getBackStackEntryCount() > 1)
            getSupportFragmentManager().popBackStack();
        else {
            closeApp();
        }
    }

    public BasePreferenceHelper getPreferenceHelper() {
        return preferenceHelper;
    }

    public Titlebar getTitlebar() {
        return binding.titlebar;
    }

    public View getMainFrameLayout() {
        return binding.mainFrame;
    }

    public void showLoader() {
        binding.progressBarContainer.setVisibility(View.VISIBLE);
    }

    public void hideLoader() {
        binding.progressBarContainer.setVisibility(View.GONE);
    }

    public void replaceFragment(MainFragment frag, String tag, boolean isAddToBackStack, boolean animate) {
        mainFragment = frag;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (animate) {
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
        }
        transaction.replace(mainFrameLayoutID, frag, tag);

        if (isAddToBackStack) {
            transaction.addToBackStack(null).commit();
        } else {
            transaction.commit();
        }
    }

    public void replaceFragmentWithClearBackStack(MainFragment frag, String tag, boolean isAddToBackStack, boolean animate) {
        clearBackStack();
        mainFragment = frag;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (animate) {
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
        }
        transaction.replace(mainFrameLayoutID, frag, tag);

        if (isAddToBackStack) {
            transaction.addToBackStack(null).commit();
        } else {
            transaction.commit();
        }
    }

    public void clearBackStack() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
            fragmentManager.popBackStack();
        }
    }

    public void closeApp() {
        UIHelper.showSimpleDialog(
                this, 0, getResources().getString(R.string.close_app), getResources().getString(R.string.do_you_want_close_app), getResources().getString(R.string.yes), getResources().getString(R.string.no), false, false,
                new SimpleDialogActionListener() {
                    @Override
                    public void onDialogActionListener(DialogInterface dialog, int which, boolean positive, boolean logout) {
                        if (positive) {
                            dialog.dismiss();
                            finish();
                        } else {
                            dialog.dismiss();
                        }
                    }
                }
        );
    }

}
