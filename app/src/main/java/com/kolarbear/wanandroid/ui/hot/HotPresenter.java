package com.kolarbear.wanandroid.ui.hot;

import com.kolarbear.wanandroid.api.ApiResult;
import com.kolarbear.wanandroid.base.BasePresenter;
import com.kolarbear.wanandroid.base.interfac.IView;
import com.kolarbear.wanandroid.bean.BaseBean;
import com.kolarbear.wanandroid.bean.hot.HotBean;
import com.kolarbear.wanandroid.bean.hot.WebsiteBean;
import com.kolarbear.wanandroid.utils.RxScheduler;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/6/1.
 */

public class HotPresenter extends BasePresenter<HotContract.View> {

    @Inject
    public HotPresenter(IView view) {
        super((HotContract.View)view);
    }

    public void hotWords()
    {
        service.hotWords()
                .compose(getView().<BaseBean<List<HotBean>>>bindToLife())
                .compose(RxScheduler.<BaseBean<List<HotBean>>>applySchedulers())
                .subscribe(new ApiResult<List<HotBean>>() {
                    @Override
                    public void onSuccess(BaseBean<List<HotBean>> t) {
                        getView().showHot(t.data);
                    }

                    @Override
                    public void onFail(Throwable e) {

                    }
                });
    }

    public void websites()
    {
        service.commonSites()
                .compose(getView().<BaseBean<List<WebsiteBean>>>bindToLife())
                .compose(RxScheduler.<BaseBean<List<WebsiteBean>>>applySchedulers())
                .subscribe(new ApiResult<List<WebsiteBean>>() {
                    @Override
                    public void onSuccess(BaseBean<List<WebsiteBean>> t) {
                        getView().showSites(t.data);
                    }

                    @Override
                    public void onFail(Throwable e) {

                    }
                });
    }
}
