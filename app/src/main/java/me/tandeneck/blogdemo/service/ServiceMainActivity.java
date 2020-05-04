package me.tandeneck.blogdemo.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import me.tandeneck.blogdemo.BaseActivity;
import me.tandeneck.blogdemo.R;
import me.tandeneck.blogdemo.service.receiver.ReceiverActivity;

public class ServiceMainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "ServiceMainActivity";

    private Button mStartBtn, mStopBtn, mBindBtn, mUnbindBtn,
            mStartForegroundBtn, mStopForegroundBtn, mBroadcastBtn;


    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: ");
            //这里的IBinder涉及到Binder机制，有兴趣的同学可以去详细了解下
            //可以简单地理解为在 Service中 onBinder 返回的
            ((MyService.MyBinder) service).getService().startDownloadApk();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected: ");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_main);
        mStartBtn = findViewById(R.id.start_btn);
        mStopBtn = findViewById(R.id.stop_btn);
        mBindBtn = findViewById(R.id.bind_btn);
        mUnbindBtn = findViewById(R.id.unbind_btn);
        mStartForegroundBtn = findViewById(R.id.start_foreground_btn);
        mStopForegroundBtn = findViewById(R.id.stop_foreground_btn);
        mBroadcastBtn = findViewById(R.id.broadcast_btn);

        mStartBtn.setOnClickListener(this);
        mStopBtn.setOnClickListener(this);
        mBindBtn.setOnClickListener(this);
        mUnbindBtn.setOnClickListener(this);
        mStartForegroundBtn.setOnClickListener(this);
        mStopForegroundBtn.setOnClickListener(this);
        mBroadcastBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_btn:
                startService(new Intent(this, MyService.class));
                break;
            case R.id.stop_btn:
                stopService(new Intent(this, MyService.class));
                break;
            case R.id.bind_btn:
                bindService(new Intent(this, MyService.class), mConnection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind_btn:
                unbindService(mConnection);
                break;
            case R.id.start_foreground_btn:
                startService(new Intent(this, ForegroundService.class));
                break;
            case R.id.stop_foreground_btn:
                stopService(new Intent(this, ForegroundService.class));
                break;
            case R.id.broadcast_btn:
                startActivity(new Intent(this, ReceiverActivity.class));
                break;
            default:
                break;
        }
    }


}
