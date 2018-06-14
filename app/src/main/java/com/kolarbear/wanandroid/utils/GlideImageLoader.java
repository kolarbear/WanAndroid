package com.kolarbear.wanandroid.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by Administrator on 2018/5/21.
 */

public class GlideImageLoader extends ImageLoader {

    RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL);
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
        Glide.with(context.getApplicationContext())
                .applyDefaultRequestOptions(options)
                .load(path)
                .into(imageView);
    }
}
