package com.kolarbear.wanandroid.bean.knowledge;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.lang.reflect.Type;
import java.util.List;

/**
 * 自定义Converter 存储List集合
 * Created by Administrator on 2018/6/20.
 */

public class ChildConverter implements PropertyConverter<List<KnowledgeBean.ChildrenBean>,String> {

    private static final String TAG = "ChildConverter";
    private Gson mGson;
    public ChildConverter() {
        mGson = new Gson();
    }

    //将数据库中存储的数据转换为Bean
    @Override
    public List<KnowledgeBean.ChildrenBean> convertToEntityProperty(String databaseValue) {
        Log.e(TAG, "convertToEntityProperty: "+databaseValue);
        Type type = new TypeToken<List<KnowledgeBean.ChildrenBean>>() {
        }.getType();
        List<KnowledgeBean.ChildrenBean> childrenBeans = mGson.fromJson(databaseValue,type);
        return childrenBeans;
    }

    @Override
    public String convertToDatabaseValue(List<KnowledgeBean.ChildrenBean> entityProperty) {
        String s = mGson.toJson(entityProperty);
        Log.e(TAG, "convertToDatabaseValue: "+s);
        return s;
    }
}
