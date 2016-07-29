package com.zongxinbo.qiushui.module.home;

import android.Manifest;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.CropOptions;
import com.zongxinbo.qiushui.R;
import com.zongxinbo.qiushui.base.BaseActivity;
import com.zongxinbo.qiushui.util.UIUtils;

import java.io.File;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

/**
 * 主页面
 */
@RuntimePermissions
public class HomeActivity extends BaseActivity implements HomeContract.View, View.OnClickListener {

    @BindView(R.id.home_et_search)
    EditText mHomeEtSearch;
    @BindView(R.id.home_btn_display)
    Button mHomeBtnDisplay;
    @BindView(R.id.home_gv_copybook)
    GridView mHomeGvCopybook;
    @BindView(R.id.home_btn_add_copybook)
    Button mHomeBtnAddCopybook;
    private HomePresenter mPresenter;
    private String mAddText;

    @BindString(R.string.home_photo)
    String mStringHomePhoto;
    @BindString(R.string.home_gallery)
    String mStringHomeGallery;
    @BindString(R.string.home_file)
    String mStringHomeFile;
    private String mSearchText;
    private TakePhotoImpl mTakePhoto;
    private String mCopybookSaveName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initTakePhoto();
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

    public void initTakePhoto() {
        mTakePhoto = new TakePhotoImpl(this, new TakePhoto.TakeResultListener() {
            @Override
            public void takeSuccess(String imagePath) {

            }

            @Override
            public void takeFail(String msg) {

            }

            @Override
            public void takeCancel() {

            }
        });
    }

    @Override
    public void initView() {

        mHomeBtnDisplay.setOnClickListener(this);
        mHomeBtnAddCopybook.setOnClickListener(this);

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
            case R.id.home_btn_add_copybook:
                openSetTextDialog();
                break;
            default:
                break;
        }
    }

    @Override
    public void openSetTextDialog() {
        final EditText editText = new EditText(this);
        editText.setGravity(Gravity.CENTER);

        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle(R.string.home_please_input_add_text)
                .setView(editText)
                .setNegativeButton(R.string.cancel, null)
                .setPositiveButton(R.string.confirm, null)
                .show();

        Button button = dialog.getButton(DialogInterface.BUTTON_POSITIVE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCopybookSaveName = editText.getText().toString();

                if (TextUtils.isEmpty(mCopybookSaveName)) {
                    UIUtils.toast(R.string.home_input_cannot_be_empty);
                } else {
                    dialog.dismiss();
//                    SPUtils.putString(GlobalConstants.COPYBOOK_SAVE_NAME, mCopybookSaveName);
                    openAddCopybookDialog();
                }
            }
        });
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
                        File file=new File(Environment.getExternalStorageDirectory(), "/quishui/"+ mCopybookSaveName + ".jpg");
                        if (!file.getParentFile().exists())file.getParentFile().mkdirs();
                        Uri imageUri = Uri.fromFile(file);
                        CropOptions cropOptions = new CropOptions.Builder().setAspectX(1).setAspectY(1).setWithOwnCrop(true).create();
                        switch (which) {
                            case 0:
                                // 相机拍照
                                mTakePhoto.onPickFromCaptureWithCrop(imageUri, cropOptions);
                                break;
                            case 1:
                                // 本地相册
                                mTakePhoto.onPickFromGalleryWithCrop(imageUri, cropOptions);
                                break;
                            case 2:
                                // 本地文件
                                mTakePhoto.onPickFromDocumentsWithCrop(imageUri, cropOptions);
                                break;
                        }
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    void checkCamera() {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        HomeActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}
