package com.yuan.eventbustest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    private Button mEventClick;
    private TextView mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEventClick = findViewById(R.id.btn_event);
        mResult = findViewById(R.id.tv_result);
        mResult.setText("我是第一个activity");
        EventBus.getDefault().register(this);
        jumpActivity();

    }

    private void jumpActivity() {
        mEventClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SecondeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void event(MessageEvent messageEvent) {
        mResult.setText(messageEvent.getMessage());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
