package com.cui.trypro;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cui.trypro.animation.CenterActivity;
import com.cui.trypro.animation.GifActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by cuiyang on 15/8/4.
 */
public class SimpleAnimationActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    @InjectView(R.id.simple_list)
    ListView simpleList;

    private String s[] = {"知乎欢迎动画", "gif显示"};
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simplea_act);
        ButterKnife.inject(this);
        mContext = this;
        simpleList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, s));
        simpleList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                nextActivity(CenterActivity.class);
                overridePendingTransition(R.anim.unzoom_in, R.anim.unzoom_out);//缩放
                break;
            case 1:
                nextActivity(GifActivity.class);
                overridePendingTransition(R.anim.small_2_big, R.anim.fade_out);//缩放另一种效果
                break;
        }
    }

    private void nextActivity(Class getclass) {
        startActivity(new Intent(mContext, getclass));
    }
}
