package com.example.vadamar.Model;

import android.content.Context;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.vadamar.Dashboard;

public class NotificationHelper {
    public static void displayNotification(Context context, String title, String body){

//        Intent intent = new Intent(context,MainPage.class);
//
//        PendingIntent pendingIntent = PendingIntent.getActivity(context,100,intent,PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, Dashboard.CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
//                .setContentIntent(pendingIntent)
//                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(1,mBuilder.build());
    }
}
