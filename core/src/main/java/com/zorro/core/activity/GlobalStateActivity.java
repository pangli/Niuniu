package com.zorro.core.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.zorro.core.R;
import com.zorro.core.bus.MsgEvent;
import com.zorro.core.bus.RxBus;
import com.zorro.core.widget.Toasts;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by pangli on 2018/3/14 16:31
 * 备注：  activity基类
 */
public class GlobalStateActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    private Disposable disposable;
    private Toasts toasts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscribeGlobalEvents();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (toasts == null) {
            toasts = new Toasts();
        }
    }


    @Override
    protected void onDestroy() {
        unsubscribeGlobalEvents();
        super.onDestroy();
    }

    protected void onNetworkAvailable() {
    }

    protected void onNetworkUnavailable() {
        toasts.toast(this, R.drawable.pic_toast_network_fault, getString(R.string.network_err));
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
