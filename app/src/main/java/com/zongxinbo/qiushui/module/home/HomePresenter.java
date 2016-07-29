package com.zongxinbo.qiushui.module.home;

/**
 * @author 宗新博 on 2016/6/4 22:18
 */
public class HomePresenter extends HomeContract.Presenter {

    @Override
    protected void setPresenter() {
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        mModel.createDirectoryForQiushui();
    }

}
