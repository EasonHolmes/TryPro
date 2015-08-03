package com.cui.trypro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by cuiyang on 15/8/3.
 */
public class OpenActivity extends BaseActivity {
    @InjectView(R.id.img_one)
    ImageView imgOne;
    @InjectView(R.id.img_two)
    ImageView imgTwo;
    @InjectView(R.id.img_three)
    ImageView imgThree;
    @InjectView(R.id.img_four)
    ImageView imgFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_act);
        ButterKnife.inject(this);

        super.initToolbar("OpenAct", true);


    }
}
