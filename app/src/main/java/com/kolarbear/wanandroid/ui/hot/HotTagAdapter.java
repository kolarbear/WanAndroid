package com.kolarbear.wanandroid.ui.hot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.kolarbear.wanandroid.R;
import com.kolarbear.wanandroid.bean.hot.HotBean;
import com.kolarbear.wanandroid.utils.Utils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

/**
 * 热词搜索适配器
 * Created by Administrator on 2018/6/1.
 */

public class HotTagAdapter extends TagAdapter<HotBean> {

    private final Context context;

    public HotTagAdapter(Context context, List<HotBean> datas) {
        super(datas);
        this.context = context;
    }

    @Override
    public View getView(FlowLayout parent, int position, HotBean hotBean) {

        TextView tv = (TextView) LayoutInflater.from(context).inflate(R.layout.tv,parent,false);

        tv.setText(hotBean.getName());

        tv.setTextColor(Utils.getRandomColor());

        return tv;
    }
}
