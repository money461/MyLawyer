package com.tianzhi.shop520.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.tianzhi.shop520.APPLICATION;
import com.tianzhi.shop520.AtyMgr;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.callback.DialogCallback;
import com.tianzhi.shop520.entity.BaseResponse;
import com.tianzhi.shop520.entity.login.UserInfo;
import com.tianzhi.shop520.ui.adapter.SimpleFragmentPagerAdapter;
import com.tianzhi.shop520.ui.diyview.CustomViewPager;
import com.tianzhi.shop520.ui.diyview.dialog.LMToast;
import com.tianzhi.shop520.ui.fragment.HomeFragment;
import com.tianzhi.shop520.ui.fragment.PersonalCenterFragment;
import com.tianzhi.shop520.ui.fragment.ShoppingCartFragment;
import com.tianzhi.shop520.ui.fragment.StoreFragment;
import com.tianzhi.shop520.util.ACache;
import com.tianzhi.shop520.util.AppShared;
import com.tianzhi.shop520.util.BaseConstant;
import com.tianzhi.shop520.util.Constants;
import com.tianzhi.shop520.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends FragmentActivity {
    @BindView(R.id.main_viewpager)
    CustomViewPager mainViewpager;
    @BindView(R.id.tl_tabs)
    TabLayout tlTabs;
    @BindView(R.id.fragment_layout)
    RelativeLayout fragmentLayout;
    @BindView(R.id.drawerlayout)
    RelativeLayout drawerlayout;
    /**
     * 页卡视图集合
     */
    private List<Fragment> mFragmentList = new ArrayList<>();
    private Context mContext;
    ACache aCache;
    private long lastClickTime = 0;
    private SimpleFragmentPagerAdapter pagerAdapter;
    private TabLayout.Tab tabhome;
    private TabLayout.Tab tabpersonal;
    private TabLayout.Tab tabshopping;
    private TabLayout.Tab tabstore;
    private String[] titles={"首页","商城","购物车","个人"};
    private int[] images= {R.drawable.selector_tab_home,R.drawable.selector_tab_store,
    R.drawable.selector_tab_shopping,R.drawable.selector_tab_personal};
    private UserInfo userInfo;
    HomeFragment homeFragment;
    StoreFragment storeFragment;
    ShoppingCartFragment shoppingCartFragment;
    PersonalCenterFragment personalCenterFragment;
    String CurrentPage ;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AtyMgr.getAppManager().addActivity(this);
        LogUtils.e("mainactivity","onCreate");
        mContext = this;
        aCache = ACache.get(mContext);

        if(!TextUtils.isEmpty(AppShared.getInstance(mContext).getLoginInfo().id)
                &&!TextUtils.isEmpty(AppShared.getInstance(mContext).getLoginInfo().userToken)){
            UpdateUser();
        }
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        setView();
    }


    public void initView() {
        homeFragment = new HomeFragment();
        storeFragment = new StoreFragment();
        shoppingCartFragment = new ShoppingCartFragment();
        personalCenterFragment = new PersonalCenterFragment();

        //添加页卡视图
        mFragmentList.add(homeFragment);
        mFragmentList.add(storeFragment);
        mFragmentList.add(shoppingCartFragment);
        mFragmentList.add(personalCenterFragment);

        pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), mFragmentList);
        mainViewpager.setAdapter(pagerAdapter);
        //禁止viewpaper滚动，通过设置setCurrentItem 来进行切换
        mainViewpager.setScanScroll(false);
        mainViewpager.setOffscreenPageLimit(3);
        //监听切换事件，设置tab的选中和颜色
