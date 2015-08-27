package com.cui.trypro.activity_animation;

import android.os.Bundle;

import com.ant.liao.GifView;
import com.cui.trypro.BaseActivity;
import com.cui.trypro.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by cuiyang on 15/8/4.
 */
public class GifActivity extends BaseActivity {

    @InjectView(R.id.gif2)
    GifView gif1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gif_act);
        ButterKnife.inject(this);
        // 设置Gif图片源
        gif1.setGifImage(R.drawable.dagong_qdy);
        // 添加监听器
//        gif1.setOnClickListener(this);
        // 设置显示的大小，拉伸或者压缩
        gif1.setShowDimension(300, 300);
        // 设置加载方式：先加载后显示、边加载边显示、只显示第一帧再显示
        gif1.setGifImageType(GifView.GifImageType.COVER);
    }
}
