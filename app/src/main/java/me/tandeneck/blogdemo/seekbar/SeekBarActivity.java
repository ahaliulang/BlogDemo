package me.tandeneck.blogdemo.seekbar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import me.tandeneck.blogdemo.BaseActivity;
import me.tandeneck.blogdemo.R;

public class SeekBarActivity extends BaseActivity {

    private SeekBar mSeekbar;
    private TextView mTextViewCurrent;
    private TextView mTextViewMax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seekbar);
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
//                Toast.makeText(SeekBarActivity.this,"startTrackingTouch",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
//                Toast.makeText(SeekBarActivity.this,"stopTrackingTouch",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
