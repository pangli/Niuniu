package com.zorro.core;

import android.app.Application;
import android.content.Context;

/**
 * Created by pangli on 2018/3/14 18:15
 * 备注：
 */

public class BaseApplication extends Application {
    public static Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
    }


}
