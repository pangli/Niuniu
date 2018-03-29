package com.zorro.core.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.zorro.core.bus.MsgEvent;
import com.zorro.core.bus.RxBus;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by pangli on 2017/4/12.
 * 备注：
 */

public class GlobalStateFragment extends Fragment implements EasyPermissions.PermissionCallbacks {
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
            grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    //申请成功时调用
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    //申请失败时调用
    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }
}
