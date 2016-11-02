package com.example.administrator.neteasenews.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.administrator.neteasenews.entity.NewsType;
import com.example.administrator.neteasenews.fragment.NewsListFragment;

/**
 * Created by Administrator on 2016/10/28.
 */
public class NewsTypesPagerAdapter extends FragmentPagerAdapter {
NewsType newsType;

    public NewsTypesPagerAdapter(FragmentManager fm){
        super(fm);

    }
    public NewsTypesPagerAdapter(FragmentManager fm, NewsType newsType){
        super(fm);
        if (newsType!=null) {
            this.newsType=newsType;
            Log.d("111",newsType.toString()+"不为空");
        }
    }
    @Override
    public Fragment getItem(int position) {
        //持有着viewpager要显示的视图的那个碎片对象
        return NewsListFragment.newInstance(newsType.tList.get(position).getTid(),newsType.tList.get(position).gettName());
    }

    @Override
    public int getCount() {
        return newsType.tList==null?0:newsType.tList.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return newsType.tList.get(position).gettName();
    }
}
