package com.kolarbear.wanandroid.ui.knowledge;

import com.kolarbear.wanandroid.base.BasePresenter;
import com.kolarbear.wanandroid.base.interfac.IView;
import com.kolarbear.wanandroid.bean.BaseBean;
import com.kolarbear.wanandroid.bean.knowledge.KnowledgeBean;
import com.kolarbear.wanandroid.utils.RxScheduler;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * 知识体系
 * Created by Administrator on 2018/5/22.
 */

public class KnowledgePresenter extends BasePresenter<KnowledgeContract.View>{

    @Inject
    public KnowledgePresenter(IView view) {
        super((KnowledgeContract.View)view);
    }

    /**
     * 获取知识体系
     */
    public void getKnowledgeTree()
    {
        service.knowledgeTree()
                .compose(getView().<BaseBean<List<KnowledgeBean>>>bindToLife())
                .compose(RxScheduler.<BaseBean<List<KnowledgeBean>>>applySchedulers())
                .subscribe(new Consumer<BaseBean<List<KnowledgeBean>>>() {
                    @Override
                    public void accept(BaseBean<List<KnowledgeBean>> knowledgeBeanBaseBean) throws Exception {
                            if (knowledgeBeanBaseBean.errorCode==0)
                            {
                                getView().showKnowledgeTree(knowledgeBeanBaseBean.data);
                            }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

}
