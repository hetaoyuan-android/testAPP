package com.yuan.myapplication.shradiobutton;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.yuan.myapplication.R;

public class TwoActivity extends AppCompatActivity {

    private RadioGroup mRgMain;
    private RadioButton man;
    private RadioButton women;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        mRgMain = findViewById(R.id.rg_main);
        man = findViewById(R.id.man);
        women = findViewById(R.id.women);

        mRgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Log.i("hetao","mesgadlsdksl");
                switch (i) {
                    case R.id.man:
                        if (man.isChecked()){
                            getSharedPreferences("info", MODE_PRIVATE).edit().putInt("abc", 10).commit();
                        }
                        break;
                    case R.id.women:
                        if (women.isChecked()){
                            getSharedPreferences("info", MODE_PRIVATE).edit().putInt("abc", 11).commit();
                        }
                        break;
                }
            }
        });
        readAccount();
    }


    private void readAccount() {
        int s = getSharedPreferences("info", MODE_PRIVATE).getInt("abc", 10);
        Log.i("hetao","s" + s);
        if (s == 10) {
            mRgMain.check(R.id.man);
        }
        if (s == 11) {
            mRgMain.check(R.id.women);
        }
    }


    public void onClick(View view) {

        finish();
    }
}
