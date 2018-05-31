package com.kolarbear.wanandroid.ui.collectlist;

import com.kolarbear.wanandroid.api.ApiResult;
import com.kolarbear.wanandroid.base.BasePresenter;
import com.kolarbear.wanandroid.base.interfac.IView;
import com.kolarbear.wanandroid.bean.BaseBean;
import com.kolarbear.wanandroid.bean.collect.CollectArticle;
import com.kolarbear.wanandroid.utils.RxScheduler;
import com.kolarbear.wanandroid.utils.Utils;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/5/31.
 */

public class CollectListPresenter extends BasePresenter<CollectListContract.View> {


    @Inject
    public CollectListPresenter(IView view) {
        super((CollectListContract.View)view);
    }

    /**
     * 收藏的文章列表
     */
    public void collectList()
    {
        service.collectList(page)
                .compose(getView().<BaseBean<CollectArticle>>bindToLife())
                .compose(RxScheduler.<BaseBean<CollectArticle>>applySchedulers())
                .subscribe(new ApiResult<CollectArticle>() {
                    @Override
                    public void onFail(Throwable e) {

                    }

                    @Override
                    public void onSuccess(BaseBean<CollectArticle> t) {
                        if (refresh)
                        {
                            getView().showList(t, Utils.TYPE_REFRESH);
                        }else {
                            getView().showList(t, Utils.TYPE_LOAD_MORE);
                        }
                    }
                });

    }
    public void more()
    {
        page++;
        refresh = false;
        collectList();
    }

    public void refresh()
    {
        page = 0;
        refresh = true;
        collectList();
    }

}
