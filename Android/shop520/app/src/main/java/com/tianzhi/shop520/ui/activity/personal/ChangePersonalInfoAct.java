package com.tianzhi.shop520.ui.activity.personal;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.tianzhi.shop520.APPLICATION;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.base.BaseFragmentActivity;
import com.tianzhi.shop520.callback.DialogCallback;
import com.tianzhi.shop520.callback.DialogJsonCallback;
import com.tianzhi.shop520.entity.BaseResponse;
import com.tianzhi.shop520.entity.BaseResponseData;
import com.tianzhi.shop520.entity.SimpleResponse;
import com.tianzhi.shop520.entity.login.UserInfo;
import com.tianzhi.shop520.entity.personal.UploadHead;
import com.tianzhi.shop520.ui.diyview.BaseImageView;
import com.tianzhi.shop520.util.ACache;
import com.tianzhi.shop520.util.AppShared;
import com.tianzhi.shop520.util.BaseConstant;
import com.tianzhi.shop520.util.Constants;
import com.tianzhi.shop520.util.ImageUtil;
import com.tianzhi.shop520.util.LogUtils;
import com.tianzhi.shop520.util.PathManager;
import com.tianzhi.shop520.util.PhotoUtils;

import java.io.File;
import java.util.UUID;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

import static com.tianzhi.shop520.R.style.BottomDialog;

/**
 * Created by thinkpad on 2017/10/25.
 *修改用户信息
 */

public class ChangePersonalInfoAct extends BaseFragmentActivity {
    @BindView(R.id.rl_change_headpic)
    RelativeLayout rlChangeHeadpic;
    @BindView(R.id.rl_change_name)
    RelativeLayout rlChangeName;
    @BindView(R.id.rl_change_sex)
    RelativeLayout rlChangeSex;
    @BindView(R.id.iv_head)
    BaseImageView ivHead;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.save_info)
    TextView saveInfo;
    @BindView(R.id.tv_userSex)
    TextView tvUserSex;
    private String mPicturePath;
    Context context;
    UploadHead muploadHead;
    private String sex;
    private String name;
    private String headUrl;
    private UserInfo userInfo;
    ACache aCache;
