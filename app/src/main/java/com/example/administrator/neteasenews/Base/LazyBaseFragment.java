package com.example.administrator.neteasenews.Base;

/**
 * Created by Administrator on 2016/11/1.
 */

public abstract class LazyBaseFragment extends BaseFragment {
    protected boolean isVisible, isPrepared, hasLoad;//是否显示，视图是否创建完毕，数据是否加载完成

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisible = isVisibleToUser;
        if (!isVisible || !isPrepared || hasLoad) {
            return;
        }
//        加载数据
        hasLoad = lazyLoad();//子类要实现加载数据的方法
    }


    @Override
    protected void initData() {
isPrepared=true;
        if (!isVisible||!isPrepared||hasLoad){
            return;//b不可见，未准备好，已经加载好，return;
        }
        hasLoad=lazyLoad();
    }

    protected abstract boolean lazyLoad();

}
