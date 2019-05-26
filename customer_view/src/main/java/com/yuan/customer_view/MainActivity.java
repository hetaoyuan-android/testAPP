package com.yuan.customer_view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView lv_one;
    private ListView lv_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv_one = findViewById(R.id.lv_one);
        lv_two = findViewById(R.id.lv_two);
        String[] strings = {"1","2","3","3","3","3","3","3","3","3","3","3"};
        ArrayAdapter<String>  adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,strings);
        lv_one.setAdapter(adapter);
        String[] string2 = {"A","S","D","F","G","F","W","E","F","F","E","E"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,string2);
        lv_two.setAdapter(adapter2);

    }
}