//    File imageFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.act_changepersonalinfo);
        context = this;
        aCache = ACache.get(context);
        initView();

    }

    @Override
    protected void initView() {
        setActivityTitle("编辑资料");
        sex = paras.getString("userSex");
        name = paras.getString("userNick");
        headUrl = paras.getString("headUrl");

        if(!TextUtils.isEmpty(sex)){
            if("0".equals(sex)){
//                Glide.with(context).load(R.drawable.user_default).into(ivHead);
                tvUserSex.setText("男");
            }else if("1".equals(sex)){
//                Glide.with(context).load(R.drawable.user_default_avatar_women).into(ivHead);
                tvUserSex.setText("女");
            }
        }
        if(!TextUtils.isEmpty(name)){
            etAccount.setText(name);
        }
        if(!TextUtils.isEmpty(headUrl)){
            Glide.with(context).load(headUrl).into(ivHead);
        }
    }

    @OnClick({R.id.rl_change_headpic,  R.id.rl_change_sex,R.id.save_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_change_headpic:
                showdialog();
                break;
            case R.id.rl_change_sex:
                showDialogSex();
                break;
            case R.id.save_info:
                getData();
                break;
        }
    }

    Dialog bottomDialog;

    private void showDialogSex() {
        bottomDialog = new Dialog(this, BottomDialog);
        View contentView = LayoutInflater.from(this).inflate(R.layout.dialog_sex, null);
        bottomDialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.show();
        TextView btnmen = (TextView) contentView.findViewById(R.id.btn_men);
        TextView btnwomen = (TextView) contentView.findViewById(R.id.btn_women);
        btnmen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvUserSex.setText("男");
                bottomDialog.cancel();
            }
        });
        btnwomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvUserSex.setText("女");
                bottomDialog.cancel();
            }
        });
    }

    /*选择照片弹出框*/
    private void showdialog() {
        bottomDialog = new Dialog(this, BottomDialog);
        View contentView = LayoutInflater.from(this).inflate(R.layout.my_custom_view, null);
        bottomDialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.show();
        TextView textView1 = (TextView) contentView.findViewById(R.id.btn1);
        TextView textView2 = (TextView) contentView.findViewById(R.id.btn2);
        TextView textView3 = (TextView) contentView.findViewById(R.id.btn3);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPicturePath = PhotoUtils
                        .takePicture(ChangePersonalInfoAct.this,context);
                bottomDialog.cancel();
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoUtils.selectPhoto(ChangePersonalInfoAct.this);
                bottomDialog.cancel();
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomDialog.cancel();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case PhotoUtils.INTENT_REQUEST_CODE_ALBUM:
                if (data == null) {
                    return;
                }
                if (resultCode == RESULT_OK) {
                    if (data.getData() == null) {
                        return;
                    }
                    if (!PathManager.isSdCardExit()) {
                        return;
                    }
                    Uri uri = data.getData();
                    PhotoUtils.cropPhoto(context, ChangePersonalInfoAct.this, uri);
                }
                break;

            case PhotoUtils.INTENT_REQUEST_CODE_CAMERA:
                if (resultCode == RESULT_OK) {
                    Uri imageUri;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        String authority = this.getPackageName() + ".fileProvider";
                        imageUri = FileProvider.getUriForFile(context, authority,new File(mPicturePath));
                    } else {
                        imageUri = Uri.parse("file://" + mPicturePath);
                    }
                    PhotoUtils.cropPhoto(context, ChangePersonalInfoAct.this, imageUri);
                }
                break;

            case PhotoUtils.INTENT_REQUEST_CODE_CROP:
                if (resultCode == RESULT_OK) {
                    if (data == null) {
                        return;
                    } else {
                        Bundle extras = data.getExtras();
                        Bitmap photo = extras.getParcelable("data");

                        ivHead.setImageBitmap(photo);

                        String mPicturePath = ImageUtil.savePicture(photo,
                                PathManager.DOWNLOAD_FILES_DIR, UUID.randomUUID().toString());
                        LogUtils.e("相册路径",mPicturePath.toString());
                        if (mPicturePath != null) {
                            this.mPicturePath = mPicturePath;
                            File  imageFile = new File(mPicturePath);
                                uploadImageToServer(imageFile);
                        }
                        photo = null;
                    }
                }
                break;

        }
    }

    /* 把头像上传到服务器保存 */
    private void uploadImageToServer(final File file) {
        showLoading();
        OkGo.post(BaseConstant.TestUrl + BaseConstant.UPLOADHEAD)
                .tag(this)
                .params("userId", AppShared.getInstance(context).getLoginInfo().id)
                .params("userToken", AppShared.getInstance(context).getLoginInfo().userToken)
                .isMultipart(true)
                .params("file", file)
//                .execute(new StringDialogCallback(this) {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//                        LogUtils.e("上传头像",s);
//                    }
//                });
                .execute(new DialogJsonCallback<UploadHead>(this,false) {
                    @Override
                    public void onSuccess(UploadHead uploadHead, Call call, Response response) {
                        dismissLoading();
                        muploadHead = uploadHead;
                        LogUtils.e("上传头像",uploadHead.toString());
                        if (!TextUtils.isEmpty(AppShared.getInstance(context).getHeadUrl())) {
                            deleteHead(AppShared.getInstance(context).getHeadUrl());
                        }else {
                            UpdateUser();
                        }
                        AppShared.getInstance(context).saveHeadUrl(muploadHead.data);
                        //{
                      /*  "flag":200,
                                "msg":"success",
                                "data":"group1/M00/00/03/rBBH51ny4w2AP6WlAAA3xUGX7Wo033.jpg?a30e16c6-6a1d-4e9a-97cd-81fe83794b02.jpg"
                    }*/
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        dismissLoading();
                    }
                });


    }

    /* 删除头像 */
    private void deleteHead( String imageFile) {
        showLoading();
        OkGo.post(BaseConstant.TestUrl + BaseConstant.DELETEFILE)
                .tag(this)
                .params("userId", AppShared.getInstance(context).getLoginInfo().id)
                .params("userToken", AppShared.getInstance(context).getLoginInfo().userToken)
                .params("fileUrl", imageFile)
                .execute(new DialogJsonCallback<BaseResponseData>(this,false) {
                    @Override
                    public void onSuccess(BaseResponseData baseResponseData, Call call, Response response) {
                        dismissLoading();
                        if(!"200".equals(baseResponseData.flag)){
                            toast(baseResponseData.msg);
                        }

                        UpdateUser();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        dismissLoading();
                    }
                });
    }


    /*修改信息接口*/
    public void getData() {
        showLoading();
        OkGo.post(BaseConstant.TestUrl + BaseConstant.UPDATEUSER)
                .tag(this)
                .params("id", AppShared.getInstance(context).getLoginInfo().id)
                .params("userToken", AppShared.getInstance(context).getLoginInfo().userToken)
                .params("userSex", "男".equals(tvUserSex.getText().toString()) ? 0 : 1)
                .params("userNick", etAccount.getText().toString().trim())
                .execute(new DialogJsonCallback<SimpleResponse>(this,false) {
                    @Override
                    public void onSuccess(SimpleResponse simpleResponse, Call call, Response response) {
                        LogUtils.e("保存信息", simpleResponse.toString());
                        dismissLoading();
                        if("200".equals(simpleResponse.flag)){
                            UpdateUser();
                        }else {
                            toast(simpleResponse.msg);
                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        dismissLoading();
                    }
                });
    }

    /*即时更新用户信息*/
    public void UpdateUser() {
        OkGo.post(BaseConstant.TestUrl + BaseConstant.INSTANTUPDATEUSER)
                .tag(this)
                .params("userId", AppShared.getInstance(context).getLoginInfo().id)
                .params("userToken", AppShared.getInstance(context).getLoginInfo().userToken)
                .execute(new DialogCallback <BaseResponse<UserInfo>>(this,false) {
                    @Override
                    public void onSuccess(BaseResponse<UserInfo> userInfoBaseResponse, Call call, Response response) {
                        super.onSuccess(userInfoBaseResponse, call, response);
                        userInfo = userInfoBaseResponse.data;
                        LogUtils.e("更改保存信息", userInfo.toString());
                        etAccount.setText(userInfo.userNick);
                        //判断token和id是否存在
                        if(TextUtils.isEmpty(userInfo.userToken)||TextUtils.isEmpty(userInfo.id)){
                            return;
                        }
                        APPLICATION.isLogin = true;
                        AppShared.getInstance(context).saveHeadUrl(userInfo.headUrl);
                        AppShared.getInstance(context).saveLoginUserInfo(userInfo);
                        Glide.with(context).load(userInfo.headUrl).into(ivHead);
                        aCache.put(Constants.USERINFO,userInfo);
                        if("0".equals(userInfo.userSex)){
                         tvUserSex.setText("男");
                        } else {
                            tvUserSex.setText("女");
                        }
                        finish();
                    }
                });
    }



}
