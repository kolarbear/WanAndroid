package com.kolarbear.wanandroid.utils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;

/**
 * 用于绘制分割线
 * Created by Administrator on 2018/5/22.
 */

public class StickDecoration extends RecyclerView.ItemDecoration {

    private int mDecorationHeight = 40;
    private final Paint mPaint;
    private final Paint mTextPaint;
    private OnTagListener mListener;
    private static final String TAG = "StickDecoration";
    public StickDecoration(OnTagListener listener) {
        this.mListener = listener;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#FF4081"));
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(18);
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


        int itemCount = state.getItemCount(); //获取所有item的数量
        int childCount = parent.getChildCount();//获取当前屏幕显示的item数量
        int right = parent.getChildAt(0).getRight();
        int left = parent.getChildAt(0).getLeft();
        String preTag;
        String curTag = null;
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            int adapterPosition = parent.getChildAdapterPosition(child);
            preTag = curTag;
            curTag = mListener.getCurrentTag(adapterPosition);
            if (curTag==null||TextUtils.equals(preTag,curTag)) //如果两个item属于同一个tag就不绘制
                continue;
            int bottom = child.getBottom();//获取item的bottom
            float tagBottom = Math.max(mDecorationHeight,child.getTop());
            if (adapterPosition+1<itemCount)//判断是否为最后一个
            {
                String nextTag = mListener.getCurrentTag(adapterPosition + 1);

                if (!TextUtils.equals(nextTag,curTag)&&bottom<tagBottom)//被顶起来的条件
                {
                    tagBottom = bottom;
                }
            }
            Rect rect = new Rect();
            mTextPaint.getTextBounds(curTag,0,curTag.length(),rect);
            int textWidth = rect.width();
            int height = rect.height();
            c.drawRect(left,tagBottom-mDecorationHeight,right,tagBottom,mPaint);
            c.drawText(curTag,right/2-textWidth/2,tagBottom-mDecorationHeight/2+height/2,mTextPaint);
        }

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
       /* int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            int left = child.getLeft();
            int right = child.getRight();
            int bottom = child.getTop();
            int top = bottom - mDecorationHeight;
            c.drawRect(left,top,right,bottom,mPaint);
        }*/
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

        int position = parent.getChildAdapterPosition(view);
        if (isFirstTag(position))
            outRect.top = mDecorationHeight;

    }

    private boolean isFirstTag(int position)
    {
        if (position==0)
            return true;
        String currentTag = mListener.getCurrentTag(position);
        String preTag = mListener.getCurrentTag(position - 1);
        return !TextUtils.equals(currentTag,preTag);
    }

    public interface OnTagListener{
        String getCurrentTag(int position);
    }

}
