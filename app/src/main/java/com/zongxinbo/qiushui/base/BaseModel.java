package com.zongxinbo.qiushui.base;

/**
 * Created by 宗新博 on 2016/6/1 16:04.
 */
public interface BaseModel {

    /**
     * 通用回调接口
     */
    interface Callback {

        void onSuccess(Object response);

        void onFailure(Object error);
    }

    /**
     * 下载回调接口
     */
    interface downloadCallback {

        void onDownloadError();

        void onProgress(int progress);

        void onFinish(String filePath);
    }
}
