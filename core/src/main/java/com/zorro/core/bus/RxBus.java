package com.zorro.core.bus;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by pangli on 2018/3/14 16:28
 * 备注：   RxBus2
 */
public class RxBus {
    private volatile static RxBus instance;
    private final Subject<Object> mBus;

    private RxBus() {
        mBus = PublishSubject.create().toSerialized();
    }

    public static RxBus getInstance() {
        if (instance == null) {
            synchronized (RxBus.class) {
                if (instance == null) {
                    instance = new RxBus();
                }
            }
        }
        return instance;
    }

    /**
     * 发送事件
     */
    public void post(Object obj) {
        mBus.onNext(obj);
    }

    /**
     * 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
     */
    public <T> Observable<T> registerObservable(final Class<T> tClass) {
        return mBus.ofType(tClass).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Object> registerObservable() {
        return mBus;
    }

    /**
     * 判断是否有订阅者
     */
    public boolean hasObservers() {
        return mBus.hasObservers();
    }


}
