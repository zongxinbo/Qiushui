package com.zongxinbo.qiushui.base;

/**
 * Created by 宗新博 on 2016/5/30 20:33.
 */
public interface BaseView<P> {

    /**
     * 将presenter传入的其对象设置到成员位置,具体由子类实现
     * @param presenter
     */
    void setPresenter(P presenter);

}
