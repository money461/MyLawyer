package com.tianzhi.shop520.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by thinkpad on 2017/10/20.
 * banner
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Object> items;

    public ViewPagerAdapter(FragmentManager fm, ArrayList<Object> items) {
        super(fm);
        this.items = items;
    }

    public Fragment getItem(int position) {
        switch(position) {
            case 0:
            {
                return (Fragment)items.get(position);
            }
            case 1:
            {
                return (Fragment)items.get(position);
            }
            case 2:
            {
                return (Fragment)items.get(position);
            }
           
        }
        return (Fragment)items.get(position);
    }

    public int getCount() {
        return items.size();
    }
}

