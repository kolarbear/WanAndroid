package com.kolarbear.wanandroid.ui.article;

import com.kolarbear.wanandroid.aspectj.annotation.LoginCheck;
import com.kolarbear.wanandroid.base.BasePresenter;
import com.kolarbear.wanandroid.base.interfac.IView;
import com.kolarbear.wanandroid.bean.BaseBean;
import com.kolarbear.wanandroid.utils.RxScheduler;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * 文章详情Presenter
 * Created by Administrator on 2018/6/6.
 */

public class ArticlePresenter extends BasePresenter<ArticleContract.View> {

    @Inject
    public ArticlePresenter(IView view) {
        super((ArticleContract.View) view);
    }

    /**
     *收藏站外文章
     * @param title
     * @param author
     * @param link
     */
    @LoginCheck(type = 2)
    public void collectArticle(String title,String author, String link)
    {
        service.collectOutsideArticle(title,author,link)
                .compose(getView().<BaseBean>bindToLife())
                .compose(RxScheduler.<BaseBean>applySchedulers())
                .subscribe(new Consumer<BaseBean>() {
                    @Override
                    public void accept(BaseBean baseBean) throws Exception {
                        getView().collect(baseBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

}
