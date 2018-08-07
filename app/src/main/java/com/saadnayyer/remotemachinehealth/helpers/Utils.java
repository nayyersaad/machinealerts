package com.saadnayyer.remotemachinehealth.helpers;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.saadnayyer.remotemachinehealth.R;
import com.saadnayyer.remotemachinehealth.activities.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class Utils {

    public static String getCurrentDateTime(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat(AppConstants.DATE_TIME_FORMAT);
        String strDate = mdformat.format(calendar.getTime());
        return strDate;
    }

    public static void sendNotification(Context context, String title, String description) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        .setContentTitle(title)
                        .setContentText(description)
                        .setAutoCancel(false);

        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, builder.build());
    }

    public static int getRandomNumber() {
        Random rand = new Random();
        int n = rand.nextInt(4) + 1;
        return n;
    }

}
