package com.cui.trypro;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.cui.trypro.activitys.Activity_Animation_Act;
import com.cui.trypro.activitys.Animation_Groups__Activity;
import com.cui.trypro.activitys.LitePalTestActivity;
import com.cui.trypro.activitys.Small_FunctionDemo_Act;
import com.cui.trypro.adapter.MySimpleRecycler_Adapter;
import com.cui.trypro.animation_groups.ReboundActivity;
import com.cui.trypro.small_function.SVG_act;
import com.cui.trypro.small_function.Time_line_Act;
import com.cui.trypro.utils.RecyclerUtils;
import com.cui.trypro.utils.Utils;
import com.cui.trypro.widget.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    @InjectView(R.id.main_list)
    RecyclerView mainList;
    @InjectView(R.id.mDrawerlayout)
    DrawerLayout mDrawerlayout;
    @InjectView(R.id.mToolBar)
    Toolbar mToolBar;
    @InjectView(R.id.img_userHead_fordrawer)
    CircleImageView imgUserHeadFordrawer;
    @InjectView(R.id.txt_user_name)
    TextView txtUserName;
    @InjectView(R.id.layout_toAccountSetting)
    FrameLayout layoutToAccountSetting;
    @InjectView(R.id.layout_menu_to_account)
    LinearLayout layoutMenuToAccount;
    @InjectView(R.id.layout_menu_to_opinionsetting)
    LinearLayout layoutMenuToOpinionsetting;
    @InjectView(R.id.layout_menu_to_usersetting)
    LinearLayout layoutMenuToUsersetting;
    @InjectView(R.id.drawer_view)
    LinearLayout drawerView;

    private ActionBarDrawerToggle mDrawerToggle;
    private Context mContext;
    private int lastItem;

    private boolean isIn = true;
    private View v;
    private final int UPDATE = 1;
    private ArrayAdapter adapter;
    private int visiPostion = 0;
    private List<String> list = new ArrayList<String>();

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
        super.initToolbar("main", false);
        initView();

    }

    private void initView() {
        drawerView.setOnClickListener(this);

        mainList.setLayoutManager(new LinearLayoutManager(mContext));
        mainList.setAdapter(new MySimpleRecycler_Adapter(getData()));
        mainList.addOnItemTouchListener(new RecyclerUtils.RecyclerItemClickListener(mContext, new RecyclerUtils.RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        nextActivity(ReboundActivity.class);
                        overridePendingTransition(R.anim.in_translate_top, R.anim.in_translate_top);//从上面掉下来
                        break;
                    case 1:
                        nextActivity(Activity_Animation_Act.class);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);//push推的          style.xml中添加<item name="android:windowIsTranslucent">true</item>因为有这个属性会变成进入的activity push覆盖上来的效果
                        break;
                    case 2:
                        nextActivity(Animation_Groups__Activity.class);
                        break;
                    case 3:
                        Utils.nextAct(mContext, Small_FunctionDemo_Act.class);
                        break;
                    case 4:
                        Utils.nextAct(mContext, LitePalTestActivity.class);
                        break;

                }
            }
        }));
        /**先加载mainactivity再加载welactivity这样过度就有知乎的效果了*/
        startActivity(new Intent(mContext, WelActivity.class));
    }

    private List<String> getData() {
        list.add("Rebound使用");
        list.add("Activity转场动画");
        list.add("Material_animation");
        list.add("小功能demo");
        list.add("LitePal使用");
//        list.add("InstaMaterial概念设计_library");
//        list.add("InstaMaterial概念设计_拍照");
//        list.add("InstaMaterial概念设计_progress");
//        list.add("ListAnimation");
//        list.add("ListAnimation");
//        list.add("ListAnimation");
//        list.add("ListAnimation");
        return list;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(false);//不退出程序让在后台运行
    }

    private void nextActivity(Class getclass) {
        startActivity(new Intent(mContext, getclass));
    }

    @Override
    public void onClick(View v) {

    }
}
