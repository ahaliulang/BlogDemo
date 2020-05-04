package me.tandeneck.blogdemo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * author:tandeneck
 * time:2020/5/4
 * description:懒得在每个 Activity 再写一遍
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(getClass().getSimpleName());
    }
}
