package com.kolarbear.wanandroid.utils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 用于绘制分割线
 * Created by Administrator on 2018/5/22.
 */

public class StickDecoration extends RecyclerView.ItemDecoration {

    private int mDecorationHeight = 40;
    private final Paint mPaint;
    private final Paint mTextPaint;


    public StickDecoration() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#FF4081"));
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(16);
    }

    /**
     * 绘制在最上面
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int right = parent.getRight();
        int left = parent.getLeft();

    }

    /**
     * 绘制item之前调用
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    /**
     * 通过Rect为每个item设置偏移，用于绘制Decoration
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = mDecorationHeight;
    }
}
