package com.cui.trypro.animation_groups;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

import com.cui.trypro.BaseActivity;
import com.cui.trypro.R;
import com.cui.trypro.View.circlerefreshlayout.SystemBarTintManager;
import com.cui.trypro.View.circlerefreshlayout.Insta_Utils;
import com.cui.trypro.adapter.FeedAdapter;
import com.cui.trypro.animation_groups.instaMaterial_view.FeedContextMenu;
import com.cui.trypro.animation_groups.instaMaterial_view.FeedContextMenuManager;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by cuiyang on 15/8/16.
 */
public class InstaMateriaL_Activity extends BaseActivity implements FeedAdapter.OnFeedItemClickListener,
        FeedContextMenu.OnFeedContextMenuItemClickListener {


    @InjectView(R.id.rvFeed)
    RecyclerView mRecyclerView;
    @InjectView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @InjectView(R.id.btnCreate)
    FloatingActionButton fabCreate;
    @InjectView(R.id.content)
    CoordinatorLayout content;
    @InjectView(R.id.toolbar2)
    Toolbar toolbar2;
    @InjectView(R.id.ivLogo)
    ImageView ivLogo;

    private Context mContext;
    private FeedAdapter adapter;
    //    private MenuItem inboxMenuItem;//侧滑栏的三个横线
    private static final int ANIM_DURATION_TOOLBAR = 300;
    private static final int ANIM_DURATION_FAB = 400;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instamaterial);
        ButterKnife.inject(this);
        mContext = this;
        initView();
    }

    private void initView() {
        initToolbar();
        startIntroAnimation();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new FeedAdapter(mContext);
        mRecyclerView.setAdapter(adapter);
        fabCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(mRecyclerView, "content", Snackbar.LENGTH_LONG)
                        .setAction("right", null).show();
            }
        });
        adapter.setOnFeedItemClickListener(this);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                FeedContextMenuManager.getInstance().onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void initToolbar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            //此处可以重新指定状态栏颜色
            tintManager.setStatusBarTintResource(R.color.background_blue2);
        }
        toolbar2.setTitle("material");
        toolbar2.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar2);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void startIntroAnimation() {
        /**设置初始fab位置*/
        fabCreate.setTranslationY(2 * getResources().getDimensionPixelOffset(R.dimen.btn_fab_size));
        int actionbarSize = Insta_Utils.dpToPx(56);
        toolbar2.setTranslationY(-actionbarSize);
        ivLogo.setTranslationY(-actionbarSize);
//        inboxMenuItem.getActionView().setTranslationY(-actionbarSize);
        toolbar2.animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(300);//设置延迟时间
        ivLogo.animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(400).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                startFabAnimation();
            }
        });
//        inboxMenuItem.getActionView().animate()
//                .translationY(0)
//                .setDuration(ANIM_DURATION_TOOLBAR)
//                .setStartDelay(500)
//                .setListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        startContentAnimation();
//                    }
//                })
//                .start();
    }

    private void startFabAnimation() {
        fabCreate.animate()
                .translationY(0)
                .setInterpolator(new OvershootInterpolator(1.f))
                .setStartDelay(300)
                .setDuration(ANIM_DURATION_FAB)
                .start();
        adapter.updateItem();//adapter add data
    }

    //留言按钮的回调
    @Override
    public void onCommentsClick(View v, int position) {
//        final Intent intent = new Intent(this, CommentsActivity.class);
//        int[] startingLocation = new int[2];
//        v.getLocationOnScreen(startingLocation);
//        intent.putExtra(CommentsActivity.ARG_DRAWING_START_LOCATION, startingLocation[1]);
//        startActivity(intent);
//        overridePendingTransition(0, 0);
        startActivity(new Intent(mContext,CommentsActivity.class));
    }

    //更多的点击回调
    @Override
    public void onMoreClick(View v, int position) {
        FeedContextMenuManager.getInstance().toggleContextMenuFromView(v, position, this);
    }

    @Override
    public void onProfileClick(View v) {

    }


    //more button callback
    @Override
    public void onReportClick(int feedItem) {

    }

    @Override
    public void onSharePhotoClick(int feedItem) {

    }

    @Override
    public void onCopyShareUrlClick(int feedItem) {

    }

    @Override
    public void onCancelClick(int feedItem) {

    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        inboxMenuItem = menu.findItem(R.id.action_inbox);
////        inboxMenuItem.setActionView(R.layout.menu_item_view);
//        return true;
//    }
}
