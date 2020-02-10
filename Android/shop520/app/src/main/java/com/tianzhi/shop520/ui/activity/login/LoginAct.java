package com.tianzhi.shop520.ui.activity.login;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.tianzhi.shop520.APPLICATION;
import com.tianzhi.shop520.AtyMgr;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.base.BaseFragmentActivity;
import com.tianzhi.shop520.callback.DialogCallback;
import com.tianzhi.shop520.callback.DialogJsonCallback;
import com.tianzhi.shop520.entity.BaseResponse;
import com.tianzhi.shop520.entity.BaseResponseData;
import com.tianzhi.shop520.entity.login.UserInfo;
import com.tianzhi.shop520.entity.shop.CartListItem;
import com.tianzhi.shop520.entity.shop.ShopCarEntity;
import com.tianzhi.shop520.util.ACache;
import com.tianzhi.shop520.util.AppShared;
import com.tianzhi.shop520.util.BaseConstant;
import com.tianzhi.shop520.util.Constants;
import com.tianzhi.shop520.util.LogUtils;
import com.tianzhi.shop520.util.NetworkUtil;
import com.tianzhi.shop520.util.StringUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by thinkpad on 2017/10/20.
 * 登录
 */

public class LoginAct extends BaseFragmentActivity {

    ACache aCache;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_forgetpassword)
    TextView tvForgetpassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_regist)
    TextView btnRegist;
    private boolean isShowPassword;
    private Context mContext;
    private String account = null;
    private String password = null;
    UserInfo userInfo;
    ArrayList<ShopCarEntity> shopCarList;//本地购物车信息
    ArrayList<CartListItem> cartlistitems;//存储的上传服务器信息
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        AtyMgr.getAppManager().addActivity(this);
        setContentLayout(R.layout.act_login);
        aCache = ACache.get(mContext);
        initView();
    }

    @Override
    protected void initView() {
        setLeftButtonShow(false);
        setActivityTitle("登录");
        account = paras.getString("account");
        if (!TextUtils.isEmpty(account)) {
            etAccount.setText(account);
        }
    }

    public void login() {
        account = etAccount.getText().toString();
        password = etPassword.getText().toString();
        if (TextUtils.isEmpty(account)) {
            toast("账号不能为空");
            return;
        } else if (!StringUtils.isPhoneNum(account)) {
            toast("请输入正确的手机号码！");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            toast("密码不能为空");
            return;
        }
        if (!NetworkUtil.isConnected(context)) {
            toast("请开启网络连接");
            return;
        }
        LogUtils.e("地址", BaseConstant.TestUrl+BaseConstant.Login);
        showLoading();
        OkGo.post(BaseConstant.TestUrl+BaseConstant.Login)
                .tag(this)
                .params("phone", etAccount.getText().toString().trim())
                .params("password", etPassword.getText().toString().trim())
                .execute(new DialogCallback<BaseResponse<UserInfo>>(this,false) {
                    @Override
                    public void onSuccess(BaseResponse<UserInfo> userInfoBaseResponse, Call call, Response response) {
                        super.onSuccess(userInfoBaseResponse, call, response);
                        dismissLoading();
                        userInfo = userInfoBaseResponse.data;
                        if(!TextUtils.isEmpty(userInfo.userToken)
                                &&!TextUtils.isEmpty(userInfo.type)
                                ){
                            AppShared.getInstance(mContext).cleanUserInfoEntity();
                            AppShared.getInstance(mContext).saveLoginUserInfo(userInfo);
                            AppShared.getInstance(context).saveHeadUrl(userInfo.headUrl);
                            String token = AppShared.getInstance(context).getLoginInfo().userToken;
                            String id = AppShared.getInstance(context).getLoginInfo().id;
                            String headurl = AppShared.getInstance(context).getHeadUrl();
                            LogUtils.e("存储的token",token);
                            LogUtils.e("存储的id",id);
                            LogUtils.e("存储的头像url",headurl);
                            LogUtils.e("我的推荐码",AppShared.getInstance(context).getLoginInfo().recommendedCode);
                            APPLICATION.isLogin =true;
                            aCache.put(Constants.USERINFO,userInfo);
                            /*判断本地是否有缓存*/
                            addCar();
                            //保存数据
                            finish();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        dismissLoading();
                    }
                });
    }

    /*本地购物车*/
    private void addCar(){
        shopCarList = (ArrayList<ShopCarEntity>)aCache.getAsObject(Constants.SHOPCAR);
        if(null!=shopCarList&&shopCarList.size()>0){
            cartlistitems = new ArrayList();
            for (int i=0;i<shopCarList.size();i++){
                for (int j=0;j<shopCarList.get(i).getCartList().size();j++){
                    CartListItem caritem = new CartListItem();
                    caritem.setItemId(shopCarList.get(i).getCartList().get(j).getItemId());
                    caritem.setNum(shopCarList.get(i).getCartList().get(j).getNum());
                    cartlistitems.add(caritem);
                }
            }
            Gson gson = new Gson();
            String obj = gson.toJson(cartlistitems);
            LogUtils.e("登录时判断是否有本地缓存",obj.toString());
            addCart(obj);
        }
    }

    /*添加购物车信息*/
    private void addCart(String obj){
        showLoading();
        OkGo.post(BaseConstant.GoodsUrl+BaseConstant.ADDCART)
                .params("cartList",obj)
                .params("userId", AppShared.getInstance(context).getLoginInfo().id)
                .params("userToken",AppShared.getInstance(context).getLoginInfo().userToken)
                .execute(new DialogJsonCallback<BaseResponseData>(this,false) {
                    @Override
                    public void onSuccess(BaseResponseData baseResponseData, Call call, Response response) {
                       dismissLoading();
                        if("200".equals(baseResponseData.flag)){
//                            toast(baseResponseData.data);
                            //上传本地购物车  清空本地数据
                            aCache.remove(Constants.SHOPCAR);
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        dismissLoading();
                    }
                });
    }

    @OnClick({R.id.btn_login, R.id.tv_forgetpassword, R.id.btn_regist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.tv_forgetpassword://找回密码
                goNext(FindPwdAct.class,true);
                break;
            case R.id.btn_regist:
                goNext(RegistAgreeAct.class,true);
                break;
        }
    }
}
