package com.tianzhi.shop520.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by thinkpad on 2017/11/9.
 */

public class OrderPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> listFragments;
    private List<String> mTitleList;

    public OrderPagerAdapter(FragmentManager fm,
                                      List<Fragment> al,List<String> mTitleList) {
        super(fm);
        listFragments = al;
        this.mTitleList =mTitleList;
    }

    public OrderPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return listFragments.get(position);
    }

    @Override
    public int getCount() {
        return listFragments.size();
    }

    /**
     * 防止频繁的销毁视图，设置ViewPager.setOffscreenPageLimit(2)<br/>
     * 或者重写PagerAdaper的destroyItem方法为空,<br/>
     * 即注释掉调用父类方法即可
     *
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);//页卡标题
    }

}