package com.yuan.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private ProgressView commonProgressView1, commonProgressView2;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.name);
        textView.setText("ddd");

        commonProgressView1 = findViewById(R.id.progress1);
        commonProgressView1.setValue("30");
        commonProgressView1.setCurrentPercent(0.3f);

        commonProgressView2 = findViewById(R.id.progress2);
        commonProgressView2.setValue("60");
        commonProgressView2.setCurrentPercent(0.6f);
    }

    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }


}
