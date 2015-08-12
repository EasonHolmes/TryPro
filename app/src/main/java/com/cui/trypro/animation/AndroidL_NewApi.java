package com.cui.trypro.animation;

import android.os.Bundle;
import android.transition.Explode;
import android.transition.Slide;
import android.transition.Transition;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.Window;

import com.cui.trypro.BaseActivity;
import com.cui.trypro.R;

/**
 * Created by cuiyang on 15/8/11.
 */
public class AndroidL_NewApi extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS); // 允许使用transitions 
        super.onCreate(savedInstanceState);

        getWindow().setEnterTransition(new Slide());
        getWindow().setExitTransition(new Slide());

        setContentView(R.layout.center_act);

        super.initToolbar("", true);


//        getWindow().getExitTransition().addListener(new Transition.TransitionListener() {
//            @Override
//            public void onTransitionStart(Transition transition) {
//
//            }
//
//            @Override
//            public void onTransitionEnd(Transition transition) {
//
//            }
//
//            @Override
//            public void onTransitionCancel(Transition transition) {
//
//            }
//
//            @Override
//            public void onTransitionPause(Transition transition) {
//
//            }
//
//            @Override
//            public void onTransitionResume(Transition transition) {
//
//            }
//        });
    }

    @Override
    public void onBackPressed() {//
        super.onBackPressed();
        finishAfterTransition();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        getWindow().getExitTransition().removeListener()//为了防止内容泄漏，需要在Ondestory()方法中移除监听。
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
