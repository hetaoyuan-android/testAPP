package com.yuan.eventbustest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

public class SecondeActivity extends AppCompatActivity {

    private Button mBtnSecode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seconde);
        mBtnSecode = findViewById(R.id.btn_seconde);
        jumpActivity();
    }

    private void jumpActivity() {
        mBtnSecode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new MessageEvent("我是第二个Activty"));
                finish();
            }
        });
    }
}
