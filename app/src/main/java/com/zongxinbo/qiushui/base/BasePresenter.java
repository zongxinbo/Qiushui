package com.zongxinbo.qiushui.base;

/**
 * Created by 宗新博 on 2016/5/30 20:37.
 */
public abstract class BasePresenter<M, V> {

    public M mModel;
    public V mView;

    /**
     * 绑定Model和View
     * @param m
     * @param v
     */
    public void attach(M m, V v) {
        mModel = m;
        mView = v;
        setPresenter();
    }

    /**
     * 子类实现时,调用对应的View中的setPresenter(),将自身传给View
     */
    protected abstract void setPresenter();

    public abstract void start();

    /**
     * 解绑Model和View
     */
    public void detach() {
        if (mModel != null) {
            mModel = null;
        }

        if (mView != null) {
            mView = null;
        }
    }
}
