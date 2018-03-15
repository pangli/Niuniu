package com.zorro.core.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zorro.core.R;


/**
 * Created by pangli on 2017/04/26.
 * 备注：网络异常Toast提示
 */
public class Toasts {
    private View view;
    private Toast toast;
    private TextView textView;
    private ImageView imageView;
    private LinearLayout.LayoutParams params;

    public void toast(Context context, int id, String content) {
        view = LayoutInflater.from(context).inflate(R.layout.toast_layout, null);
        textView = (TextView) view.findViewById(R.id.toast_text);
        imageView = (ImageView) view.findViewById(R.id.toast_img);
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams
                .WRAP_CONTENT);
        if (toast == null) {
            toast = new Toast(context);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, Gravity.CENTER, Gravity.CENTER);
        }
        imageView.setBackgroundResource(id);
        textView.setText(content);
        toast.setView(view);
        toast.show();
    }

}
