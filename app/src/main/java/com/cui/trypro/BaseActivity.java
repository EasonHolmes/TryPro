package com.cui.trypro;

import android.annotation.SuppressLint;
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

import com.cui.trypro.View.circlerefreshlayout.SystemBarTintManager;
import com.cui.trypro.activity_animation.activityOptionCS.transition.TransitionCompat;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;


public class BaseActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

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

//    protected void initAnimation() {
//        TransitionCompat.setAnimDuration(500);
////         这段代码必须放在ActivityOptionsCompat各种设置之后
//        TransitionCompat.startTransition(this, R.layout.instamaterial);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();//在这调用这个相当于finish。但finish不会调用this.onbackpressed就等于无返回键没有动画了
                break;
        }
        return super.onOptionsItemSelected(item);
    }


//    @Override
//    public void onBackPressed() {
    /**
     * //material onBackPressed用这两行代码
     * */
//        注意onBackPressed()方法——这很重要。因为它让操作系统知道在关闭第二个activity之前要完成动画的执行。
//        finishAfterTransition();
    /**
     * activityOPtionICs就这个
     * */
//        super.onBackPressed();//activityOPtionICs不能用super要不失效
    //TransitionCompat.setExitTransition(new MySceneAnim(this));//a test anim.Should not be use with customAnimation
    //TransitionCompat.setAnimStartDelay(0);// default
//        TransitionCompat.setAnimDuration(500);// default
    //TransitionCompat.setTimeInterpolator(new AccelerateDecelerateInterpolator());// default
    //TransitionCompat.finishAfterTransition(activity, enterAnim, exitAnim);// custom animation
    // 这段代码必须放在ActivityOptionsCompat各种设置之后
//        TransitionCompat.finishAfterTransition(this);
//    }
}
