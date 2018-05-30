package com.kolarbear.wanandroid.ui.category_articles;

import com.blankj.utilcode.util.ToastUtils;
import com.kolarbear.wanandroid.base.BasePresenter;
import com.kolarbear.wanandroid.base.interfac.IView;
import com.kolarbear.wanandroid.bean.BaseBean;
import com.kolarbear.wanandroid.bean.knowledge.CategoryBean;
import com.kolarbear.wanandroid.utils.RxScheduler;
import com.kolarbear.wanandroid.utils.Utils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2018/5/30.
 */

public class ArticleListPresenter extends BasePresenter<ArticleListContract.View>{


    private int page;
    private boolean refresh;
    @Inject
    public ArticleListPresenter(IView view) {
        super((ArticleListContract.View)view);
    }


    /**
     * 获取该分类下的所有文章
     * @param cid
     */
    public void articleList(int cid)
    {
        service.categoryArticles(page,cid)
                .compose(getView().<BaseBean<CategoryBean>>bindToLife())
                .compose(RxScheduler.<BaseBean<CategoryBean>>applySchedulers())
                .subscribe(new Consumer<BaseBean<CategoryBean>>() {
                    @Override
                    public void accept(BaseBean<CategoryBean> categoryBeanBaseBean) throws Exception {
                        if (categoryBeanBaseBean.errorCode==0)
                        {
                            if (refresh)
                            {
                                getView().showArticleList(categoryBeanBaseBean, Utils.TYPE_REFRESH);
                            }else {
                                getView().showArticleList(categoryBeanBaseBean, Utils.TYPE_LOAD_MORE);
                            }
                        }else {
                            ToastUtils.showShort(categoryBeanBaseBean.errorMsg);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
    public void more(int cid)
    {
        page++;
        refresh = false;
        articleList(cid);
    }

    public void refresh(int cid)
    {
        page = 0;
        refresh = true;
        articleList(cid);
    }

}
