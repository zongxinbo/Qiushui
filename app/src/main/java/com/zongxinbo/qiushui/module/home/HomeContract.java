package com.zongxinbo.qiushui.module.home;

import com.zongxinbo.qiushui.base.BaseModel;
import com.zongxinbo.qiushui.base.BasePresenter;
import com.zongxinbo.qiushui.base.BaseView;

/**
 * @author 宗新博 on 2016/6/4 22:17
 */
public abstract interface HomeContract {

    interface Model extends BaseModel {

        void createDirectoryForQiushui();

    }

    interface View extends BaseView<HomePresenter> {

        void openSetTextDialog();

        void openAddCopybookDialog();
    }

    abstract class Presenter extends BasePresenter<HomeModel, HomeActivity> {

    }
}
