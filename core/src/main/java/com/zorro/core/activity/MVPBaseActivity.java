package com.zorro.core.activity;


import com.zorro.core.presenter.BasePresenter;

/**
 * Created by pangli on 2016/12/22.
 * 备注：
 */

public abstract class MVPBaseActivity<V, P extends BasePresenter<V>> extends BaseActivity {
    /**
     * Presenter对象
     */
    protected P mPresenter;


    @Override
    public void initWidget() {
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
    protected void onRestart() {
        super.onRestart();
        if (null != mPresenter) {
            mPresenter.onRestart();
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
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        super.onDestroy();
    }

    /**
     * 创建此activity使用的Presenter
     *
     * @return Presenter，若不使用则为null
     */
    protected abstract P createPresenter();
}
