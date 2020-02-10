package com.tianzhi.shop520.ui.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.tianzhi.shop520.APPLICATION;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.base.BaseLazyFragment;
import com.tianzhi.shop520.callback.DialogCallback;
import com.tianzhi.shop520.entity.BaseResponse;
import com.tianzhi.shop520.entity.login.UserInfo;
import com.tianzhi.shop520.ui.activity.login.LoginAct;
import com.tianzhi.shop520.ui.activity.order.MyorderAct;
import com.tianzhi.shop520.ui.activity.personal.About520Act;
import com.tianzhi.shop520.ui.activity.personal.ChangePersonalInfoAct;
import com.tianzhi.shop520.ui.activity.personal.MemberCenterAct;
import com.tianzhi.shop520.ui.activity.personal.MemberUpgradeAct;
import com.tianzhi.shop520.ui.activity.personal.MyRecommendAct;
import com.tianzhi.shop520.ui.activity.personal.PersonalSettingAct;
import com.tianzhi.shop520.ui.activity.shop.AddressManageAct;
import com.tianzhi.shop520.ui.diyview.BaseImageView;
import com.tianzhi.shop520.util.ACache;
import com.tianzhi.shop520.util.AppShared;
import com.tianzhi.shop520.util.BaseConstant;
import com.tianzhi.shop520.util.Constants;
import com.tianzhi.shop520.util.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by thinkpad on 2017/10/14.
 * 个人中心tab
 */

public class PersonalCenterFragment extends BaseLazyFragment {
    View view;
    @BindView(R.id.iv_head)
    BaseImageView ivHead;
    @BindView(R.id.personal_setting)
    TextView personalSetting;
    @BindView(R.id.personal_name)
    TextView personalName;
    @BindView(R.id.tab_order)
    LinearLayout tabOrder;
    @BindView(R.id.tab_recommend)
    LinearLayout tabRecommend;
    @BindView(R.id.tab_address)
    LinearLayout tabAddress;
    @BindView(R.id.tab_upgrade)
    LinearLayout tabUpgrade;
    @BindView(R.id.tab_setting)
    LinearLayout tabSetting;
    @BindView(R.id.tab_about)
    LinearLayout tabAbout;
    @BindView(R.id.tv_vip_type)
    TextView tvVipType;
    @BindView(R.id.tv_loveSurplus)
    TextView tvLoveSurplus;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.rl_user_info)
    RelativeLayout rlUserInfo;
    @BindView(R.id.text_member)
    TextView textMember;
    private UserInfo userInfo;
    ACache aCache;
    Context context;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_personal, container, false);
        ButterKnife.bind(this, view);
        context = this.getActivity();
        aCache = ACache.get(context);
        return view;
    }

    private void setData() {
        if (APPLICATION.isLogin) {
            userInfo = AppShared.getInstance(context).getLoginInfo();
//            userInfo = (UserInfo) aCache.getAsObject(Constants.USERINFO);
            if (null != userInfo) {
                LogUtils.e("缓存userinfo",userInfo.toString());
           /*会员昵称*/
                if (!TextUtils.isEmpty(userInfo.userNick)) {
                    personalName.setText(userInfo.userNick);
                }
        /*会员类型*/
                if (!TextUtils.isEmpty(userInfo.type)) {
                    if ("0".equals(userInfo.type)) {
                        textMember.setText("会员升级");
                    } else {
                        textMember.setText("会员中心");
                    }
                    if ("0".equals(userInfo.type)) {
                        tvVipType.setText("普通用户");
                    } else if ("1".equals(userInfo.type)) {
                        tvVipType.setText("会员");
                    } else if ("2".equals(userInfo.type)) {
                        tvVipType.setText("爱心会员");
                    } else if ("3".equals(userInfo.type)) {
                        tvVipType.setText("爱心合伙人");
                    } else if ("4".equals(userInfo.type)) {
                        tvVipType.setText("城市爱心合伙人");
                    }
                }
        /*可用爱心值*/
                if (!TextUtils.isEmpty(userInfo.loveSurplus)) {
                    tvLoveSurplus.setText("可用爱心值：" + userInfo.loveSurplus + "点");
                }
        /*用户头像*/
                if (!TextUtils.isEmpty(userInfo.headUrl)) {
                    Glide.with(context).load(userInfo.headUrl).into(ivHead);
                } else{
                    Glide.with(context).load(R.drawable.user_default).into(ivHead);
                    if(!TextUtils.isEmpty(userInfo.userSex)){
                        if("0".equals(userInfo.userSex)){
                            Glide.with(context).load(R.drawable.user_default).into(ivHead);
                        }else if("1".equals(userInfo.userSex)){
                            Glide.with(context).load(R.drawable.user_default_avatar_women).into(ivHead);
                        }
                    }
                }
            }
        } else {
            Glide.with(context).load(R.drawable.user_default).into(ivHead);
            tvLoveSurplus.setText("");
            personalName.setText("");
        }
    }

    private void inivView() {
        if (APPLICATION.isLogin) {
            rlUserInfo.setVisibility(View.VISIBLE);
            tvLogin.setVisibility(View.GONE);
        } else {
            rlUserInfo.setVisibility(View.GONE);
            tvLogin.setVisibility(View.VISIBLE);
        }
        setData();
    }

    public void onFirstUserVisible() {
        LogUtils.e("第一次打开个人中心", "jijijijiijijijiji");
        inivView();
        if (APPLICATION.isLogin) {
            UpdateUser();
        }

    }

    public void onUserVisible() {
        LogUtils.e("再次打开个人中心", "jijijijiijijijiji");
        inivView();
        if (APPLICATION.isLogin) {
            UpdateUser();
        }
    }

    public void onFirstUserInvisible() {
    }

    public void onUserInvisible() {
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.personal_setting, R.id.tab_order, R.id.tab_recommend, R.id.tab_address,
            R.id.tab_upgrade, R.id.tab_setting, R.id.tab_about, R.id.tv_login,
            R.id.tv_vip_type,R.id.iv_head})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.personal_setting:
                if (!APPLICATION.isLogin) {
                    goNext(LoginAct.class);
                } else {
                    if (null != userInfo) {
                        paras.putString("userNick", userInfo.userNick);
                        paras.putString("headUrl", userInfo.headUrl);
                        paras.putString("userSex", userInfo.userSex);
                        goNext(ChangePersonalInfoAct.class, paras, false);
                    }

                }
                break;
            case R.id.tab_order:
                if (!APPLICATION.isLogin) {
                    goNext(LoginAct.class);
                } else {
                    goNext(MyorderAct.class);
                }
                break;
            case R.id.tab_recommend:
                if (!APPLICATION.isLogin) {
                    goNext(LoginAct.class);
                } else {
                    if ("0".equals(userInfo.type)) {
                        AlertDialog alert = new AlertDialog.Builder(context).create();
                        alert.setMessage("您还不是会员，请先升级为会员");
                        alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        return;
                                    }
                                });
                        alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        goNext(MemberUpgradeAct.class);
                                    }
                                });
                        alert.show();

                    } else {
                        goNext(MyRecommendAct.class);
                    }

                }
                break;
            case R.id.tab_address:
                if (!APPLICATION.isLogin) {
                    goNext(LoginAct.class);
                } else {
                    goNext(AddressManageAct.class);
                }
                break;
            case R.id.tab_upgrade:
                if (!APPLICATION.isLogin) {
                    goNext(LoginAct.class);
                } else {
                    if ("0".equals(userInfo.type))
                        goNext(MemberUpgradeAct.class);
                    else
                        goNext(MemberCenterAct.class);
                }
                break;
            case R.id.tab_setting:
                if (!APPLICATION.isLogin) {
                    goNext(LoginAct.class);
                } else {
                    goNext(PersonalSettingAct.class);
                }
                break;
            case R.id.tab_about:
