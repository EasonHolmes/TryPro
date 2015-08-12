package com.cui.trypro.animation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
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
    @InjectView(R.id.img_center)
    ImageView imgCenter;

    private Context mContext;
    private AnimationUtils animationUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.center_act);
        ButterKnife.inject(this);
        mContext = this;


        final Animation a = animationUtils.loadAnimation(mContext, R.anim.welcome_fade_in_scale);
        a.setDuration(2000);
        imgCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgCenter.startAnimation(a);
            }
        });
    }
}


