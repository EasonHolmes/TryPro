package com.cui.trypro;

import android.os.Bundle;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by cuiyang on 15/8/3.
 */
public class CenterActivity extends BaseActivity {
    @InjectView(R.id.img_center)
    ImageView imgCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.center_act);
        ButterKnife.inject(this);



    }
}
