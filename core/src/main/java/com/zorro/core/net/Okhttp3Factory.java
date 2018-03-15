package com.zorro.core.net;


import com.zorro.core.BaseApplication;
import com.zorro.core.BuildConfig;
import com.zorro.core.utils.NetworkUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by pangli on 2018/3/15 10:01
 * 备注： okhttp配置
 */
public class Okhttp3Factory {
    private static final long TIMEOUT = 500;

    /**
     * 设置OkHttpClient进行网络缓存，但只对get请求有效,并设置在debug模式下打印请求日志
     */
    public static OkHttpClient getNewClient() {
        File httpCacheDirectory = new File(BaseApplication.applicationContext.getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);
        OkHttpClient okHttpClient = new OkHttpClient();
        OkHttpClient.Builder builder = okHttpClient.newBuilder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .cache(cache)
                .retryOnConnectionFailure(true)
                .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }
        return builder.build();
    }

    /**
     * 网络缓存只对get请求有效
     */
    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetworkUtils.isNetworkAvailable(BaseApplication.applicationContext)) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response = chain.proceed(request);
            if (NetworkUtils.isNetworkAvailable(BaseApplication.applicationContext)) {
                int maxAge = 60;// 有网络时 设置缓存超时时间0秒, 即直接请求网络
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // 无网络时，设置超时为4周
                response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build();
            }
            return response;
        }
    };


}
