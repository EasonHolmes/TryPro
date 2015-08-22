package com.cui.trypro;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.cui.trypro.View.SystemBarTintManager;
import com.cui.trypro.animation.MyOptionICS;
import com.cui.trypro.animation.activityOptionCS.ActivityCompatICS;
import com.cui.trypro.animation.activityOptionCS.ActivityOptionsCompatICS;
import com.cui.trypro.animation.activityOptionCS.transition.TransitionCompat;


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
        // 允许使用transitions 
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
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
            tintManager.setStatusBarTintResource(R.color.background_blue2);
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
    protected void initAnimation(){
        TransitionCompat.setAnimDuration(500);
//         这段代码必须放在ActivityOptionsCompat各种设置之后
        TransitionCompat.startTransition(this, R.layout.instamaterial);
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
                Log.e(getClass().getName(), "sdfsdfs2222222");
                onBackPressed();//在这调用这个相当于finish。但finish不会调用this.onbackpressed就等于无返回键没有动画了
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        /**
         * //material onBackPressed用这两行代码
         * */
//        注意onBackPressed()方法——这很重要。因为它让操作系统知道在关闭第二个activity之前要完成动画的执行。
//        finishAfterTransition();
        /**
         * activityOPtionICs就这个
         * */
//        super.onBackPressed();//不能用super要不失效
        //TransitionCompat.setExitTransition(new MySceneAnim(this));//a test anim.Should not be use with customAnimation
        //TransitionCompat.setAnimStartDelay(0);// default
        TransitionCompat.setAnimDuration(500);// default
        //TransitionCompat.setTimeInterpolator(new AccelerateDecelerateInterpolator());// default
        //TransitionCompat.finishAfterTransition(activity, enterAnim, exitAnim);// custom animation
        // 这段代码必须放在ActivityOptionsCompat各种设置之后
        TransitionCompat.finishAfterTransition(this);

    }

    /**
     * source：一个view对象，用户确定新activity启动的初始坐标
     * <p/>
     * startX：新activity出现的初始X坐标，这个坐标是相对于source的左上角X坐标
     * <p/>
     * startY：新activity出现的初始Y坐标，这个坐标相对于source的左上角Y坐标
     * <p/>
     * width：新activity初始的宽度
     * <p/>
     * height：新activity初始的高度
     */
    public void scaleUpAnim(Class ss,Context mContext,View view) {

        ActivityOptionsCompatICS options = ActivityOptionsCompatICS.makeScaleUpAnimation(view,
                30, 30, //拉伸开始的坐标
                view.getMeasuredWidth(), view.getMeasuredHeight());//初始的宽高
        ActivityCompatICS.startActivity(this, new Intent(mContext, ss), options.toBundle());
    }
}
