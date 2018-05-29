package com.kolarbear.wanandroid.api;

import com.kolarbear.wanandroid.bean.BaseBean;
import com.kolarbear.wanandroid.bean.home.HomeArticle;
import com.kolarbear.wanandroid.bean.home.HomeBanner;
import com.kolarbear.wanandroid.bean.knowledge.KnowledgeBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 *  接口
 * Created by Administrator on 2018/5/15.
 */

public interface ApiService {

    /**
     * 首页banner http://www.wanandroid.com/banner/json
     */
    @GET("banner/json")
    Observable<BaseBean<List<HomeBanner>>> homeBanner();

    /**
     *http://www.wanandroid.com/article/list/0/json

     方法：GET
     参数：页码，拼接在连接中，从0开始。
     * @param page
     * @return
     */
    @GET("article/list/{page}/json")
    Observable<BaseBean<HomeArticle>> homeArticle(@Path("page") int page);


    /**
     *  http://www.wanandroid.com/tree/json
         方法：GET
         参数：无
     */
    @GET("tree/json")
    Observable<BaseBean<List<KnowledgeBean>>> knowledgeTree();


    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @POST("user/login")
    @FormUrlEncoded
    Observable<BaseBean> login(@Field("username") String username,
                               @Field("password") String password);

    /**
     * 注册
     * @param username
     * @param password
     * @param repassword
     * @return
     */
    @POST("user/register")
    @FormUrlEncoded
    Observable<BaseBean> register(@Field("username") String username,
                                  @Field("password") String password,
                                  @Field("repassword") String repassword);

}
