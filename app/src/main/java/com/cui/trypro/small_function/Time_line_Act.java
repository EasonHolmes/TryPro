package com.cui.trypro.small_function;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cui.trypro.BaseActivity;
import com.cui.trypro.R;
import com.cui.trypro.widget.CirclesView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by cuiyang on 15/8/27.
 */
public class Time_line_Act extends BaseActivity {
    @InjectView(R.id.base_list)
    RecyclerView baseList;


    private Context mContext;
    private List<String> list = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baselist);
        ButterKnife.inject(this);
        super.initToolbar("时间轴", true);
        mContext = this;

        initView();

    }


    private void initView() {
        initData();
        baseList.setLayoutManager(new LinearLayoutManager(mContext));
        baseList.setAdapter(new SimpleAdapter());

    }
    private void initData() {
       list.add("#BBBBBB");
       list.add("#ff368adb");
       list.add("#252c68");
       list.add("#B847FF");
       list.add("#F27474");
       list.add("#11B7D1");
       list.add("#ff1692d1");
       list.add("#E1E0DE");
       list.add("#FFC37DFF");
    }

    class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.Viewholder> {
        GradientDrawable gd;

        public SimpleAdapter() {
//            int strokeWidth = 1; // 3dp 边框宽度
//            int roundRadius = 50; // 8dp 圆角半径
//            int strokeColor = Color.parseColor("#ff9000");//边框颜色
//            int fillColor = Color.parseColor("#ff9000");//内部填充颜色
//
//            gd = new GradientDrawable();//创建drawable
//            gd.setColor(fillColor);
//            gd.setCornerRadius(roundRadius);
//            gd.setStroke(strokeWidth, strokeColor);

        }

        @Override
        public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_time_line, parent, false);
            return new Viewholder(v);
        }

        @Override
        public void onBindViewHolder(Viewholder holder, int position) {
//            holder.itemTimeCircle.setImageDrawable(gd);
            holder.itemTimeCircle.setColor(Color.parseColor(list.get(position)));
            if(position == 0){
                holder.itemTimeTopLine.setVisibility(View.INVISIBLE);
            }else if(position == getItemCount()-1){
                holder.itemTimeBottomeLine.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemCount() {
            return list.size();
        }


        public class Viewholder extends RecyclerView.ViewHolder {
            View itemTimeTopLine;
            CirclesView itemTimeCircle;
            View itemTimeBottomeLine;

            public Viewholder(View itemView) {
                super(itemView);
                itemTimeCircle = (CirclesView) itemView.findViewById(R.id.item_time_circle);
                itemTimeTopLine = itemView.findViewById(R.id.item_time_top_line);
                itemTimeBottomeLine = itemView.findViewById(R.id.item_time_bottome_line);

            }
        }
    }
}
