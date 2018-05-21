package com.kolarbear.wanandroid.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;

import com.kolarbear.wanandroid.R;
import com.kolarbear.wanandroid.base.BaseFragment;
import com.kolarbear.wanandroid.ui.home.HomeFragment;
import com.kolarbear.wanandroid.ui.knowledge.KnowledgeFragment;

import butterknife.BindView;
import me.yokeyword.fragmentation.ISupportFragment;

/**
 *
 * Created by Administrator on 2018/5/21.
 */

public class MainFragment extends BaseFragment implements BottomNavigationView.OnNavigationItemSelectedListener{

    @BindView(R.id.bottomNavigation)
    BottomNavigationView bottomNavigation;
    private ISupportFragment[] mFragments = new ISupportFragment[2];
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void initInject() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView(View view) {
        bottomNavigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (findChildFragment(HomeFragment.class)==null)
        {
            mFragments[FIRST] = HomeFragment.newInstance();
            mFragments[SECOND] = KnowledgeFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_tab_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND]);
        }else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题

            // 这里我们需要拿到mFragments的引用,也可以通过getChildFragmentManager.findFragmentByTag自行进行判断查找(效率更高些),用下面的方法查找更方便些
            mFragments[FIRST] = findChildFragment(HomeFragment.class);
            mFragments[SECOND] = findChildFragment(KnowledgeFragment.class);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.nav_home:
                showHideFragment(mFragments[0],mFragments[1]);
                _mActivity.setToolbarText("玩Android");
                break;
            case R.id.nav_knowledge:
                showHideFragment(mFragments[1],mFragments[0]);
                _mActivity.setToolbarText("知识体系");
                break;
        }
        return true;
    }
}
