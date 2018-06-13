package com.kolarbear.wanandroid.app;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.Utils;
import com.kolarbear.wanandroid.BuildConfig;
import com.kolarbear.wanandroid.bean.home.DaoMaster;
import com.kolarbear.wanandroid.bean.home.DaoSession;
import com.kolarbear.wanandroid.constant.Constant;
import com.kolarbear.wanandroid.di.component.AppComponent;
import com.kolarbear.wanandroid.di.component.DaggerAppComponent;
import com.kolarbear.wanandroid.di.module.ApplicationModule;
import com.kolarbear.wanandroid.di.module.ClientModule;
import com.kolarbear.wanandroid.di.module.ServiceModule;
import com.kolarbear.wanandroid.http.HttpGlobalHandler;
import com.squareup.leakcanary.LeakCanary;

import me.yokeyword.fragmentation.Fragmentation;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

/**
 *  本项目由
 *  mvp
 *  +dagger2
 *  +retrofit
 *  +rxjava
 *  +butterknife
 *  +rxlifecycle组成
 * Created by Administrator on 2018/5/15.
 */

public class App extends Application {

    private AppComponent appComponent;
    private static final String TAG = "App";
     public static Context context;
    private SQLiteDatabase database;
    private DaoSession daoSession;
    private static App app;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        app = this;
        LeakCanary.install(this);//内存泄漏检测
        //初始化工具类
        Utils.init(this);
        initARouter();
        if (BuildConfig.DEBUG)
        {
            Timber.plant(new Timber.DebugTree());
        }
        initAppComponent();
        initFragmentation();
        initThreadExceptionHandler();
        initGreenDao();
    }

    private void initGreenDao() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "home-db");
        database = devOpenHelper.getWritableDatabase();
        daoSession = new DaoMaster(database).newSession();
    }

    public static App getApp()
    {
        return app;
    }

    public SQLiteDatabase getDatabase(){
        return database;
    }

    public DaoSession getDaoSession(){
        return daoSession;
    }


    private void initThreadExceptionHandler() {
        final Thread.UncaughtExceptionHandler uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                Log.e(TAG, "uncaughtException: ThreadId>"+t.getId()+"message>"+e.toString());
                if (uncaughtExceptionHandler!=null)
                {
                    uncaughtExceptionHandler.uncaughtException(t, e);
                }
            }
        };
        Thread.setDefaultUncaughtExceptionHandler(handler);
    }


    public static Context getContext()
    {
        return context;
    }

    private void initARouter() {
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }

    private void initFragmentation() {
        Fragmentation.builder()
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(BuildConfig.DEBUG)
                .install();
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .clientModule(getClientModule())
                .serviceModule(new ServiceModule())
                .build();
    }

    public AppComponent appComponent()
    {
        return appComponent;
    }

    private ClientModule getClientModule(){
        return ClientModule.builder()
                .baseUrl(Constant.BASE_URL)
                .globalHandler(new HttpGlobalHandler() {
                    @Override
                    public Request beforeRequest(Interceptor.Chain chain, Request request) {
                        return request;
                    }

                    @Override
                    public Response beforeResponse(String result, Interceptor.Chain chain, Response response) {
                        return response;
                    }
                })
                .inteceptors(getInteceptors())
                .build();
    }

    private Interceptor[] getInteceptors()
    {
        return null;
    }

}
