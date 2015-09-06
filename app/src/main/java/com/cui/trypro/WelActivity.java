package com.cui.trypro;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;


import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by cuiyang on 15/8/26.
 */
public class WelActivity extends AppCompatActivity {

    @InjectView(R.id.wel_img)
    ImageView welImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wel_act);
        ButterKnife.inject(this);


        ObjectAnimator animator = new ObjectAnimator().ofFloat(welImg, "scaleY", 1f, 2f);
        ObjectAnimator animator1 = new ObjectAnimator().ofFloat(welImg, "scaleX", 1f, 2f);
        ObjectAnimator animator2 = new ObjectAnimator().ofFloat(welImg, "alpha", 1f, 0f);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animator, animator1, animator2);
        set.setDuration(1000);
        set.start();
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                finish();
            }
        });
    }
}
