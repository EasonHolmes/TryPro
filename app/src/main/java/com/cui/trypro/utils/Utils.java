package com.cui.trypro.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by cuiyang on 15/8/26.
 */
public class Utils {


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

}
