package com.cui.trypro.utils;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import dalvik.annotation.TestTargetClass;

/**
 * Created by cuiyang on 15/8/26.
 */
public class Utils {

    private static Intent i = null;

    public static void showSnackbar(View v, String txt, String right) {
        Snackbar.make(v, txt, Snackbar.LENGTH_LONG)
                .setAction(right, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                })
//                .setActionTextColor(R.color.green)
//                .setDuration(3000)
                .show(); // Don’t forget to show!

    }

    public static void nextAct(Context mContext, Class ss) {
        if (i == null) {
            i = new Intent();
            i.setClass(mContext, ss);
            Log.e("tag...", "ssss==" + ss.toString());
            mContext.startActivity(i);
        } else {
            i.setClass(mContext, ss);
            Log.e("tag...", "sss222s==" + ss.toString());
            mContext.startActivity(i);
        }

    }

}
