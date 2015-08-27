package com.cui.trypro.animation_groups;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.TextView;

import com.cui.trypro.BaseActivity;
import com.cui.trypro.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * http://mp.weixin.qq.com/s?__biz=MzA3MDMyMjkzNg==&mid=211106689&idx=1&sn=5a9d8fef8006965e247fc4f134d237dd&scene=5&srcid=feAtVCV2itfCWT3r3vkf#rd
 * <p/>
 * valueAnimator,objectAnimator,AnimatorSet 一般都用objectanimation
 * translationX Y 平移
 * rotation 旋转
 * scale 翻转
 * alpha
 * <p/>
 * <p/>
 * 实现组合动画功能主要需要借助AnimatorSet这个类，这个类提供了一个play()方法，
 * 如果我们向这个方法中传入一个Animator对象(ValueAnimator或ObjectAnimator)将会返回一个AnimatorSet.Builder的实例，AnimatorSet.Builder中包括以下四个方法：
 * after(Animator anim)   将现有动画插入到传入的动画之后执行
 * after(long delay)   将现有动画延迟指定毫秒后执行
 * before(Animator anim)   将现有动画插入到传入的动画之前执行
 * with(Animator anim)   将现有动画和传入的动画同时执行
 */
public class View_BaseAnimation extends BaseActivity {
    @InjectView(R.id.txt_base)
    TextView txtBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_base_anim);
        ButterKnife.inject(this);


        ObjectAnimator moveIn = ObjectAnimator.ofFloat(txtBase, "translationX", -500f, 0f);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(txtBase, "rotation", 0f, 360f);
        ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(txtBase, "alpha", 1f, 0f, 1f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(rotate).with(fadeInOut).after(moveIn);
        animSet.setDuration(5000);
        animSet.start();


    }
}
