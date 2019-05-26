package com.yuan.kanmeitu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yuan.kanmeitu.activity.LoginActivity;
import com.yuan.kanmeitu.utils.SharedPreferencesUtil;

public class MainActivity extends AppCompatActivity {

    private SharedPreferencesUtil sharedPreferencesUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferencesUtil = SharedPreferencesUtil.getInstance(getApplicationContext());
    }

    public void onLogoutClick(View view) {
        sharedPreferencesUtil.setLogin(false);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
