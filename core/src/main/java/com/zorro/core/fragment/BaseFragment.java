package com.zorro.core.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by pangli on 2016/12/22.
 * 备注：
 */
public abstract class BaseFragment extends GlobalStateFragment {
    protected View view;
    protected LayoutInflater mInflater;
    protected Context mContext;
    private Unbinder unbinder;

    /**
     * 创建视图，将此fragment与布局文件绑定
     *
     * @return 布局文件的视图
     */
    public abstract View onCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * 初始化控件，通常是findView或执行new方法
     *
     * @param view 布局文件的视图
     */
    public abstract void initWidget(View view);

    /**
     * 开始进行操作，对控件、对象进行操作在此完成
     *
     * @param view 布局文件的视图
     */
    public abstract void startInvoke(View view);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInflater = inflater;
        mContext = getActivity();
        view = onCreate(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        initWidget(view);
        startInvoke(view);
        return view;
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

    @Override
    public void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }
}
