package com.zorro.core.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by pangli on 2017/2/24.
 * 备注：
 */

public abstract class BaseRecyclerViewAdapter<T, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> {
    public BaseRecyclerViewAdapter(int layoutResId, List<T> data) {
        super(layoutResId, data);
    }

    public BaseRecyclerViewAdapter(List<T> data) {
        super(data);
    }

    public BaseRecyclerViewAdapter(int layoutResId) {
        super(layoutResId);
    }
}
