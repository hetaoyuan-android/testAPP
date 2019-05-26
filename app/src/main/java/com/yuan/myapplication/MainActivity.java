package com.yuan.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yuan.myapplication.call.CallPersimission;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt_call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SystemClock.sleep(30 * 1000);
        bt_call = findViewById(R.id.bt_call);
        bt_call.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_call:
                Intent intent = new Intent(MainActivity.this, CallPersimission.class);
                startActivity(intent);
                break;
        }
    }
}
