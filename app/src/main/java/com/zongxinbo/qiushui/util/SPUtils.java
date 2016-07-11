package com.zongxinbo.qiushui.util;

import android.content.Context;

import com.securepreferences.SecurePreferences;
import com.zongxinbo.qiushui.global.App;
import com.zongxinbo.qiushui.global.GlobalConstants;

/**
 * SharedPreferences工具类
 * @author 宗新博 on 2016/6/1 18:20
 */
public class SPUtils {

    private static final String SHARE_PREFS_NAME = "xyjxc";
    private static Context mContext = App.getmContext();
    private static SecurePreferences mSp = new SecurePreferences(mContext, GlobalConstants.CRYPT_KEY, SHARE_PREFS_NAME);
//    private static SharedPreferences mSp = mContext.getSharedPreferences(SHARE_PREFS_NAME,
//            mContext.MODE_PRIVATE);

    /**
     * 存储boolean
     * @param key
     * @param value
     */
    public static void putBoolean(String key, boolean value) {
        mSp.edit().putBoolean(key, value).commit();
    }

    /**
     * 取出boolean
     * @param key
     * @param defaultValue
     * @return
     */
    public static boolean getBoolean(String key,
                                     boolean defaultValue) {
        return mSp.getBoolean(key, defaultValue);
    }

    /**
     * 存储String
     * @param key
     * @param value
     */
    public static void putString(String key, String value) {
        mSp.edit().putString(key, value).commit();
    }

    /**
     * 取出String
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getString(String key, String defaultValue) {
        return mSp.getString(key, defaultValue);
    }

    /**
     * 存储int
     * @param key
     * @param value
     */
    public static void putInt(String key, int value) {
        mSp.edit().putInt(key, value).commit();
    }

    /**
     * 取出int
     * @param key
     * @param defaultValue
     * @return
     */
    public static int getInt(String key, int defaultValue) {
        return mSp.getInt(key, defaultValue);
    }

    /**
     * 存储long
     * @param key
     * @param value
     */
    public static void putLong(String key, long value) {
        mSp.edit().putLong(key, value).commit();
    }

    /**
     * 取出long
     * @param key
     * @param defaultValue
     * @return
     */
    public static long getLong(String key, long defaultValue) {
        return mSp.getLong(key, defaultValue);
    }

}
