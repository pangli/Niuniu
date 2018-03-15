package com.zorro.core.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by pangli on 2016/12/22.
 * 备注：
 */

public abstract class BaseActivity extends GlobalStateActivity {
    /**
     * 设置activity的布局
     *
     * @see android.support.v7.app.AppCompatActivity#setContentView(int)
     * @see android.support.v7.app.AppCompatActivity#setContentView(android.view.View)
     */
    public abstract void setView();

    /**
     * 初始化使用的各个类
     */
    public abstract void initWidget();

    /**
     * 执行动作
     */
    public abstract void startInvoke();

    protected void setView(Bundle savedInstanceState) {
    }

    protected Context mContext;
    protected Intent mIntent;
    protected Uri mUri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();
        setView(savedInstanceState);
        mContext = this;
        ButterKnife.bind(this);
        initWidget();
        startInvoke();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        mIntent = getIntent();
        if (mIntent != null) {
            mUri = mIntent.getData();
        }
        if (mUri != null) {
            processExtraData();
        }
    }

    /**
     * onNewIntent触发后逻辑处理
     */
    protected void processExtraData() {
    }

    protected void setGone(View targetView) {
        if (null == targetView) {
            return;
        }
        if (targetView.getVisibility() != View.GONE) {
            targetView.setVisibility(View.GONE);
        }
    }

    protected void setVisible(View targetView) {
        if (null == targetView) {
            return;
        }
        if (targetView.getVisibility() != View.VISIBLE) {
            targetView.setVisibility(View.VISIBLE);
        }
    }

    protected void setInvisible(View targetView) {
        if (null == targetView) {
            return;
        }
        if (targetView.getVisibility() != View.INVISIBLE) {
            targetView.setVisibility(View.INVISIBLE);
        }
    }
}
