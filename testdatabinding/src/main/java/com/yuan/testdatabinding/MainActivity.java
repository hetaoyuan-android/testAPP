package com.yuan.testdatabinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewDataBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        user = new User("hetao","123456");
    }
}
