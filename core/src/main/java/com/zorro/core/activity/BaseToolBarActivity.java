package com.zorro.core.activity;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zorro.core.R;
import com.zorro.core.presenter.BasePresenter;


/**
 * Created by pangli on 2016/12/22.
 * 备注：Toolbar
 */

public abstract class BaseToolBarActivity<V, P extends BasePresenter<V>> extends MVPBaseActivity<V, P> implements
        Toolbar.OnMenuItemClickListener {
    View mRootPannel;
    Toolbar mToolbar;
    FrameLayout mContentPannel;
    TextView tool_bar_title;

    /**
     * 获取布局文件
     *
     * @return
     */
    public abstract int getLayoutResID();

    private void setupView() {
        mRootPannel = View.inflate(this, R.layout.activity_tool_bar_base, null);
        mContentPannel = (FrameLayout) mRootPannel.findViewById(R.id.content_pannel);
    }


    @Override
    public void setView() {
        setupView();
        mContentPannel.removeAllViews();
        mContentPannel.addView(View.inflate(this, getLayoutResID(), null));
        setContentView(mRootPannel);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        mToolbar = (Toolbar) mRootPannel.findViewById(R.id.tool_bar);
        mToolbar.setOnMenuItemClickListener(this);
        tool_bar_title = (TextView) mRootPannel.findViewById(R.id.tool_bar_title);
    }

    @Override
    public void setTitle(CharSequence title) {
        tool_bar_title.setText(title);
    }

    @Override
    public void setTitle(int titleId) {
        tool_bar_title.setText(titleId);
    }

    public void onLeftClick(View view) {
        finish();
    }

    public void setLeftIcon(@DrawableRes int resId) {
        mToolbar.setNavigationIcon(resId);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLeftClick(v);
            }
        });
    }

    public void setLeftIcon(Drawable drawable) {
        mToolbar.setNavigationIcon(drawable);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLeftClick(v);
            }
        });
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


}
