package com.cui.trypro.small_function;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cui.trypro.BaseActivity;
import com.cui.trypro.R;

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
    private List<Integer> list = new ArrayList<Integer>();


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
        list.add(R.color.toolbar_background2);
        list.add(R.color.black);
        list.add(R.color.cardview_shadow_start_color);
        list.add(R.color.red_btn_bg_pressed_color);
        list.add(R.color.material_blue_500);
        list.add(R.color.material_blue_grey_80);
        list.add(R.color.material_blue_500);
        list.add(R.color.material_blue_600);
        list.add(R.color.material_blue_grey_900);
        list.add(R.color.material_blue_grey_800);
        list.add(R.color.switch_thumb_material_dark);
        list.add(R.color.material_deep_teal_20);
        list.add(R.color.secondary_text_default_material_light);
    }

    class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.Viewholder> {
        GradientDrawable gd;

        public SimpleAdapter() {
            int strokeWidth = 1; // 3dp 边框宽度
            int roundRadius = 50; // 8dp 圆角半径
            int strokeColor = Color.parseColor("#ff9000");//边框颜色
            int fillColor = Color.parseColor("#ff9000");//内部填充颜色

            gd = new GradientDrawable();//创建drawable
            gd.setColor(fillColor);
            gd.setCornerRadius(roundRadius);
            gd.setStroke(strokeWidth, strokeColor);

        }

        @Override
        public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_time_line, parent, false);
            return new Viewholder(v);
        }

        @Override
        public void onBindViewHolder(Viewholder holder, int position) {
            holder.itemTimeCircle.setImageDrawable(gd);
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
            ImageView itemTimeCircle;
            View itemTimeBottomeLine;

            public Viewholder(View itemView) {
                super(itemView);
                itemTimeCircle = (ImageView) itemView.findViewById(R.id.item_time_circle);
                itemTimeTopLine = itemView.findViewById(R.id.item_time_top_line);
                itemTimeBottomeLine = itemView.findViewById(R.id.item_time_bottome_line);

            }
        }
    }
}
