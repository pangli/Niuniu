package com.zorro.core.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.View;

import com.zorro.core.R;


/**
 * Created by pangli on 2017/3/3.
 * 备注：自定义Dialog
 */

public class CustomDialog {

    private Context mContext;
    private Dialog mDialog;


    public CustomDialog(Context context) {
        mContext = context;
        mDialog = new Dialog(mContext, R.style.DialogActivity);
    }

    public CustomDialog customView(@NonNull View view) {
        mDialog.setContentView(view);

        return this;
    }

    public CustomDialog setCancelable(boolean cancel) {
        mDialog.setCancelable(cancel);
        return this;
    }

    public boolean isShowing() {
        if (mDialog != null) {
            return mDialog.isShowing();
        }
        return false;
    }

    public CustomDialog show() {
        if (mDialog != null) {
            mDialog.show();
        }
        return this;
    }

    public void dismiss() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener listener) {
        if (mDialog != null) {
            mDialog.setOnDismissListener(listener);
        }
    }


}
