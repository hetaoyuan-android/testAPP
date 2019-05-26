package com.yuan.kanmeitu.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yuan.kanmeitu.MainActivity;
import com.yuan.kanmeitu.R;
import com.yuan.kanmeitu.utils.SharedPreferencesUtil;

public class SplashActivity extends AppCompatActivity {


    private SharedPreferencesUtil sharedPreferencesUtil;

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            next();
        }
    };

    private void next() {
        Intent intent = null;
        if (sharedPreferencesUtil.isLogin()) {
            intent = new Intent(this, MainActivity.class);
        } else {
            intent = new Intent(this, LoginActivity.class);
        }
        startActivity(intent);

        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sharedPreferencesUtil = SharedPreferencesUtil.getInstance(getApplicationContext());

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(0);
            }
        }, 3000);
    }
}
