package com.veyron.www.money.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.veyron.www.money.fragment.MyFragment;

import java.util.ArrayList;

/**
 * Created by Veyron on 2017/3/13.
 * Function：
 */



public class MyViewPagerAdapter extends FragmentPagerAdapter {
    private final ArrayList<MyFragment> fragments;

    public MyViewPagerAdapter(FragmentManager fm, ArrayList<MyFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    /**
     * 根据位置返回对应的Fragment
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }



    @Override
    public int getCount() {
        return fragments.size();
    }

    /**
     * 得到页面的标题
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getTitle();
    }
}
