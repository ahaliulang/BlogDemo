package me.tandeneck.blogdemo.handlerthread;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import me.tandeneck.blogdemo.BaseActivity;
import me.tandeneck.blogdemo.R;

public class HandlerThreadActivity extends BaseActivity {

    private static final String TAG = "HandlerThreadActivity";

    private Button mStartBtn;
    private Handler mHandler;
    private HandlerThread mHandlerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_thread);
        mStartBtn = findViewById(R.id.start_btn);

        mHandlerThread = new HandlerThread("THREAD_NAME");
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper());

        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, Thread.currentThread().getId() + " " + (Looper.myLooper() == Looper.getMainLooper()) + " 任务：" + this.hashCode());
                        SystemClock.sleep(3000);
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandlerThread.quit();
    }
}
