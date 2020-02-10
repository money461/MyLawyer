package com.tianzhi.shop520.ui.activity.order;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.tianzhi.shop520.R;
import com.tianzhi.shop520.base.BaseFragmentActivity;
import com.tianzhi.shop520.ui.adapter.OrderPagerAdapter;
import com.tianzhi.shop520.ui.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by thinkpad on 2017/10/20.
 */

public class MyorderAct extends BaseFragmentActivity {

    @BindView(R.id.tl_tabs)
    TabLayout tlTabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private ViewPagerAdapter adapter;
    private FragmentManager fm;
    private ArrayList<Object> items = new ArrayList();
    private OrderPagerAdapter pagerAdapter;
    /**
     * 页卡视图集合
     */
    private List<Fragment> mFragmentList = new ArrayList<>();
    private String[] titles={"全部","未付款","待发货","待收货","已完成"};
    private List<String> mTitleList;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.act_myorder);
        initView();
//        initEvent();
    }


    protected void initView() {
        setActivityTitle("我的订单");
//        leftBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                paras.putString("page","3");
//                goNext(MainActivity.class,paras,true);
//            }
//        });
        mTitleList = new ArrayList<String>() ;
        for(int i=0;i<titles.length;i++){
            mTitleList.add(titles[i]);
        }
        //添加页卡视图
        mFragmentList.add(new OrderTotalFragment());
        mFragmentList.add(new OrderNoPayFragment());
        mFragmentList.add(new OrderWaitShipFragment());
        mFragmentList.add(new OrderWaitReviceFragment());
        mFragmentList.add(new OrderSuccessFragment());
        pagerAdapter = new OrderPagerAdapter(getSupportFragmentManager(), mFragmentList,mTitleList);
        viewpager.setAdapter(pagerAdapter);
        viewpager.setOffscreenPageLimit(3);
        tlTabs.setupWithViewPager(viewpager);
        tlTabs.setTabMode(TabLayout.MODE_FIXED);
//        fm = getSupportFragmentManager();
//        items.add(new FristFragment());
//        items.add(new SecondFragment());
//        adapter = new ViewPagerAdapter(fm, items);
//        viewpager.setAdapter(adapter);
//        viewpager.setCurrentItem(0, false);
//        viewpager.setOffscreenPageLimit(items.size());
    }


//    private void initEvent() {
//        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch (checkedId) {
//                    case R.id.rb_daishouhuo: {
//                        viewpager.setCurrentItem(0);
//                        return;
//                    }
//                    case R.id.rb_yiwancheng: {
//                        viewpager.setCurrentItem(1);
//                        break;
//                    }
//                }
//            }
//        });
//        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageSelected(int position) {
//                switch (position) {
//                    case 0: {
//                        rbDaishouhuo.setChecked(true);
//                        return;
//                    }
//                    case 1: {
//                        rbYiwancheng.setChecked(true);
//                        break;
//                    }
//                }
//            }
//
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//            }
//        });
//    }

}

