package com.cui.trypro;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cui.trypro.animation.CenterActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {


    @InjectView(R.id.main_list)
    ListView mainList;
    private String[] animation = {"ReboundActivity", "CenterActivity", "ListAnimation", "ListAnimation", "ListAnimation", "ListAnimation", "ListAnimation", "ListAnimation", "ListAnimation", "ListAnimation", "ListAnimation", "ListAnimation", "ListAnimation", "ListAnimation"};
    private String[] animation2 = {"ListAnimation2", "ListAnimation2", "ListAnimation2", "ListAnimation2", "ListAnimation2", "ListAnimation2", "ListAnimation2", "ListAnimation2", "ListAnimation2", "ListAnimation", "ListAnimation", "ListAnimation", "ListAnimation", "ListAnimation"};
    private Context mContext;
    private int lastItem;

    private boolean isIn = true;
    private View v;
    private final int UPDATE = 1;
    private ArrayAdapter adapter;
    private int visiPostion = 0;
    private Handler hand = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE:
                    adapter.notifyDataSetChanged();
                    v.setVisibility(View.GONE);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        mContext = this;
        super.initToolbar(mContext.getResources().getString(R.string.app_name), false);


        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, animation);
        mainList.setAdapter(adapter);

        mainList.setOnItemClickListener(this);
        v = LayoutInflater.from(mContext).inflate(R.layout.footer, null);
        v.setVisibility(View.GONE);
        mainList.addFooterView(v);
        mainList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (lastItem == adapter.getCount()
                        && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    if (adapter.getCount() > visiPostion) {
                        LoadMore();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                lastItem = firstVisibleItem + visibleItemCount - 1;
                visiPostion = visibleItemCount;
            }
        });
    }

    private void LoadMore() {
        v.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, animation2);
                mainList.setAdapter(adapter);
                hand.obtainMessage(UPDATE).sendToTarget();
            }
        }, 5000);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                nextActivity(ReboundActivity.class);
                overridePendingTransition(R.anim.in_translate_top, R.anim.in_translate_top);//从上面掉下来
                break;
            case 1:
                nextActivity(SimpleAnimationActivity.class);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);//push推的
                break;
        }
    }

    private void nextActivity(Class getclass) {
        startActivity(new Intent(mContext, getclass));
    }
}
