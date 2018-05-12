package com.ahaliulang.seekbardemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SeekBar mSeekbar;
    private TextView mTextViewCurrent;
    private TextView mTextViewMax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSeekbar = findViewById(R.id.seekbar_custom);
        mTextViewCurrent = findViewById(R.id.tv_current);
        mTextViewMax = findViewById(R.id.tv_max);
        mTextViewMax.setText("max:" + mSeekbar.getMax());
        mSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mTextViewCurrent.setText("current:" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this,"startTrackingTouch",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this,"stopTrackingTouch",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
