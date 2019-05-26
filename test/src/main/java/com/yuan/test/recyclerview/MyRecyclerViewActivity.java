package com.yuan.test.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yuan.test.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyRecyclerViewActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.btn_add)
    Button mBtnAdd;
    @BindView(R.id.btn_delete)
    Button mBtnDelete;
    @BindView(R.id.btn_list)
    Button mBtnList;
    @BindView(R.id.btn_grid)
    Button mBtnGrid;
    @BindView(R.id.btn_flow)
    Button mBtnFlow;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private ArrayList<String> datas;
    private MyRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recycler_view);
        ButterKnife.bind(this);
        initView();
        initClick();
        adapter= new MyRecyclerAdapter(MyRecyclerViewActivity.this,datas);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MyRecyclerViewActivity.this,LinearLayoutManager.VERTICAL,false));
        adapter.setOnItemClickListener(new MyRecyclerAdapter.OnItemClickListenrt() {
            @Override
            public void onItemClick(View view, String data) {
                Toast.makeText(MyRecyclerViewActivity.this,"data==" + data, Toast.LENGTH_SHORT).show();
            }
        });

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initView() {
        datas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            datas.add("content_" + i);
        }
    }


    private void initClick() {
        mBtnAdd.setOnClickListener(this);
        mBtnDelete.setOnClickListener(this);
        mBtnList.setOnClickListener(this);
        mBtnGrid.setOnClickListener(this);
        mBtnFlow.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                adapter.addData(0,"new_content");
                mRecyclerView.scrollToPosition(0);
                break;
            case R.id.btn_delete:
                adapter.removeData(0);
                break;
            case R.id.btn_list:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(MyRecyclerViewActivity.this,LinearLayoutManager.VERTICAL,false));
                break;
            case R.id.btn_grid:
                mRecyclerView.setLayoutManager(new GridLayoutManager(MyRecyclerViewActivity.this,3,GridLayoutManager.VERTICAL,false));
                break;
            case R.id.btn_flow:
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.HORIZONTAL));
                break;
        }
    }
}
