package com.yuan.test.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuan.test.R;

import java.util.ArrayList;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {


    private final Context context;
    private final ArrayList<String> datas;

    public MyRecyclerAdapter(MyRecyclerViewActivity contex, ArrayList<String> datas) {
        this.context = contex;
        this.datas = datas;
    }

    /**
     * Listview中getview创建view和viewholder
     *
     * @param viewGroup
     * @param i
     * @return
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = View.inflate(context, R.layout.item_recyclerview, null);
        return new MyViewHolder(itemView);
    }

    /**
     * 数据和view的绑定
     *
     * @param holder
     * @param i
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        //根据位置得到对应的数据
        String data = datas.get(i);
        holder.tv_title.setText(data);
    }

    /**
     * 得到总条数
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return datas.size();
    }

    /**
     *  添加数据
     * @param i
     * @param data
     */
    public void addData(int i, String data) {
        datas.add(i,data);
        notifyItemInserted(i);
    }

    /**
     *  移除操作
     * @param i
     */
    public void removeData(int i) {
        datas.remove(i);
        notifyItemRemoved(i);

    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        private ImageView iv_icon;
        private TextView tv_title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_icon = itemView.findViewById(R.id.iv_icon);
            tv_title = itemView.findViewById(R.id.tv_title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(context, "data == " + datas.get(getLayoutPosition()), Toast.LENGTH_SHORT).show();
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(view, datas.get(getLayoutPosition()));
                    }
                }
            });
            //对图片的点击事件，再写一个接口
            iv_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }

    /**
     * 接口回调点击事件
     */
    public interface OnItemClickListenrt {
        /**
         * 点击
         * @param view 点击的视图
         * @param data 点击得到的数据
         */
        public void onItemClick(View view, String data);
    }
    private OnItemClickListenrt onItemClickListener;

    /**
     * 设置某条的监听
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListenrt onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
