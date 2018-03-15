package com.zorro.core.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.zorro.core.bus.MsgEvent;
import com.zorro.core.bus.RxBus;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by pangli on 2017/4/12.
 * 备注：
 */

public class GlobalStateFragment extends Fragment {
    private Disposable disposable;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscribeGlobalEvents();
    }


    @Override
    public void onDestroy() {
        unsubscribeGlobalEvents();
        super.onDestroy();
    }

    protected void onNetworkAvailable() {
        Log.e("event", "FragmentOnNetworkAvailable");
    }

    protected void onNetworkUnavailable() {
        Log.e("event", "FragmentOnNetworkUnavailable");
    }


    protected void subscribeGlobalEvents() {
        disposable = RxBus.getInstance().registerObservable(MsgEvent.class).subscribe(new Consumer<MsgEvent>() {
            @Override
            public void accept(MsgEvent msgEvent) throws Exception {
                if (msgEvent != null) {
                    if (msgEvent.getType() == 1) {
                        onNetworkAvailable();
                    } else {
                        onNetworkUnavailable();
                    }
                }
            }
        });
    }

    protected void unsubscribeGlobalEvents() {
        if (disposable != null && disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
