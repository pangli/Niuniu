package com.zorro.core.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.zorro.core.presenter.BasePresenter;

/**
 * Created by pangli on 2017/1/20.
 * 备注：懒加载fragment
 */

public abstract class LazyFragment<V, P extends BasePresenter<V>> extends MVPBaseFragment<V, P> {
    /////////////////
    //懒加载
    ////////////
    // 标识已经触发过懒加载数据
    private boolean hasFetchData;
    // 标志位，标志Fragment已经初始化完成。
    private boolean isPrepared;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            lazyFetchDataIfPrepared();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isPrepared = true;
        lazyFetchDataIfPrepared();
    }


    private void lazyFetchDataIfPrepared() {
        // 用户可见fragment && 没有加载过数据 && 视图已经准备完毕
        if (getUserVisibleHint() && !hasFetchData && isPrepared) {
            hasFetchData = true; //已加载过数据
            lazyFetchData();
        }
    }

    @Override
    public void startInvoke(View view) {
    }

    /**
     * 懒加载的方式获取数据，仅在满足fragment可见和视图已经准备好的时候调用一次
     */
    protected abstract void lazyFetchData();

    public void RefreshFragment() {
    }

    @Override
    public void onDestroyView() {
        hasFetchData = false;
        isPrepared = false;
        super.onDestroyView();
    }
}
