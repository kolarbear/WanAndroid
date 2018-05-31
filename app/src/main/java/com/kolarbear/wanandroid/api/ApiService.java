package com.kolarbear.wanandroid.api;

import com.kolarbear.wanandroid.bean.BaseBean;
import com.kolarbear.wanandroid.bean.collect.CollectArticle;
import com.kolarbear.wanandroid.bean.home.HomeArticle;
import com.kolarbear.wanandroid.bean.home.HomeBanner;
import com.kolarbear.wanandroid.bean.knowledge.CategoryBean;
import com.kolarbear.wanandroid.bean.knowledge.KnowledgeBean;
import com.kolarbear.wanandroid.bean.login.LoginBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
     * 该分类下的所有文章
     * @param page
     * @param cid
     * @return
     */
    @GET("article/list/{page}/json")
    Observable<BaseBean<CategoryBean>> categoryArticles(@Path("page") int page,
                                                        @Query("cid") int cid);
    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @POST("user/login")
    @FormUrlEncoded
    Observable<BaseBean<LoginBean>> login(@Field("username") String username,
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


    /**
     * 收藏文章列表
     * http://www.wanandroid.com/lg/collect/list/0/json
        方法：GET
        参数： 页码：拼接在链接中，从0开始。
     * @param page
     * @return
     */
    @GET("lg/collect/list/{page}/json")
    Observable<BaseBean<CollectArticle>> collectList(@Path("page") int page);


    /**
     * 收藏文章
     * @param id
     * @return
     */
    @POST("lg/collect/{id}/json")
    Observable<BaseBean> collect(@Path("id") int id);

    /**
     * 取消收藏文章
     * @param id
     * @return
     */
    @POST("lg/uncollect_originId/{id}/json")
    Observable<BaseBean> cancelCollect(@Path("id") int id);

}
