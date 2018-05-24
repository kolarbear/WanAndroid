package com.kolarbear.wanandroid.ui.article;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.ChromeClientCallbackManager;
import com.kolarbear.wanandroid.R;
import com.kolarbear.wanandroid.base.BaseActivity;
import com.kolarbear.wanandroid.constant.Constant;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/5/24.
 */
@Route(path = "/article/ArticleActivity")
public class ArticleActivity extends BaseActivity {


    @Autowired
    int id;
    @Autowired
    String title;
    @Autowired
    String author;
    @Autowired
    String url;


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.web_content)
    FrameLayout webContent;

    @Override
    protected void initData() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initView() {
        AgentWeb.with(this)
                .setAgentWebParent(webContent,new LinearLayout.LayoutParams(-1,-1))
                .useDefaultIndicator()
                .defaultProgressBarColor()
                .setReceivedTitleCallback(mReceivedTitleCallback)
                .createAgentWeb()
                .ready()
                .go(url);
    }

    @Override
    protected void initInject() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.article_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.menuShare:
                Intent intent1 = new Intent(Intent.ACTION_SEND);
                intent1.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_article_url, getString(R.string.app_name), title, url));
                intent1.setType("text/plain");
                startActivity(intent1);
                break;
            case R.id.menuCollect:
                ToastUtils.showShort("收藏");
                break;
            case R.id.menuSystemBrowser:
                Intent intent2 = new Intent("android.intent.action.VIEW");
                intent2.setData(Uri.parse(url));
                startActivity(intent2);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_article;
    }

    public static void startArticle(int id, String title, String author, String url)
    {
        ARouter.getInstance().build("/article/ArticleActivity")
                .withInt(Constant.CONSTANT_ID,id)
                .withString(Constant.CONSTANT_TITLE,title)
                .withString(Constant.CONSTANT_AUTHOR,author)
                .withString(Constant.CONSTANT_URL,url)
                .navigation();
    }
    private ChromeClientCallbackManager.ReceivedTitleCallback mReceivedTitleCallback = new ChromeClientCallbackManager.ReceivedTitleCallback() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            toolbar.setTitle(title);
        }
    };
}
