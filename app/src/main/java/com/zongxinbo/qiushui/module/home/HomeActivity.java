package com.zongxinbo.qiushui.module.home;

import android.os.Bundle;

import com.zongxinbo.qiushui.R;
import com.zongxinbo.qiushui.base.BaseActivity;

/**
 * 主页面
 */
public class HomeActivity extends BaseActivity implements HomeContract.View {

    private HomePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initToolBar() {
        mToolbar.setTitle(R.string.toolbar_home);
    }

    @Override
    protected void initMVP() {
        mModelList.add(new HomeModel());
        mViewList.add(this);
        mPresenterList.add(new HomePresenter());
    }

    @Override
    public void initView() {

    }

    @Override
    public void setPresenter(HomePresenter presenter) {
        mPresenter = presenter;
    }

}
