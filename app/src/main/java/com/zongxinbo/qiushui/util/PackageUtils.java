package com.zongxinbo.qiushui.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.zongxinbo.qiushui.global.App;

import java.io.File;

/**
 * @author 宗新博 on 2016/6/13 16:20
 */
public class PackageUtils {

    private static Context mContext = App.getmContext();

    /**
     * 获取包信息
     * @return 包信息对象
     */
    public static PackageInfo getPackageInfo() {
        PackageInfo pi = null;
        try {
            PackageManager pm = mContext.getPackageManager();
            if (pm != null) {
                pi = pm.getPackageInfo(mContext.getPackageName(),
                        PackageManager.GET_CONFIGURATIONS);
            }
            return pi;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return pi;
    }

    /**
     * 获取版本名
     * @return 版本名
     */
    public static String getVersionName() {
        return getPackageInfo().versionName;
    }

    /**
     * 获取版本号
     * @return 版本号
     */
    public static int getVersionCode() {
        return getPackageInfo().versionCode;
    }

    /**
     * 安装apk
     * @param filePath apk的文件路径
     * @return 是否成功安装
     */
    public static boolean installApk(String filePath) {

        File file = new File(filePath);

        if (file == null || !file.exists() || !file.isFile()) {
            return false;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
        return true;
    }
}
