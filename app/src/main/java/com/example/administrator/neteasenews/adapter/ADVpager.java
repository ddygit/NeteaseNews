package com.example.administrator.neteasenews.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.neteasenews.R;
import com.example.administrator.neteasenews.entity.Netease;
import com.example.administrator.neteasenews.utils.XImageUtil;

import java.util.List;

/**
 * Created by Administrator on 2016/10/25.
 * 图片轮播 不自动
 */

public class ADVpager extends PagerAdapter {
    private List<Netease.Ads> list;

    public ADVpager(List<Netease.Ads> list) {
        this.list = list;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(container.getContext(), R.layout.layout_item_one_head, null);
        TextView mTitile = (TextView) view.findViewById(R.id.tv_title);
        ImageView imgView = (ImageView) view.findViewById(R.id.img_head);
        mTitile.setText(list.get(position % list.size()).getTitle());
        XImageUtil.display(imgView, list.get(position % list.size()).getImgsrc());
        container.addView(view);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
