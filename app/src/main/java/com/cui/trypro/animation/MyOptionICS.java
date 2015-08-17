package com.cui.trypro.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.cui.trypro.BaseActivity;
import com.cui.trypro.R;
import com.cui.trypro.animation.activityOptionCS.anim.ViewAnimationListenerAdapter;
import com.cui.trypro.animation.activityOptionCS.transition.TransitionCompat;
import com.cui.trypro.animation.activityOptionCS.transition.TransitionListenerAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by cuiyang on 15/8/12.
 * <p/>
 * 开源地址http://blog.csdn.net/wangjia55/article/details/42583011
 */
public class MyOptionICS extends BaseActivity {

    @InjectView(R.id.img_bujian2)
    ImageView imgBujian2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_option_ics);
        ButterKnife.inject(this);
        super.initToolbar("", true);

/**
 * view动画的监听器，比如thumbNailScaleAnim，screenTransitAnim这样的动画就会在这里得到监听
 *
 * 这里的值是说明动画进行到什么时候，原始的view开始显示
 * 设置方式是：动画时间越长，可以设置的越精细，越靠近1，动画时间越短设置为0.95就差不多了
 * 这里的值请自行根据你的动画长度进行调整，如果调整不好可能会出现动画结束后相应元素不见的问题。
 * 这里测试是如果动画是2000ms，那么用0.998较为合适
 */
        final float fraction = 0.9f;
        TransitionCompat.addViewAnimListener(new ViewAnimationListenerAdapter() {
            boolean isShowed = false;

            @Override
            public void onViewAnimationStart(View view, Animator animator) {
                // TODO 自动生成的方法存根
                super.onViewAnimationStart(view, animator);

            }

            public void onViewAnimationUpdate(View view, ValueAnimator valueAnimator, float progress) {
                super.onViewAnimationUpdate(view, valueAnimator, progress);
                // 判断当前是否是进入的状态，如果是进入的那么isEnter= true
                if (!TransitionCompat.isEnter
                        && progress >= fraction && !isShowed) {
                    isShowed = true;
                }
            }

            @Override
            public void onViewAnimationEnd(View view, Animator animator) {
                // TODO 自动生成的方法存根
                super.onViewAnimationEnd(view, animator);
                if (!TransitionCompat.isEnter && !isShowed) {
                    isShowed = true;
                }
            }
        });

        /**
         * 屏幕（场景）动画的监听器，这里用了适配器模式。可以传入完整的接口实现类
         */
        TransitionCompat.addListener(new TransitionListenerAdapter() {
            @Override
            public void onTransitionEnd(Animator animator, Animation animation,
                                        boolean isEnter) {
                super.onTransitionEnd(animator, animation, isEnter);
                // TODO:onEnd
            }
        });

//        TransitionCompat.setEnterTransition(new SceneFade(MyOptionICS.this));// use to scale Up animation
//        TransitionCompat.setAnimDuration(1000);// default
//        TransitionCompat.setAnimStartDelay(0);// default
//        TransitionCompat.setTimeInterpolator(new AccelerateDecelerateInterpolator());// default
        TransitionCompat.setAnimDuration(500);
//         这段代码必须放在ActivityOptionsCompat各种设置之后
        TransitionCompat.startTransition(this, R.layout.act_option_ics);
    }
}
