package com.saadnayyer.remotemachinehealth.helpers;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.saadnayyer.remotemachinehealth.interfaces.SimpleDialogActionListener;

public class UIHelper {
    public static void showSnackbar(View view, String message, int length) {
        Snackbar.make(view, message.trim(), length).show();
    }

    public static void showToast(Context context, String message, int length) {
        Toast.makeText(context, message.trim(), length).show();
    }

    public static void hideSoftKeyboard(Activity activity) {
        if (activity.getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public static void showSimpleDialog(final Context context, int icon, String title, String message, String positiveButton, String negativeButton, boolean cancelable, final boolean logout, final SimpleDialogActionListener simpleDialogActionListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(icon)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(cancelable)
                .setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (logout) {
                            simpleDialogActionListener.onDialogActionListener(dialog, which, true, true);
                        } else {
                            simpleDialogActionListener.onDialogActionListener(dialog, which, true, false);
                        }
                    }
                })
                .setNegativeButton(negativeButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        simpleDialogActionListener.onDialogActionListener(dialog, which, false, false);
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void showSimpleDialog(final Context context, int icon, String title, String message, String positiveButton, String negativeButton, String neutralButton, boolean cancelable, final boolean neutral, final SimpleDialogActionListener simpleDialogActionListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(icon)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(cancelable)
                .setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        simpleDialogActionListener.onDialogActionListener(dialog, which, true, false);
                    }
                })
                .setNegativeButton(negativeButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        simpleDialogActionListener.onDialogActionListener(dialog, which, false, false);
                    }
                })
                .setNeutralButton(neutralButton, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        simpleDialogActionListener.onDialogActionListener(dialog, which, false, neutral);
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
