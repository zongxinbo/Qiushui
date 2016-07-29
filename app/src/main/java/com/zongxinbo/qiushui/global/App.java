package com.zongxinbo.qiushui.global;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Process;

import java.io.File;

/**
 * @author 宗新博 on 2016/5/30 21:02
 */
public class App extends Application {

    private static Context mContext;
    private static Handler mHandler;
    private static int mMainThreadId;
    public static File file;
    public static File dir;
    public static Bitmap bitmap;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();
        mHandler = new Handler();
        mMainThreadId = Process.myTid();

    }

    /**
     * 获取全局上下文
     * @return
     */
    public static Context getmContext() {
        return mContext;
    }

    /**
     * 创建一个主线程Handler
     * @return
     */
    public static Handler getmHandler() {
        return mHandler;
    }

    /**
     * 获取主线程id
     * @return
     */
    public static int getmMainThreadId() {
        return mMainThreadId;
    }

}
