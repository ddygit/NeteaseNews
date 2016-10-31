package com.example.administrator.neteasenews.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.example.administrator.neteasenews.Base.BaseFragment;
import com.example.administrator.neteasenews.R;
import com.example.administrator.neteasenews.activity.MainActivity;
import com.example.administrator.neteasenews.adapter.NewsTypesPagerAdapter;
import com.example.administrator.neteasenews.entity.NewsType;
import com.viewpagerindicator.TabPageIndicator;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/10/28.
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.indicator)
    TabPageIndicator indicator;
    @BindView(R.id.pager)
    ViewPager pager;
private NewsTypesPagerAdapter adapter;
public static HomeFragment newInstance(Bundle bundle) {
    HomeFragment fragment = new HomeFragment();
    fragment.setArguments(bundle);
    return fragment;
}

    @Override
    protected void initData() {
        Bundle b=getArguments();
       NewsType newsType = (NewsType) b.getSerializable(MainActivity.KEY_NEWSTYPE);
        Log.d("111",newsType.tList.size()+"");
        adapter = new NewsTypesPagerAdapter(getFragmentManager(),newsType);
        pager.setAdapter(adapter);
        indicator.setViewPager(pager);
    }

    @Override
    public int getLayoutId() {
        return R.layout.home_layout;
    }

}