//                goNext(MemberBenefitsAct.class);
//                goNext(PaySuccessAct.class);
                goNext(About520Act.class);
                break;
            case R.id.tv_login:
                goNext(LoginAct.class);
                break;
            case R.id.tv_vip_type:
                if(APPLICATION.isLogin){
                    if ("0".equals(userInfo.type))
                        goNext(MemberUpgradeAct.class);
                    else
                        goNext(MemberCenterAct.class);
                }else {
                    goNext(LoginAct.class);
                }
                break;
            case R.id.iv_head:
                if (!APPLICATION.isLogin) {
                    goNext(LoginAct.class);
                } else {
                    if (null != userInfo) {
                        paras.putString("userNick", userInfo.userNick);
                        paras.putString("headUrl", userInfo.headUrl);
                        paras.putString("userSex", userInfo.userSex);
                        goNext(ChangePersonalInfoAct.class, paras, false);
                    }
                }
                break;
        }
    }

    /*即时更新用户信息*/
    public void UpdateUser() {
        showLoading();
        LogUtils.e("用户id",AppShared.getInstance(context).getLoginInfo().id);
        LogUtils.e("用户id",AppShared.getInstance(context).getLoginInfo().userToken);
        OkGo.post(BaseConstant.TestUrl + BaseConstant.INSTANTUPDATEUSER)
                .tag(this)
                .params("userId", AppShared.getInstance(context).getLoginInfo().id)
                .params("userToken", AppShared.getInstance(context).getLoginInfo().userToken)
                .execute(new DialogCallback<BaseResponse<UserInfo>>(this.getActivity(), false) {
                    @Override
                    public void onSuccess(BaseResponse<UserInfo> userInfoBaseResponse, Call call, Response response) {
                        super.onSuccess(userInfoBaseResponse, call, response);
                        dismissLoading();
                        userInfo = userInfoBaseResponse.data;
                        if (!TextUtils.isEmpty(userInfo.userToken)
                                && !TextUtils.isEmpty(userInfo.id)
                                ) {
                            APPLICATION.isLogin = true;
                            LogUtils.e("点击个人中心更新保存信息", userInfo.toString());
                            AppShared.getInstance(context).cleanUserInfoEntity();
                            aCache.put(Constants.USERINFO, userInfo);
                            AppShared.getInstance(context).saveHeadUrl(userInfo.headUrl);
                            AppShared.getInstance(context).saveLoginUserInfo(userInfo);
                            inivView();
                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        dismissLoading();
                        APPLICATION.isLogin = false;
                        inivView();

                    }
                });
    }


}

