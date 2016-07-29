package com.zongxinbo.qiushui.module.home;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.zongxinbo.qiushui.R;
import com.zongxinbo.qiushui.base.BaseActivity;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主页面
 */
public class HomeActivity extends BaseActivity implements HomeContract.View, View.OnClickListener {

    @BindView(R.id.home_et_search)
    EditText mHomeEtSearch;
    @BindView(R.id.home_btn_display)
    Button mHomeBtnDisplay;
    @BindView(R.id.home_gv_copybook)
    GridView mHomeGvCopybook;
    @BindView(R.id.home_add_copybook)
    Button mHomeAddCopybook;
    private HomePresenter mPresenter;
    private String mAddText;

    @BindString(R.string.home_photo)
    String mStringHomePhoto;
    @BindString(R.string.home_gallery)
    String mStringHomeGallery;
    @BindString(R.string.home_file)
    String mStringHomeFile;
    private String mSearchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initToolBar() {
        mToolbar.setTitle(R.string.app_name);
    }

    @Override
    protected void initMVP() {
        mModelList.add(new HomeModel());
        mViewList.add(this);
        mPresenterList.add(new HomePresenter());
    }

    @Override
    public void initView() {
        mHomeBtnDisplay.setOnClickListener(this);
        mHomeAddCopybook.setOnClickListener(this);

        mHomeEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void setPresenter(HomePresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_btn_display:
                mSearchText = mHomeEtSearch.getText().toString();
                break;
            case R.id.home_add_copybook:
                openSetTextDialog();
                break;
            default:
                break;
        }
    }

    @Override
    public void openSetTextDialog() {
        final EditText editText = new EditText(this);
//        editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
//        View view = getLayoutInflater().inflate(R.layout.item_input, null);
//        final EditText editText = (EditText) view.findViewById(R.id.item_et_add_text);

        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle(R.string.home_please_input_add_text)
                .setView(editText)
                .setNegativeButton(R.string.cancel, null)
//                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int i) {
//                        mAddText = editText.getText().toString();
//
//                        if (TextUtils.isEmpty(mAddText)) {
//                            UIUtils.toast(R.string.home_input_cannot_be_empty);
//                        } else {
//                            dialog.dismiss();
//                            openAddCopybookDialog();
//                        }
//                    }
//                })
                .setPositiveButton(R.string.confirm, null)
                .show();
    }

    @Override
    public void openAddCopybookDialog() {

        String[] choiceItems = new String[]{mStringHomePhoto, mStringHomeGallery, mStringHomeFile};

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.item_choice, choiceItems);

        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setSingleChoiceItems(adapter, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                // 相机拍照
//                                TakePhoto();
                                break;
                            case 1:
                                // 本地相册
//                                PickPhotoFromGallery();
                                break;
                            case 2:
                                // 本地文件
                                break;
                        }
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }
}
