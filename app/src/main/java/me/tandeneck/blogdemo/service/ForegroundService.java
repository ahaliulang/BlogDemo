package me.tandeneck.blogdemo.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import me.tandeneck.blogdemo.R;

public class ForegroundService extends Service {

    //Channel ID 必须保证唯一
    private static final String CHANNEL_ID = "me.tandeneck.blogdemo.service.foreground";
    private static final String CHANNEL_NAME = "foreground";
    private static final int ONGOING_NOTIFICATION_ID = 0x1;

    @Override
    public void onCreate() {
        super.onCreate();
        //点击通知跳转的Activity
        Intent notificationIntent = new Intent(this, NotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("标题")
                .setContentText("内容")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true) // 用户触摸时，自动关闭
                .setOngoing(false)//设置处于运行状态
                .build();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_LOW);
            //向系统注册通知渠道，注册后不能改变重要性以及其他通知行为
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }
        //提供给 startForeground() 的整型 ID 不得为 0。
        startForeground(ONGOING_NOTIFICATION_ID, notification);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
    }
}
