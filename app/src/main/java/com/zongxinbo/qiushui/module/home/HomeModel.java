package com.zongxinbo.qiushui.module.home;

import android.content.Context;
import android.os.Environment;

import com.zongxinbo.qiushui.global.App;

import java.io.File;

/**
 * @author 宗新博 on 2016/6/4 22:18
 */
public class HomeModel implements HomeContract.Model {

    private static final String TAG = "HomeModel";
    private Context mContext = App.getmContext();

    @Override
    public void createDirectoryForQiushui() {
        App.dir = new File(
                Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES), "qiushui");
        if (!App.dir.exists()) {
            App.dir.mkdirs();
        }
    }
}
