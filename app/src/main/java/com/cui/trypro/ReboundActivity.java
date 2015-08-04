package com.cui.trypro;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringListener;
import com.facebook.rebound.SpringSystem;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by cuiyang on 15/8/3.
 */
public class ReboundActivity extends BaseActivity implements View.OnTouchListener, SpringListener {


    // Create a system to run the physics loop for a set of springs.
    //.首先创建一个SpringSystem对象
    SpringSystem mSpringSystem = SpringSystem.create();
    private int tension = 40; //张力系数
    private int friction = 3; //阻力系数
    private Spring mSpring;
    private SBListener sbListener;
    @InjectView(R.id.imageView)
    ImageView ivScalaImage;
    @InjectView(R.id.tvTension)
    TextView tvTension;
    @InjectView(R.id.skTension)
    SeekBar skTension;
    @InjectView(R.id.llTension)
    LinearLayout llTension;
    @InjectView(R.id.tvFriction)
    TextView tvFriction;
    @InjectView(R.id.skFriction)
    SeekBar skFriction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rebound_act);
        ButterKnife.inject(this);

        super.initToolbar("OpenAct", true);
        //初始化控件
        initWidget();
        //创建系统用于循环执行控件弹簧效果
        mSpringSystem = SpringSystem.create();
        //给系统添加一个“弹簧”
        mSpring = mSpringSystem.createSpring();
        //添加监听器，监听“弹簧”的形变
        mSpring.addListener(this);
        //根据张力系数和阻力系数创建一组“弹簧”参数
        SpringConfig config = new SpringConfig(tension, friction);
        //配置“弹簧”
        mSpring.setSpringConfig(config);

    }

    private void initWidget() {
        ivScalaImage = (ImageView) findViewById(R.id.imageView);
        ivScalaImage.setOnTouchListener(this);
        skTension = (SeekBar) findViewById(R.id.skTension);
        skFriction = (SeekBar) findViewById(R.id.skFriction);
        sbListener = new SBListener();
        skTension.setMax(100);
        skFriction.setMax(30);
        skTension.setOnSeekBarChangeListener(sbListener);
        skFriction.setOnSeekBarChangeListener(sbListener);
        tvTension = (TextView) findViewById(R.id.tvTension);
        tvFriction = (TextView) findViewById(R.id.tvFriction);
        skTension.setProgress(tension);
        skFriction.setProgress(friction);
        tvTension.setText("张力系数: " + tension);
        tvFriction.setText("阻力系数: " + friction);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mSpring.setEndValue(1f);
                return true;
            case MotionEvent.ACTION_UP:
                mSpring.setEndValue(0f);
                return true;
        }
        return false;
    }

    @Override
    public void onSpringUpdate(Spring spring) {
        float value = (float) spring.getCurrentValue();
        float scale = 1f - (value * 0.5f);
        ivScalaImage.setScaleX(scale);
        ivScalaImage.setScaleY(scale);
    }

    @Override
    public void onSpringAtRest(Spring spring) {
        SpringConfig config = new SpringConfig(tension, friction);//通过seekbar上的数字来设置张力和阴阻力
        mSpring.setSpringConfig(config);
    }

    @Override
    public void onSpringActivate(Spring spring) {
    }

    @Override
    public void onSpringEndStateChange(Spring spring) {
    }

    private class SBListener implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (seekBar.getId() == R.id.skTension) {
                tension = progress;
                skTension.setProgress(tension);
                tvTension.setText("张力系数: " + tension);
            } else if (seekBar.getId() == R.id.skFriction) {
                friction = progress;
                skFriction.setProgress(friction);
                tvFriction.setText("阻力系数: " + friction);
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            mSpring.setAtRest();
        }
    }
}
