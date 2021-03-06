package com.cui.trypro.activitys;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cui.trypro.BaseActivity;
import com.cui.trypro.R;
import com.cui.trypro.activity_animation.AndroidL_NewApi;
import com.cui.trypro.activity_animation.GifActivity;
import com.cui.trypro.activity_animation.MyOptionICS;
import com.cui.trypro.activity_animation.ZhiHuActivity;
import com.cui.trypro.activity_animation.activityOptionCS.ActivityCompatICS;
import com.cui.trypro.activity_animation.activityOptionCS.ActivityOptionsCompatICS;
import com.cui.trypro.activity_animation.activityOptionCS.transition.TransitionCompat;
import com.cui.trypro.adapter.Animation_groups_adapter;
import com.cui.trypro.adapter.MyBaseAdapter;
import com.cui.trypro.utils.RecyclerUtils;
import com.cui.trypro.utils.RecyclerUtils.RecyclerItemClickListener;
import com.cui.trypro.utils.RecyclerUtils.RecyclerItemClickListener.OnItemClickListener;
import com.cui.trypro.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * activity转场动画示例
 */
public class Activity_Animation_Act extends BaseActivity implements View.OnClickListener, OnItemClickListener {
    @InjectView(R.id.simple_list)
    RecyclerView simpleList;
    @InjectView(R.id.btn_turn)
    Button btnTurn;
    @InjectView(R.id.img_te)
    ImageView imgTe;
    @InjectView(R.id.img_bujian)
    ImageView imgBujian;

    private Context mContext;
    private List<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simplea_act);
        ButterKnife.inject(this);
        super.initToolbar("", true);
        mContext = this;


        simpleList.setLayoutManager(new LinearLayoutManager(mContext));
        simpleList.setAdapter(new Animation_groups_adapter(initData()));
        simpleList.addOnItemTouchListener(new RecyclerUtils.RecyclerItemClickListener(mContext, this));

        btnTurn.setText("optionICS点控件的缩放");
        btnTurn.setOnClickListener(this);

        imgTe.setOnClickListener(this);
        imgBujian.setOnClickListener(this);

    }


    private List<String> initData() {
        list.add("知乎欢迎动画");
        list.add("gif显示");
        list.add("android_L_Animation");
        list.add("optionICS从下到上的");

        return list;

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
    public void scaleUpAnim(View view) {

        ActivityOptionsCompatICS options = ActivityOptionsCompatICS.makeScaleUpAnimation(view,
                30, 30, //拉伸开始的坐标
                view.getMeasuredWidth(), view.getMeasuredHeight());//初始的宽高
        ActivityCompatICS.startActivity(this, new Intent(mContext, MyOptionICS.class), options.toBundle());
    }


    /**
     * source：一个view对象，用来确定起始坐标
     * <p/>
     * thumbnail：一个bitmap对象，新的activity将通过这个bitmap渐变拉伸出现，新的activity初始大小就是这个bitmap的大小
     * <p/>
     * startX：新activity初始的X坐标，相对于source左上角的X来说的
     * <p/>
     * startY：新的activity初始的Y坐标，相对于source左上角Y坐标来说的
     * <p/>
     * 效果是：一个bitmap慢慢从某个位置拉伸渐变新的activity
     */
    public void thumbNailScaleAnim(ImageView view) {
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = view.getDrawingCache();
        ActivityOptionsCompatICS options = ActivityOptionsCompatICS.makeThumbnailScaleUpAnimation(
                view, bitmap, 0, 0);
        // Request the activity be started, using the custom animation options.
        ActivityCompatICS.startActivity(this, new Intent(mContext, MyOptionICS.class), options.toBundle());
//        view.setDrawingCacheEnabled(false);
    }


    /**
     * //自定义动画效果使用anim
     */
    public void customAnim() {
        ActivityOptionsCompatICS options = ActivityOptionsCompatICS.makeCustomAnimation(this,
                R.anim.slide_bottom_in, R.anim.slide_bottom_out);
        ActivityCompatICS.startActivity(this, new Intent(mContext, MyOptionICS.class), options.toBundle());
    }


    /**
     * 补间动画
     * activity：当前activity的对象
     * <p/>
     * sharedElement：一个view对象，用来和新的activity中的一个view对象产生动画
     * <p/>
     * sharedElemetId:新的activity中的view的Id，这个view是用来和原始activity中的view产生动画的
     * <p/>
     * 效果是：原始activity中的一个view随着新activity的慢慢启动而移动到新的activity中，实现补间动画
     */
    public void screenTransitAnim(View v, int id) {
        ActivityOptionsCompatICS options = ActivityOptionsCompatICS.
                makeSceneTransitionAnimation(this, v, id);
        ActivityCompatICS.startActivity(this, new Intent(mContext, MyOptionICS.class), options.toBundle());
    }

    private void nextActivity(Class getclass) {
        startActivity(new Intent(mContext, getclass));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_turn:
                scaleUpAnim(btnTurn);
                break;
            case R.id.img_te:
                thumbNailScaleAnim(imgTe);
                break;
            case R.id.img_bujian:
                screenTransitAnim(v, R.id.btn_bujian);//第二个共享元素的id
                break;
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case 0:
                nextActivity(ZhiHuActivity.class);
                overridePendingTransition(R.anim.unzoom_in, R.anim.unzoom_out);//缩放
                break;
            case 1:
                nextActivity(GifActivity.class);
                overridePendingTransition(R.anim.small_2_big, R.anim.fade_out);//缩放另一种效果
                break;
            case 2:
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    Utils.showSnackbar(simpleList,"版本需要在5.0或以上","close");
                    return;
                }
                getWindow().setEnterTransition(new Explode());// 设置一个endter transition
                Intent i = new Intent();
                i.setClass(mContext, AndroidL_NewApi.class);
                startActivity(i, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());//当你已经设置了允许使用Transition并设置了Transition动画，你就可以通过ActivityOptions.makeSceneTransitionAnimation()方法启动一个新的Activity来激活这个Transition：
                break;
            case 3:
                customAnim();
                break;
        }
    }
}
