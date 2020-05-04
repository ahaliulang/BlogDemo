package me.tandeneck.blogdemo.service.receiver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import me.tandeneck.blogdemo.R;

/**
 * author:tdn
 * time:2020/5/4
 * description:通过线程模拟下载任务的服务
 */
public class MsgService extends Service {

    private static final String TAG = "MsgService";

    private volatile boolean mCancel = false;
    private int mProgress = 0;
    private LocalBroadcastManager mLocalBroadcastManager;

    @Override
    public void onCreate() {
        super.onCreate();
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        //创建服务后开启下载任务
        startDownload();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void startDownload() {
        final Intent intent = new Intent(getString(R.string.receiver_action));
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!mCancel && mProgress < 100) {
                    mProgress += 20;
                    intent.putExtra(ReceiverActivity.EXTRAS_PROGRESS, mProgress);
                    mLocalBroadcastManager.sendBroadcast(intent);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "run: " + mProgress);
                }
                if (mProgress >= 100) {
                    intent.putExtra(ReceiverActivity.EXTRAS_FINISH, true);
                    mLocalBroadcastManager.sendBroadcast(intent);
                }
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //停止服务后取消掉下载任务
        mCancel = true;
    }
}
