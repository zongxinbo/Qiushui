package com.zongxinbo.qiushui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.zongxinbo.qiushui.R;

import java.util.ArrayList;

/**
 * Created by 宗新博 on 2016/6/1 16:05.
 */
public abstract class BaseActivity extends AppCompatActivity {

    public ArrayList<BaseModel> mModelList = new ArrayList<>();
    public ArrayList<BaseView> mViewList = new ArrayList<>();
    public ArrayList<BasePresenter> mPresenterList = new ArrayList<>();

    public Menu menu;
    public Toolbar mToolbar;
    private int menuRes = R.menu.menu_main;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMVP();
        attach();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 调用所有子类对应的Presenter中的start()
        for (int i = 0; i < mPresenterList.size(); i++) {
            mPresenterList.get(i).start();
        }

    }

    /**
     * 当activity销毁时,先注销广播接收者,解绑mvp
     */
    @Override
    protected void onDestroy() {
        detach();
        super.onDestroy();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        // 得到包含了toolbar的布局
        View baseView = getLayoutInflater().inflate(R.layout.activity_base,
                null);
        // 获取toolbar
        mToolbar = (Toolbar) baseView.findViewById(R.id.toolbar);
        // 得到存放内容的控件
        FrameLayout contentView = (FrameLayout) baseView
                .findViewById(R.id.fl_content);
        // 加载子类setContentView中传递进来的布局文件
        View childView = getLayoutInflater().inflate(layoutResID, null);
        // 将子类需要显示的内容放到flContent中进行显示
        contentView.addView(childView);

        initToolBar();
        setToolBar();

        super.setContentView(baseView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(menuRes, menu);
        this.menu = menu;
        initMenu();
        return super.onCreateOptionsMenu(menu);
    }

    protected void initMenu() {
    }

    protected void initToolBar() {
    }

    /**
     * 给toolbar设置menu item点击事件,并进行处理
     */
    private void setToolBar() {
        setSupportActionBar(mToolbar);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
//                    case :
//                        break;
                    default:
                        break;
                }

                return true;
            }
        });
    }

    /**
     * 让子类将其对应的Model View Presenter加到基类的集合中
     */
    protected abstract void initMVP();

    /**
     * 将View和Model的对象绑定到对应的Presenter上
     */
    protected void attach() {
        for (int i = 0; i < mPresenterList.size(); i++) {
            mPresenterList.get(i).attach(mModelList.get(i), mViewList.get(i));
        }
    }

    /**
     * Presenter解绑其持有的对应的View和Model对象,以免造成内存泄漏
     */
    protected void detach() {
        for (int i = 0; i < mPresenterList.size(); i++) {
            mPresenterList.get(i).detach();
        }
    }

    /**
     * 子类初始化View
     */
    public abstract void initView();

}
