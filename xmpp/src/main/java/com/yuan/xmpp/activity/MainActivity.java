package com.yuan.xmpp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuan.xmpp.R;
import com.yuan.xmpp.fragment.ContactsFragment;
import com.yuan.xmpp.fragment.SessionFragment;
import com.yuan.xmpp.utils.ToolBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {
    @InjectView(R.id.main_tv_title)
    TextView mainTvTitle;
    @InjectView(R.id.main_viewpager)
    ViewPager mainViewpager;
    @InjectView(R.id.main_bottom)
    LinearLayout mainBottom;

    private List<Fragment> mFragment = new ArrayList<>();
    ToolBarUtil toolBarUtil;
    private String[] toolbarTitleArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initData();
        initListener();
    }

    private void initListener() {
        mainViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                //修改颜色
                toolBarUtil.changeColor(i);
                //修改title
                mainTvTitle.setText(toolbarTitleArr[i]);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        toolBarUtil.setOnToolBarClickListener(new ToolBarUtil.OnToolBarClickListener() {
            @Override
            public void onToolBarClick(int position) {
                mainViewpager.setCurrentItem(position);
            }
        });
    }

    private void initData() {
        //viewpager--> view -->pagerAdapter
        //viewpager-->fragment -->fragmentPagerAdapter -->fragement 数量少
        //viewpager-->fragment -->fragmentStatePagerAdaoter
        mFragment.add(new SessionFragment());
        mFragment.add(new ContactsFragment());
        mainViewpager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        //底部按鈕
        toolBarUtil = new ToolBarUtil();
        //文字内容
        toolbarTitleArr = new String[]{"会话", "联系人"};
        //图标
        int[] iconArr = {R.drawable.selector_meassage, R.drawable.selector_selfinfo};
        toolBarUtil.createToolBar(mainBottom, toolbarTitleArr, iconArr);
        toolBarUtil.changeColor(0);
    }

    class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fragmentPagerAdapter) {
            super(fragmentPagerAdapter);
        }

        @Override
        public Fragment getItem(int i) {
            return mFragment.get(i);
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

}
