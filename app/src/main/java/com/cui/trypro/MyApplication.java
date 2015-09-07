package com.cui.trypro;

import android.app.Application;

import com.github.mmin18.layoutcast.LayoutCast;

import org.litepal.LitePalApplication;

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
        LitePalApplication.initialize(this);
    }
}
