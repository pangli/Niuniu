package com.zorro.core.imageloader;


import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;


/**
 * @Author pangli
 * @Date 2016/12/6
 * @Description 图片加载类
 */
public class ImageUtil {
    /**
     * @param context   Any context, will not be retained.
     * @param imageView
     * @param url       图片地址
     * @Description 图片加载
     * @see #(android.app.Activity)
     * @see #(android.app.Fragment)
     * @see #(android.support.v4.app.Fragment)
     * @see #(android.support.v4.app.FragmentActivity)
     */
    public static void displayImage(Context context, ImageView imageView, String url) {
        GlideApp.with(context)
                .load(url)//图片地址
                .into(imageView);
    }

    /**
     * @param context
     * @param imageView
     * @param url
     * @Description 图片加载---居中裁剪并加圆角
     */
    public static void displayImageCenterCrop(Context context, ImageView imageView, int radius, String url) {
        GlideApp.with(context)
                .load(url)
                .apply(RequestOptions.bitmapTransform(new MultiTransformation<Bitmap>(new CenterCrop(), new RoundedCorners(radius))))//圆角
                .into(imageView);
    }

    /**
     * @param context
     * @param imageView
     * @param url
     * @Description 图片加载---圆形用户图像
     */
    public static void displayUserIcon(Context context, ImageView imageView, String url) {
        GlideApp.with(context)
                .load(url)
                .circleCrop()//圆形
                .into(imageView);
    }

    /**
     * @param context
     * @param imageView
     * @param resourceId
     * @Description 图片加载---圆形用户图像
     */
    public static void displayUserIcon(Context context, ImageView imageView, int resourceId) {
        GlideApp.with(context)
                .load(resourceId)
                .circleCrop()//圆形
                .into(imageView);
    }

    /**
     * @param context
     * @param imageView
     * @param url
     * @Description 图片加载---加圆角图像
     */
    public static void displayRoundCornerImage(Context context, ImageView imageView, int radius, String url) {
        GlideApp.with(context)
                .load(url)
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(radius)))//圆角
                .into(imageView);

    }

    /**
     * @param context
     * @param imageView
     * @param file
     * @Description 图片加载---File
     */
    public static void displayImage(Context context, ImageView imageView, File file) {
        GlideApp.with(context)
                .load(file)
                .into(imageView);
    }

    /**
     * @param context
     * @param imageView
     * @param resourceId
     * @Description 图片加载---resourceId
     */
    public static void displayImage(Context context, ImageView imageView, int resourceId) {
        GlideApp.with(context)
                .load(resourceId)
                .into(imageView);
    }

    /**
     * @param context
     * @param imageView
     * @param uri
     * @Description 图片加载---Uri
     */
    public static void displayImage(Context context, ImageView imageView, Uri uri) {
        GlideApp.with(context)
                .load(uri)
                .into(imageView);
    }

    /**
     * @param context
     * @param imageView
     * @param url
     * @param errorResourceId 加载和加载错误占位图
     * @Description 图片加载---Uri
     */
    public static void displayUserIcon(Context context, ImageView imageView, String url, int errorResourceId) {
        GlideApp.with(context)
                .load(url)
                .placeholder(errorResourceId)
                .error(errorResourceId)
                .circleCrop()//圆形
                .into(imageView);
    }

    /**
     * @param context
     * @param imageView
     * @param resourceId
     * @Description 加载gif图片
     */
    public static void displayGif(Context context, ImageView imageView, int resourceId) {
        GlideApp.with(context)
                .asGif()
                .load(resourceId)
                .into(imageView);
    }
}
