package com.cui.trypro.small_function;

import android.graphics.Path;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.cui.trypro.BaseActivity;
import com.cui.trypro.R;
import com.eftimoff.androipathview.PathView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by cuiyang on 15/8/27.
 */
public class SVG_act extends BaseActivity {
    @InjectView(R.id.pathView)
    PathView pathView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.svg_act);
        ButterKnife.inject(this);
        final Path path = makeConvexArrow(50, 100);
        pathView.setPath(path);
        pathView.setFillAfter(true);
        pathView.useNaturalColors();
        //设置播放
        pathView.getPathAnimator().
                delay(100).
                duration(1500).
                interpolator(new AccelerateDecelerateInterpolator()).
                start();
//        pathView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pathView.getPathAnimator().
//                        delay(100).
//                        duration(1500).
//                        interpolator(new AccelerateDecelerateInterpolator()).
//                        start();
//            }
//        });
    }

    private Path makeConvexArrow(float length, float height) {
        final Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.lineTo(length / 4f, 0.0f);
        path.lineTo(length, height / 2.0f);
        path.lineTo(length / 4f, height);
        path.lineTo(0.0f, height);
        path.lineTo(length * 3f / 4f, height / 2f);
        path.lineTo(0.0f, 0.0f);
        path.close();
        return path;
    }
}
