package com.veyron.www.money.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.veyron.www.money.R;


public class FragmentTwo extends BaseFragment {


    private static final String TAG = FragmentTwo.class.getSimpleName();//"CommonFrameFragment"
    private TextView textView;

    @Override
    protected View initView() {
        Log.e(TAG,"其他Fragment页面被初始化了...");
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        View contentView  = mInflater.inflate(R.layout.layout_two,null);
        return contentView;
    }

    @Override
    protected void initData() {
        super.initData();

    }
}
