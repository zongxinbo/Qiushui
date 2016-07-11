package com.zongxinbo.qiushui.util;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Process;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;

import com.zongxinbo.qiushui.R;
import com.zongxinbo.qiushui.global.App;

/**
 * UI工具类
 * @author 宗新博 on 2016/5/30 21:15
 */
public class UIUtils {

    /**
     * 获取全局上下文
     * @return
     */
    public static Context getContext() {
        return App.getmContext();
    }

    /**
     * 获取主线程Handler
     * @return
     */
    public static Handler getHandler() {
        return App.getmHandler();
    }

    /**
     * 获取主线程id
     * @return
     */
    public static int getMainThreadId() {
        return App.getmMainThreadId();
    }

    /**
     * dp转px
     * @param dp
     * @return
     */
    public static int dp2px(float dp) {
        return (int) (getContext().getResources().getDisplayMetrics().density * dp + 0.5);
    }

    /**
     * px转dp
     * @param px
     * @return
     */
    public static int px2dp(float px) {
        return (int) (px / getContext().getResources().getDisplayMetrics().density + 0.5);
    }

    /**
     * 判断是否在主线程
     * @return
     */
    public static boolean isOnUIThread() {
        return Process.myTid() == getMainThreadId();
    }

    /**
     * 在主线程进行传入的任务
     * @param r
     */
    public static void runOnUIThread(Runnable r) {
        if (isOnUIThread()) {
            r.run();
        } else {
            getHandler().post(r);
        }
    }

    /**
     * 从传入的id加载出View对象
     * @param resId
     * @return
     */
    public static View inflateView(int resId) {
        return View.inflate(getContext(), resId, null);
    }

    /**
     * Toast工具
     * @param toast 要toast的字符串
     */
    public static void toast(String toast) {
        Toast.makeText(getContext(), toast, Toast.LENGTH_SHORT).show();
    }

    /**
     * Toast工具
     * @param resId 要toast的String id
     */
    public static void toast(int resId) {
        Toast.makeText(getContext(), resId, Toast.LENGTH_SHORT).show();
    }

    /**
     * dialog工具
     * @param context
     * @param messageResId
     * @param listener
     */
    public static void dialog(Context context, @StringRes int messageResId, DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(context)
                .setCancelable(false)
                .setMessage(messageResId)
                .setPositiveButton(R.string.confirm, listener)
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    /**
     * dialog工具
     * @param context
     * @param message
     * @param listener
     */
    public static void dialog(Context context, String message, DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(context)
                .setCancelable(false)
                .setMessage(message)
                .setPositiveButton(R.string.confirm, listener)
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    /**
     * dialog工具
     * @param context
     * @param messageResId
     */
    public static void dialog(Context context, @StringRes int messageResId) {
        new AlertDialog.Builder(context)
                .setCancelable(false)
                .setMessage(messageResId)
                .setPositiveButton(R.string.confirm, null)
                .show();
    }

    /**
     * dialog工具
     * @param context
     * @param message
     */
    public static void dialog(Context context, String message) {
        new AlertDialog.Builder(context)
                .setCancelable(false)
                .setMessage(message)
                .setPositiveButton(R.string.confirm, null)
                .show();
    }

    /**
     * 获取color的值
     * @param resId
     * @return
     */
    public static int getColor(int resId) {
        return getContext().getResources().getColor(resId);
    }
}