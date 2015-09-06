package com.cui.trypro.animation_groups;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import com.cui.trypro.BaseActivity;
import com.cui.trypro.R;
import com.cui.trypro.widget.ColorTypeEvaluatorView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * http://mp.weixin.qq.com/s?__biz=MzA3MDMyMjkzNg==&mid=211106689&idx=1&sn=5a9d8fef8006965e247fc4f134d237dd&scene=5&srcid=feAtVCV2itfCWT3r3vkf#rd
 * <p/>
 * valueAnimator,objectAnimator,AnimatorSet 一般都用objectanimation
 * translationX Y 平移
 * rotation 旋转
 * scale 翻转
 * scaleX + scaleY 缩放
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
    @InjectView(R.id.colorsss)
    ColorTypeEvaluatorView colorsss;
    @InjectView(R.id.txt_basess)
    TextView txtBasess;

    /**
     * 两个球是自定义的view
     * ColorTypeEvaluatorView
     * PointTypeEvaluatorView
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_base_anim);
        ButterKnife.inject(this);

        baseUse();

        /**
         *  ViewPropertyAnimator
         *  让view 从原点开始到某个点
         * */
        txtBasess.animate().x(500).y(500).setDuration(5000)
                .setInterpolator(new BounceInterpolator());

    }

    /**
     * 基本用法
     * <p/>
     * /如果是多个动画按上面这样播放会给每个objectAnimator分别每个动画都有加不是整体
     * animSet.setInterpolator();设置补间器默认是AccelerateDecelerateInterpolator。先加速再减速
     * new AccelerateInterpolator(2f)2f加速倍数/
     * new BounceInterpolator()模拟物理规律有回弹
     * DecelerateInterpolator(2f)2f减速倍数
     * OvershootInterpolator(1f)表示向前甩一定值后再回到原来位置
     * AnticipateOvershootInterpolator(1f)：表示开始的时候向后然后向前甩一定值后返回最后的值
     * CycleInterpolator：表示动画循环播放特定的次数，速率改变沿着正弦曲线。
     * LinearInterpolator：表示以常量速率改变。
     */
    private void baseUse() {
        ObjectAnimator moveIn = ObjectAnimator.ofFloat(txtBase, "translationX", -500f, 0f);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(txtBase, "rotation", 0f, 360f);
        ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(txtBase, "alpha", 1f, 0f, 1f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(rotate).with(fadeInOut).after(moveIn);
        animSet.setInterpolator(new BounceInterpolator());
        animSet.setDuration(5000);
        animSet.start();
    }

    private void scale() {
        /** 设置缩放动画 */
        final ScaleAnimation animation = new ScaleAnimation(0.0f, 1.4f, 0.0f, 1.4f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(2000);//设置动画持续时间
/** 常用方法 */
//animation.setRepeatCount(int repeatCount);//设置重复次数
//animation.setFillAfter(boolean);//动画执行完后是否停留在执行完的状态
//animation.setStartOffset(long startOffset);//执行前的等待时间
        animation.startNow();
    }

    private void scaleXY() {
        /** 设置缩放动画 */

        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator bounceAnimX = ObjectAnimator.ofFloat(txtBase, "scaleX", 0.2f, 1f);
        bounceAnimX.setDuration(300);
        bounceAnimX.setInterpolator(new OvershootInterpolator(4));

        ObjectAnimator bounceAnimY = ObjectAnimator.ofFloat(txtBase, "scaleX", 0.2f, 1f);
        bounceAnimX.setDuration(300);
        bounceAnimX.setInterpolator(new OvershootInterpolator(4));
        animatorSet.play(bounceAnimX).with(bounceAnimY);

        animatorSet.start();
    }
}
