package com.cui.trypro.TreeView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

import com.cui.trypro.BaseActivity;
import com.cui.trypro.R;
import com.cui.trypro.adapter.InstaMaterial_adapter;
import com.cui.trypro.animation.activityOptionCS.transition.TransitionCompat;
import com.cui.trypro.animation.activityOptionCS.transition.TransitionListenerAdapter;
import com.cui.trypro.utils.Utils;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by cuiyang on 15/8/16.
 */
public class InstaMateriaL_Activity extends BaseActivity {


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
    private InstaMaterial_adapter adapter;
    private MenuItem inboxMenuItem;
    private static final int ANIM_DURATION_TOOLBAR = 300;
    private static final int ANIM_DURATION_FAB = 400;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instamaterial);
        ButterKnife.inject(this);
        mContext = this;
        super.initAnimation();
        initView();


    }

    private void initView() {
        initToolbar();
        startIntroAnimation();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new InstaMaterial_adapter(mContext);
        mRecyclerView.setAdapter(adapter);


        fabCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(mRecyclerView, "content", Snackbar.LENGTH_LONG)
                        .setAction("right", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        })
//                .setActionTextColor(R.color.green)
//                .setDuration(3000)
                        .show(); // Don’t forget to show!
            }
        });


    }

    private void initToolbar() {
        toolbar2.setTitle("material");
        toolbar2.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar2);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void startIntroAnimation() {
        fabCreate.setTranslationY(2 * getResources().getDimensionPixelOffset(R.dimen.btn_fab_size));

        int actionbarSize = Utils.dpToPx(56);
        toolbar2.setTranslationY(-actionbarSize);
        ivLogo.setTranslationY(-actionbarSize);
        inboxMenuItem.getActionView().setTranslationY(-actionbarSize);

        toolbar2.animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(300);
        ivLogo.animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(400);
        inboxMenuItem.getActionView().animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(500)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        startContentAnimation();
                    }
                })
                .start();
    }
    private void startContentAnimation() {
        fabCreate.animate()
                .translationY(0)
                .setInterpolator(new OvershootInterpolator(1.f))
                .setStartDelay(300)
                .setDuration(ANIM_DURATION_FAB)
                .start();
        adapter.updateItems(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        inboxMenuItem = menu.findItem(R.id.action_inbox);
//        inboxMenuItem.setActionView(R.layout.menu_item_view);
        return true;
    }
}
