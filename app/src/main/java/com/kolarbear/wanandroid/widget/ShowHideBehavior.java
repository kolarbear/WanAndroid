package com.kolarbear.wanandroid.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2018/6/1.
 */

public class ShowHideBehavior extends FloatingActionButton.Behavior{

    private static final String TAG = "ShowHideBehavior";

    public ShowHideBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    private FloatingActionButton.OnVisibilityChangedListener listener = new FloatingActionButton.OnVisibilityChangedListener() {
        @Override
        public void onShown(FloatingActionButton fab) {
            super.onShown(fab);
            Log.e(TAG, "onShown: ");
        }

        @Override
        public void onHidden(FloatingActionButton fab) {
            super.onHidden(fab);
            Log.e(TAG, "onHidden: ");
            fab.setVisibility(View.INVISIBLE);
        }
    };

    /**
     * 开始滑动
     * @param coordinatorLayout
     * @param child
     * @param directTargetChild
     * @param target
     * @param axes
     * @param type
     * @return
     */
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        Log.e(TAG, "onStartNestedScroll: dyConsumed>");
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL|| super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes,type);
    }

    /**
     * 正在滑动
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param dxConsumed
     * @param dyConsumed
     * @param dxUnconsumed
     * @param dyUnconsumed
     * @param type
     */
    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
        if (dyConsumed>0&&dyUnconsumed==0 || dyConsumed==0&&dyUnconsumed>0 && child.getVisibility()==View.VISIBLE)
        {
            //上划 隐藏  到底部了还在上划
            child.hide(listener);
        }else if (dyConsumed<0&&dyUnconsumed==0||dyConsumed == 0 && dyUnconsumed < 0 && child.getVisibility()!=View.VISIBLE)
        {
            //下划  显示  到顶部了还在下划
            child.show(listener);
        }
        Log.e(TAG, "onNestedScroll: dyConsumed>"+dyConsumed);
        Log.e(TAG, "onNestedScroll: dyUnconsumed>"+dyUnconsumed);
    }


}
