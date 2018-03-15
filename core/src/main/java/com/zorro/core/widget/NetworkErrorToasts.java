package com.zorro.core.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.zorro.core.R;


/**
 * Created by pangli on 2017/04/26.
 * 备注：单例模式网络异常Toast提示
 */
public class NetworkErrorToasts {
    private Toast toast;
    private static NetworkErrorToasts toasts;
    private static Context mContext;

    public static NetworkErrorToasts getInstance(Context context) {
        if (toasts == null) {
            synchronized (NetworkErrorToasts.class) {
                if (toasts == null) {
                    toasts = new NetworkErrorToasts();
                }
            }
        }
        if (null != context) {
            mContext = context.getApplicationContext();
        }
        return toasts;
    }

    public synchronized void show() {
        if (toast == null) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.toast_layout, null);
            toast = new Toast(mContext);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, Gravity.CENTER, Gravity.CENTER);
            toast.setView(view);
            toast.show();
        } else {
            toast.show();
        }

    }

}
