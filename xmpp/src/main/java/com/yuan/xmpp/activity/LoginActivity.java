package com.yuan.xmpp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yuan.xmpp.R;
import com.yuan.xmpp.service.ImService;
import com.yuan.xmpp.utils.ThreadUtils;
import com.yuan.xmpp.utils.ToastUtils;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

public class LoginActivity extends AppCompatActivity {

    private EditText mEtUserName;
    private EditText mEtPassword;
    private Button mBtnLogin;

    public static final String HOST = "192.168.1.35";
    public static final int PORT = 5222;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initListener();
    }

    private void initListener() {
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userName = mEtUserName.getText().toString();
                final String password = mEtPassword.getText().toString();
                //判断用户名是否为空
                if (TextUtils.isEmpty(userName)) {
                    mEtUserName.setError("用户名不能为空");
                    return;
                }
                //判断密码是否为空
                if (TextUtils.isEmpty(password)) {
                    mEtPassword.setError("密码不能为空");
                    return;
                }

                ThreadUtils.runInThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ConnectionConfiguration config = new ConnectionConfiguration(HOST, PORT);
                            //额外配置
                            config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
                            config.setDebuggerEnabled(true);  //开启调试模式

                            //开始连接
                            XMPPConnection connection = new XMPPConnection(config);
                            connection.connect();
                            //连接登录
                            connection.login(userName, password);
                            //連接成功
                                    ToastUtils.showToastSafe(LoginActivity.this, "登录成功");
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                            ImService.connection = connection;
                        } catch (XMPPException e) {
                            e.printStackTrace();
                            ThreadUtils.runInUIThread(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtils.showToastSafe(LoginActivity.this, "登录失败");
                                }
                            });
                        }
                    }
                });


            }
        });
    }

    private void initView() {
        mEtUserName = findViewById(R.id.et_username);
        mEtPassword = findViewById(R.id.et_password);
        mBtnLogin = findViewById(R.id.btn_login);
    }
}
