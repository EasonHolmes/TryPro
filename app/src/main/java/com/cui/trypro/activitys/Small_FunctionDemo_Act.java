package com.cui.trypro.activitys;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.cui.trypro.BaseActivity;
import com.cui.trypro.R;
import com.cui.trypro.small_function.SVG_act;
import com.cui.trypro.small_function.Time_line_Act;
import com.cui.trypro.utils.RecyclerUtils;
import com.cui.trypro.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by cuiyang on 15/8/27.
 */
public class Small_FunctionDemo_Act extends BaseActivity {
    @InjectView(R.id.base_list)
    RecyclerView listbase;


    private Context mContext;
    private List<String> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baselist);
        ButterKnife.inject(this);
        mContext = this;
        super.initToolbar("小功能Demo", true);


        initView();
    }

    private void initView() {
        initData();
        listbase.setLayoutManager(new LinearLayoutManager(mContext));
        listbase.setAdapter(new SimpleAdapter());
        listbase.addOnItemTouchListener(new RecyclerUtils.RecyclerItemClickListener(mContext, new RecyclerUtils.RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        Utils.nextAct(mContext, Time_line_Act.class);
                        break;
                    case 1:
                        Utils.nextAct(mContext, SVG_act.class);
                        break;
                }
            }
        }));
    }

    private void initData() {
        list.add("时间线(RecyclerView)");
        list.add("svg炫图");
    }


    class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.Viewholder> {


        @Override
        public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_animation_group, parent, false);

            return new Viewholder(v);
        }

        @Override
        public void onBindViewHolder(Viewholder holder, int position) {
            holder.txt_contnet.setText(list.get(position));
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
            public TextView txt_contnet;

            public Viewholder(View itemView) {
                super(itemView);
                txt_contnet = (TextView) itemView.findViewById(R.id.tvComment);
            }
        }
    }

}
