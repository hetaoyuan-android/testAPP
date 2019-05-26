package com.yuan.myapplication.shradiobutton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yuan.myapplication.R;

public class OneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
    }

    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setClass(this, TwoActivity.class);
        startActivity(intent);
    }
}
