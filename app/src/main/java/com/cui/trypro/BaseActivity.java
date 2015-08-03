package com.cui.trypro;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mDrawerToggle;
    protected String comeDate = null;

    @Override
    protected void onStart() {
        super.onStart();
        //纪录进入时间
//        comeDate = Utils.formatDate();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);//umeng统计
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //隐藏状态栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

    }

    /**
     * 每一个设置了Toolbar的Activity为其设置标题
     *
     * @param title 传入的参数就是标题
     * @param or    设置是否有返回键并且是否显示可用
     */
    protected void initToolbar(String title, boolean or) {
        //设置整个ToolBar  继承自BaseActivity
        //设定状态栏的颜色，当版本大于4.4时起作用
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            //此处可以重新指定状态栏颜色
            tintManager.setStatusBarTintResource(R.color.status_colors);
        }
        mToolbar = (Toolbar) findViewById(R.id.mToolBar);
        mToolbar.setTitle("");//设置左上角标题的，默认是APP的名字
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(or);
        getSupportActionBar().setDisplayHomeAsUpEnabled(or);
        if (!or) {
            mDrawerlayout = (DrawerLayout) findViewById(R.id.mDrawerlayout);
            mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerlayout, mToolbar, R.string.open, R.string.close);
            mDrawerToggle.syncState();
            mDrawerlayout.setDrawerListener(mDrawerToggle);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 每一个设置了Toolbar的Activity，设置其返回键功能正常
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
//        Utils.logger(getClass().getName(), "onPause===", 1);
//        Utils.rememberCometAndOutTime(comeDate, Utils.formatDate(), getLocalClassName());
    }

    //
//    @Override
//    public void onBackPressed() {
//        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
////        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_in_left);
//    }
    protected void toActivity() {
//        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
    }


}
