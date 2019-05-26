package com.yuan.kanmeitu.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yuan.kanmeitu.MainActivity;
import com.yuan.kanmeitu.R;
import com.yuan.kanmeitu.utils.SharedPreferencesUtil;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText username;
    private EditText password;
    private Button login;
    private SharedPreferencesUtil sharedPreferencesUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        login.setOnClickListener(this);

        sharedPreferencesUtil = SharedPreferencesUtil.getInstance(getApplicationContext());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                login();
                break;
        }
    }

    private void login() {
        String name = username.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
            return;
        }
        String pwd = password.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        //登录成功
        if (name.equals("123456") && pwd.equals("123456")) {
            sharedPreferencesUtil.setLogin(true);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "账号或者密码错误", Toast.LENGTH_SHORT).show();
        }
    }
}
