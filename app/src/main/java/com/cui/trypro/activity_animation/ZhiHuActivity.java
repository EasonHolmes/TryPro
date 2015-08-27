package com.cui.trypro.activity_animation;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.cui.trypro.BaseActivity;
import com.cui.trypro.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by cuiyang on 15/8/3.
 */
public class ZhiHuActivity extends BaseActivity {

    @InjectView(R.id.mToolBar)
    Toolbar mToolBar;
    @InjectView(R.id.img_bujian2)
    ImageView imgBujian2;
    private Context mContext;
    private AnimationUtils animationUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option_ics_act);
        ButterKnife.inject(this);
        mContext = this;


        final Animation a = animationUtils.loadAnimation(mContext, R.anim.welcome_fade_in_scale);
        a.setDuration(2000);
        imgBujian2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgBujian2.startAnimation(a);
            }
        });
    }
}


