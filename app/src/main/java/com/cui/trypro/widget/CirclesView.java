package com.cui.trypro.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by cuiyang on 15/8/27.
 */
public class CirclesView extends ImageView {
    private Paint paint;
    private int ColorId;

    public CirclesView(Context context) {
        super(context);
        init();
    }

    public CirclesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
//        paint.setAntiAlias(true);
//        paint.setDither(true);
    }

    public CirclesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        //画笔设置抗据齿
//        paint.setAntiAlias(true);
//        paint.setDither(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(ColorId);
        //画布设置抗据齿
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
        canvas.drawColor(Color.WHITE);
        canvas.drawCircle(this.getWidth() / 2, this.getHeight() / 2, getWidth() / 2 - 3, paint);
    }

    public void setColor(int color) {
        ColorId = color;
        invalidate();
    }
}
