package com.kolarbear.wanandroid.ui.search;

import com.kolarbear.wanandroid.api.ApiResult;
import com.kolarbear.wanandroid.base.BasePresenter;
import com.kolarbear.wanandroid.base.interfac.IView;
import com.kolarbear.wanandroid.bean.BaseBean;
import com.kolarbear.wanandroid.bean.search.SearchResult;
import com.kolarbear.wanandroid.utils.RxScheduler;
import com.kolarbear.wanandroid.utils.Utils;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/6/1.
 */

public class SearchPresenter extends BasePresenter<SearchContract.View> {

    @Inject
    public SearchPresenter(IView view) {
        super((SearchContract.View) view);
    }

    public void search(String k)
    {
        service.searchK(page,k)
                .compose(getView().<BaseBean<SearchResult>>bindToLife())
                .compose(RxScheduler.<BaseBean<SearchResult>>applySchedulers())
                .subscribe(new ApiResult<SearchResult>() {
                    @Override
                    public void onSuccess(BaseBean<SearchResult> t) {
                        if (refresh)
                        getView().showResult(t.data, Utils.TYPE_REFRESH);
                        else getView().showResult(t.data,Utils.TYPE_LOAD_MORE);
                    }

                    @Override
                    public void onFail(Throwable e) {

                    }
                });
    }
    public void more(String k)
    {
        page++;
        refresh = false;
        search(k);
    }

    public void refresh(String k)
    {
        page = 0;
        refresh = true;
        search(k);
    }
}
