package com.zorro.core.utils;

import android.os.CountDownTimer;

/**
 * Created by pangli on 2017/7/5.
 * 备注：倒计时
 */

public class CountDownTimerUtils extends CountDownTimer {
    /**
     * @param millisInFuture   The number of millis in the future from the call
     * to {@link #start()} until the countdown is done and {@link #onFinish()}
     * is called.
     * @param onFinishListener Callback fired when the time is up.
     */
    private OnFinishListener onFinishListener;

    public CountDownTimerUtils(long millisInFuture, OnFinishListener onFinishListener) {
        super(millisInFuture, millisInFuture);
        this.onFinishListener = onFinishListener;
    }

    @Override
    public void onTick(long millisUntilFinished) {

    }

    @Override
    public void onFinish() {
        if (onFinishListener != null) {
            onFinishListener.onFinish();
        }
    }

    public interface OnFinishListener {
        void onFinish();
    }
}
