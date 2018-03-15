package com.zorro.core.net;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by pangli on 2016/12/06.
 * 备注：管理网络请求
 */
public enum  HttpRequestManager {
    INSTANCE;
    private String mBaseUrl;

    private BaseRequestApi mRequestApi;

    /**
     * 需要修改{@link Retrofit.Builder#baseUrl}时可调用该方法
     *
     * @param baseUrl
     */
    public synchronized BaseRequestApi init(String baseUrl) {
        if (mRequestApi == null) {
            synchronized (this) {
                if (mRequestApi == null) {
                    mBaseUrl = baseUrl;
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(baseUrl)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .client(Okhttp3Factory.getNewClient())
                            .build();
                    mRequestApi = retrofit.create(BaseRequestApi.class);
                }
            }
        }
        return mRequestApi;
    }

    /**
     * 重置{@link Retrofit.Builder#baseUrl}
     *
     * @param baseUrl
     * @return
     */
    public synchronized BaseRequestApi resetBaseUrl(String baseUrl) {
        synchronized (this) {
            mBaseUrl = baseUrl;
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(Okhttp3Factory.getNewClient())
                    .build();
            mRequestApi = retrofit.create(BaseRequestApi.class);
        }
        return mRequestApi;
    }

    public BaseRequestApi build() {
        return init(mBaseUrl);
    }


}