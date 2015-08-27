package com.cui.trypro.activitys;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.cui.trypro.BaseActivity;
import com.cui.trypro.R;
import com.cui.trypro.adapter.Animation_groups_adapter;
import com.cui.trypro.utils.RecyclerUtils;
import com.cui.trypro.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * material 控件使用
 */
public class Animation_Groups__Activity extends BaseActivity {
    private final String TAG = "TAG";
    @InjectView(R.id.recycler_view)
    RecyclerView recyclerView;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;


    private final int TOAST = 0;

    private List<String> list = new ArrayList<String>();
    private Animation_groups_adapter adapter;
    private Context mContext;

    private Handler hand = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case TOAST:
                    Utils.showSnackbar(recyclerView, msg.obj + "", "");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_groups_act);
        ButterKnife.inject(this);
        mContext = this;
        initHeader();
        initView();
    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new Animation_groups_adapter(initData());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerUtils.RecyclerItemClickListener(this, new RecyclerUtils.RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        hand.obtainMessage(TOAST, "click:" + position).sendToTarget();

                        break;
                    case 1:
                        break;
                }
            }
        }));
    }

    private void initHeader() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，设置到Toolbar上则不会显示
        mCollapsingToolbarLayout.setTitle("Material_Library");
        //通过CollapsingToolbarLayout修改字体颜色
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.GREEN);//设置收缩后Toolbar上字体的颜色
    }

    private List<String> initData() {
        list.add("Toolbar随列表滚动");
        list.add("anroid属性动画使用");
        return list;
    }
}
