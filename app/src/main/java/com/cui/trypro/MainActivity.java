package com.cui.trypro;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cui.trypro.activitys.Activity_Animation_Act;
import com.cui.trypro.activitys.Animation_Groups__Activity;
import com.cui.trypro.activitys.Small_FunctionDemo_Act;
import com.cui.trypro.animation_groups.ReboundActivity;
import com.cui.trypro.View.circlerefreshlayout.SystemBarTintManager;
import com.cui.trypro.utils.Utils;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener, NavigationView.OnNavigationItemSelectedListener {


    @InjectView(R.id.main_list)
    ListView mainList;
    @InjectView(R.id.layout_dr)
    NavigationView layoutDr;
    @InjectView(R.id.mDrawerlayout)
    DrawerLayout mDrawerlayout;
    @InjectView(R.id.mToolBar)
    Toolbar mToolBar;

    private ActionBarDrawerToggle mDrawerToggle;
    private String[] animation = {"Rebound使用", "Activity转场动画", "Material_animation", "小功能demo",
            "InstaMaterial概念设计_library", "InstaMaterial概念设计_拍照", "InstaMaterial概念设计_progress",
            "ListAnimation", "ListAnimation", "ListAnimation", "ListAnimation", "ListAnimation",
            "ListAnimation", "ListAnimation", "ListAnimation", "ListAnimation"};

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
        super.initToolbar("",true);
        initView();

    }

    private void initView() {
        mainList.setOnItemClickListener(this);
        layoutDr.setNavigationItemSelectedListener(this);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, animation);
        mainList.setAdapter(adapter);

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
                visiPostion = visibleItemCount;//最后一个item进行刷新
            }
        });
        /**先加载mainactivity再加载welactivity这样过度就有知乎的效果了*/
        startActivity(new Intent(mContext, WelActivity.class));
    }
    /**
     * 加载更多
     */
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
                nextActivity(Activity_Animation_Act.class);
//                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);//push推的          style.xml中添加<item name="android:windowIsTranslucent">true</item>因为有这个属性会变成进入的activity push覆盖上来的效果
                break;
            case 2:
//                Material_Library_animation
                nextActivity(Animation_Groups__Activity.class);
                break;
            case 3:
                Utils.nextAct(mContext, Small_FunctionDemo_Act.class);

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(false);//不退出程序让在后台运行
    }

    private void nextActivity(Class getclass) {
        startActivity(new Intent(mContext, getclass));
    }


    //navigationitemselected Listener
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);
//        mDrawerlayout.closeDrawers();//关闭抽屉
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mDrawerlayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
