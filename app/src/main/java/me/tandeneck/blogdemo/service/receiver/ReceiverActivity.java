package me.tandeneck.blogdemo.service.receiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import me.tandeneck.blogdemo.BaseActivity;
import me.tandeneck.blogdemo.R;
import me.tandeneck.blogdemo.service.MyService;

public class ReceiverActivity extends BaseActivity {

    public static final String EXTRAS_PROGRESS = "progress";
    public static final String EXTRAS_FINISH = "finish";

    private MyReceiver mMyReceiver;
    private IntentFilter mIntentFilter;
    private TextView mProgressTv;
    private Button mStartBtn, mStopBtn;
    private LocalBroadcastManager mLocalBroadcastManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);
        mProgressTv = findViewById(R.id.progress_tv);
        mStartBtn = findViewById(R.id.start_btn);
        mStopBtn = findViewById(R.id.stop_btn);

        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开启服务
                startService(new Intent(ReceiverActivity.this, MsgService.class));
            }
        });

        mStopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //停止服务
                stopService(new Intent(ReceiverActivity.this, MsgService.class));
            }
        });

        //注册广播
        mIntentFilter = new IntentFilter(getString(R.string.receiver_action));
        mMyReceiver = new MyReceiver();
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        mLocalBroadcastManager.registerReceiver(mMyReceiver, mIntentFilter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销广播
        mLocalBroadcastManager.unregisterReceiver(mMyReceiver);
    }

    public class MyReceiver extends BroadcastReceiver {
        private static final String TAG = "MyReceiver";
        @Override
        public void onReceive(Context context, Intent intent) {
            int progress = intent.getIntExtra(EXTRAS_PROGRESS, 0);
            Log.d(TAG, "onReceive: "+progress);
            mProgressTv.setText("当前进度：" + progress);
            if (intent.getBooleanExtra(EXTRAS_FINISH, false)) {
                mProgressTv.setText("下载完成");
                stopService(new Intent(ReceiverActivity.this, MsgService.class));
            }
        }
    }
}
