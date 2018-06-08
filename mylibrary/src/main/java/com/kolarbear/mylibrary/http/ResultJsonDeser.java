package com.kolarbear.mylibrary.http;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.kolarbear.wanandroid.bean.BaseBean;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 解析gson数据
 * Created by Administrator on 2017/9/8.
 */

public class ResultJsonDeser implements JsonDeserializer<BaseBean<?>> {

    @Override
    public BaseBean<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        BaseBean response = new BaseBean();
        if (json.isJsonObject()){
            JsonObject jsonObject = json.getAsJsonObject();
            int code = jsonObject.get("errorCode").getAsInt();
            response.errorCode = code;
            response.errorMsg = jsonObject.get("errorMsg").getAsString();
            if (code<0){
                return response;
            }
            try {
                Type itemType = ((ParameterizedType) typeOfT).getActualTypeArguments()[0];
                response.data = context.deserialize(jsonObject.get("data"), itemType);
                return response;
            }catch (Exception e)
            {
                return response;
            }
        }
        return response;
    }
}
