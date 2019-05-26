package com.yuan.xmpp.utils;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuan.xmpp.R;

import java.util.ArrayList;
import java.util.List;

public class ToolBarUtil {

    private List<TextView> mTextView = new ArrayList<>();

    public void createToolBar(LinearLayout container, String[] toolbarTitleArr, int[] iconArr) {
        for (int i = 0; i < toolbarTitleArr.length; i++) {
            TextView textView = (TextView) View.inflate(container.getContext(), R.layout.inflate_toolbar_btn, null);
            textView.setText(toolbarTitleArr[i]);
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, iconArr[i], 0, 0);
            int width = 0;
            int height = LinearLayout.LayoutParams.MATCH_PARENT;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
            //设置weight属性
            params.weight = 1;
            container.addView(textView, params);
            //保存textview到集合中
            mTextView.add(textView);
            //设置点击事件
            final int finalI = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //不同模块之间传值需要接口回调
                    //3.需要传值的地方，用接口对象调用接口方法
                    onToolBarClickListener.onToolBarClick(finalI);
                }
            });
        }
    }

    public void changeColor(int position) {
        //还原所有颜色
        for (TextView tv : mTextView) {
            tv.setSelected(false);
        }
        //通过设置setSelected控制为选中效果
        mTextView.get(position).setSelected(true);
    }

    //1.创建接口和方法
    public interface OnToolBarClickListener {
        void onToolBarClick(int position);
    }
    //2.定义接口变量
    OnToolBarClickListener onToolBarClickListener;

    //4.暴露一个公共的方法

    public void setOnToolBarClickListener(OnToolBarClickListener onToolBarClickListener) {
        this.onToolBarClickListener = onToolBarClickListener;
    }
}