//        mainViewpager.addOnPageChangeListener(mOnPageChangeListener);
        tlTabs.setupWithViewPager(mainViewpager);
        tlTabs.setTabMode(TabLayout.MODE_FIXED);

    }
    public void setView(){
        Intent intent = getIntent();
        CurrentPage = intent.getExtras().getString("page");
//        CurrentPage = intent.getStringExtra("page");
        for (int i = 0; i < pagerAdapter.getCount(); i++) {
            TabLayout.Tab tab = tlTabs.getTabAt(i);//获得每一个tab
            tab.setCustomView(R.layout.tab_item);//给每一个tab设置view
            if (i == 0) {
                // 设置第一个tab的TextView是被选择的样式
                tab.getCustomView().findViewById(R.id.tab_title).setSelected(true);//第一个tab被选中
                tab.getCustomView().findViewById(R.id.tab_icon).setSelected(true);//第一个tab被选中
            }
            TextView textView = (TextView) tab.getCustomView().findViewById(R.id.tab_title);
            ImageView imageView = (ImageView) tab.getCustomView().findViewById(R.id.tab_icon);
            textView.setText(titles[i]);//设置tab上的文字
            imageView.setBackgroundResource(images[i]);
            if (tab != null) {
                if (tab.getCustomView() != null) {
                    View tabView = (View) tab.getCustomView().getParent();
                    tabView.setTag(i);
                    tabView.setOnClickListener(mTabOnClickListener);
                }
            }
        }
        tlTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getCustomView().findViewById(R.id.tab_title).setSelected(true);
                tab.getCustomView().findViewById(R.id.tab_icon).setSelected(true);
                mainViewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getCustomView().findViewById(R.id.tab_title).setSelected(false);
                tab.getCustomView().findViewById(R.id.tab_icon).setSelected(false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        if ("1".equals(CurrentPage)) {
//            storeFragment.refreshData(paras);
            mainViewpager.setCurrentItem(1);
        } else if ("2".equals(CurrentPage)) {
//            shoppingCartFragment.refreshData(paras);
            mainViewpager.setCurrentItem(2);
        }else if ("3".equals(CurrentPage)) {
//            shoppingCartFragment.refreshData(paras);
            mainViewpager.setCurrentItem(3);
        } else {
//            homeFragment.refreshData(paras);
            mainViewpager.setCurrentItem(0);
        }
    }

    private View.OnClickListener mTabOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int pos = (int) view.getTag();
//            if (pos == 3) {
//                if (APPLICATION.isLogin) {//TODO 跳转到登录界面
//                    UpdateUser();
//                }
//            } else {
                TabLayout.Tab tab = tlTabs.getTabAt(pos);
                if (tab != null) {
                    tab.select();
//                }
            }
        }
    };
    /*即时更新用户信息*/
    public void UpdateUser() {
        LogUtils.e("用户idwwwwwww",AppShared.getInstance(mContext).getLoginInfo().id);
        LogUtils.e("tokenwwwwww",AppShared.getInstance(mContext).getLoginInfo().userToken);
        OkGo.post(BaseConstant.TestUrl + BaseConstant.INSTANTUPDATEUSER)
                .tag(this)
                .params("userId", AppShared.getInstance(mContext).getLoginInfo().id)
                .params("userToken", AppShared.getInstance(mContext).getLoginInfo().userToken)
                .execute(new DialogCallback<BaseResponse<UserInfo>>(this,true) {
                    @Override
                    public void onSuccess(BaseResponse<UserInfo> userInfoBaseResponse, Call call, Response response) {
                        super.onSuccess(userInfoBaseResponse, call, response);
                        userInfo = userInfoBaseResponse.data;
                        LogUtils.e("打开app更新保存信息", userInfo.toString());
                        if(!TextUtils.isEmpty(userInfo.userToken)
                                &&!TextUtils.isEmpty(userInfo.id)
                                ){
                            AppShared.getInstance(mContext).cleanUserInfoEntity();
                        APPLICATION.isLogin = true;
                        aCache.put(Constants.USERINFO,userInfo);
                        AppShared.getInstance(mContext).saveHeadUrl(userInfo.headUrl);
                        AppShared.getInstance(mContext).saveLoginUserInfo(userInfo);
                        }
                        LogUtils.e("打开app用户id",AppShared.getInstance(mContext).getLoginInfo().id);
                        LogUtils.e("打开apptoken",AppShared.getInstance(mContext).getLoginInfo().userToken);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        APPLICATION.isLogin = false;
                    }
                });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long time = System.currentTimeMillis();
            long timeD = time - lastClickTime;
            if (0 < timeD && timeD < 1000) {
                AtyMgr.getAppManager().AppExit(this);
//                APPLICATION.isLogin =false;
                return super.onKeyDown(keyCode, event);
            } else {
                LMToast.showToast("双击退出");
            }
            lastClickTime = time;
        }
        return false;
    }
    //此方法在onResume之前执行
//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        setIntent(intent);
////        InitArg();
//    }
    @Override
    protected void onRestart() {
        super.onRestart();
    }

}
