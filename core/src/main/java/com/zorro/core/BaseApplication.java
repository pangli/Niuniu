package com.zorro.core;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by pangli on 2018/3/14 18:15
 * 备注：
 */

public abstract class BaseApplication extends Application {
    public static Context applicationContext;

    public abstract void Initialize();

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
        InitializeLogger();
        Initialize();
    }

    /**
     * 初始化log
     */
    private void InitializeLogger() {
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
    }
}
