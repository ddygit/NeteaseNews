package com.example.administrator.neteasenews.app;

import android.app.Application;

import org.xutils.BuildConfig;
import org.xutils.x;

/**
 * Created by Administrator on 2016/10/29.
 */

public class GlobalApplication extends Application {
private static GlobalApplication app;
    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
    }

    public static GlobalApplication getApp() {
        return app;
    }
}
