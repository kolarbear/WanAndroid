package com.kolarbear.wanandroid.ui.home;

import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.kolarbear.wanandroid.base.BasePresenter;
import com.kolarbear.wanandroid.base.interfac.IView;
import com.kolarbear.wanandroid.bean.BaseBean;
import com.kolarbear.wanandroid.bean.home.HomeArticle;
import com.kolarbear.wanandroid.bean.home.HomeBanner;
import com.kolarbear.wanandroid.di.scope.FragmentScope;
import com.kolarbear.wanandroid.utils.RxScheduler;
import com.kolarbear.wanandroid.utils.Utils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2018/5/15.
 */
@FragmentScope
public class HomePresenter extends BasePresenter<HomeContract.IHomeView> {

    private int page;
    private boolean refresh;

    @Inject
    public HomePresenter(IView view) {
        super((HomeContract.IHomeView)view);
    }

    public void example()
    {
        Log.e("HomePresenter", "example: 哈？"+service);
    }

    //加载首页banner
    public void loadBanner()
    {
        service.homeBanner()
                .compose(getView().<BaseBean<List<HomeBanner>>>bindToLife())
                .compose(RxScheduler.<BaseBean<List<HomeBanner>>>applySchedulers())
                .subscribe(new Consumer<BaseBean<List<HomeBanner>>>() {
                    @Override
                    public void accept(BaseBean<List<HomeBanner>> listBaseBean) throws Exception {
                        if (listBaseBean.errorCode==0)
                        {
                            getView().showBanner(listBaseBean.data);
                        }else {
                            ToastUtils.showShort(listBaseBean.errorMsg);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    /**
     * 加载首页文章
     */
    public void loadHomeArticle(){
        service.homeArticle(page)
                .compose(getView().<BaseBean<HomeArticle>>bindToLife())
                .compose(RxScheduler.<BaseBean<HomeArticle>>applySchedulers())
                .subscribe(new Consumer<BaseBean<HomeArticle>>() {
                    @Override
                    public void accept(BaseBean<HomeArticle> homeArticleBaseBean) throws Exception {
                        if (homeArticleBaseBean.errorCode==0)//获取数据成功
                        {
                            if (refresh)
                            {
                                getView().showArticles(homeArticleBaseBean.data.getDatas(), Utils.TYPE_REFRESH);
                            }else {
                                getView().showArticles(homeArticleBaseBean.data.getDatas(), Utils.TYPE_LOAD_MORE);
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    /**
     * 刷新数据
     */
    public void refresh(){
        page = 0;
        refresh = true;
        loadHomeArticle();
        loadBanner();
    }

    /**
     * 加载更多
     */
    public void loadMore(){
        refresh = false;
        ++page;
        loadHomeArticle();
    }

    /**
     * 加载首页数据
     */
    public void loadHomeData()
    {

    }
}
