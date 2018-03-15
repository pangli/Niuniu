package com.zorro.core.presenter;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by pangli on 2016/12/22.
 * 备注：BasePresenter<V>
 */
public abstract class BasePresenter<V> {

    /**
     * View的引用，实际引用的内容通过{@link #obtainView()}获取
     */
    private Reference<V> mViewRef;


    /**
     * 绑定view
     *
     * @param view
     */
    public void attach(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    /**
     * 清除Presenter与View的关联
     */
    private void detachView() {
        if (isAttach()) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    /**
     * Presenter是否已与View层建立了关联
     *
     * @return {@code true} Presenter与View层建立了关联 {@code false}未建立关联
     */
    private boolean isAttach() {
        return mViewRef != null && mViewRef.get() != null;
    }

    /**
     * 获取Presenter关联的View
     *
     * @return V View
     */
    public V obtainView() {
        return isAttach() ? mViewRef.get() : null;
    }


    public void onCreate() {
    }

    public void onStart() {
    }

    public void onRestart() {
    }

    public void onResume() {
    }

    public void onPause() {
    }

    public void onStop() {
    }

    public void onDestroy() {
        detachView();
    }
}
