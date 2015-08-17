package com.cui.trypro;

import android.app.Application;

import com.github.mmin18.layoutcast.LayoutCast;

/**
 * Created by cuiyang on 15/8/13.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            LayoutCast.init(this);
        }
    }
}
