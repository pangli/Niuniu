package com.zorro.core.fragment;

import android.view.View;

import com.zorro.core.presenter.BasePresenter;

/**
 * Created by pangli on 2016/12/22.
 * 备注：MvpFragment
 */

public abstract class MVPBaseFragment<V, P extends BasePresenter<V>> extends BaseFragment {

    /**
     * Presenter对象
     */
    protected P mPresenter;

    @Override
    public void initWidget(View view) {
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.onCreate();
            mPresenter.attach((V) this);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (null != mPresenter) {
            mPresenter.onStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (null != mPresenter) {
            mPresenter.onResume();
        }
    }

    @Override
    public void onPause() {
        if (null != mPresenter) {
            mPresenter.onPause();
        }
        super.onPause();
    }

    @Override
    public void onStop() {
        if (null != mPresenter) {
            mPresenter.onStop();
        }
        super.onStop();
    }

    @Override
    public void onDestroy() {
        if (null != mPresenter) {
            mPresenter.onDestroy();
        }
        super.onDestroy();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    /**
     * 创建此activity使用的Presenter
     *
     * @return Presenter，若不使用则为null
     */
    protected abstract P createPresenter();
}
