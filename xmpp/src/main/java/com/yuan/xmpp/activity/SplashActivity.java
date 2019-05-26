package com.yuan.xmpp.activity;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yuan.xmpp.R;
import com.yuan.xmpp.utils.ThreadUtils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //停留三秒到登录界面
        ThreadUtils.runInThread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(3000);
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
