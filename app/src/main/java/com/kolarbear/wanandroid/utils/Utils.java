package com.kolarbear.wanandroid.utils;

import android.support.v4.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kolarbear.wanandroid.constant.Constant;

import java.util.List;

/**
 * Created by Administrator on 2018/5/22.
 */

public class Utils {

    public static final int TYPE_REFRESH = 0;
    public static final int TYPE_REFRESH_ERROR = 1;
    public static final int TYPE_LOAD_MORE = 2;
    public static final int TYPE_LOAD_MORE_ERROR = 3;

    public static void update(SwipeRefreshLayout refreshLayout, BaseQuickAdapter adapter,List data,int type){
        switch (type)
        {
            case TYPE_REFRESH:
                if (data!=null)
                adapter.setNewData(data);
                refreshLayout.setRefreshing(false);
                break;
            case TYPE_REFRESH_ERROR:
                refreshLayout.setRefreshing(false);
                break;
            case TYPE_LOAD_MORE:
                if (data!=null) adapter.addData(data);
                break;
            case TYPE_LOAD_MORE_ERROR:
                adapter.loadMoreFail();
                break;
        }
        if (data==null || data.isEmpty() || data.size() < Constant.PAGE_SIZE){
            adapter.loadMoreEnd();
        }else {
            adapter.loadMoreComplete();
        }
    }
}
