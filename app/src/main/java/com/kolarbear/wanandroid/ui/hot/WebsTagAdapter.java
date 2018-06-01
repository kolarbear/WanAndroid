package com.kolarbear.wanandroid.ui.hot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.kolarbear.wanandroid.R;
import com.kolarbear.wanandroid.bean.hot.WebsiteBean;
import com.kolarbear.wanandroid.utils.Utils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/6/1.
 */

public class WebsTagAdapter extends TagAdapter<WebsiteBean> {

    private final Context context;

    public WebsTagAdapter(Context context, List<WebsiteBean> datas) {
        super(datas);
        this.context = context;
    }

    @Override
    public View getView(FlowLayout parent, int position, WebsiteBean websiteBean) {
        TextView tv = (TextView) LayoutInflater.from(context).inflate(R.layout.tv,parent,false);

        tv.setText(websiteBean.getName());

        tv.setTextColor(Utils.getRandomColor());

        return tv;
    }
}
