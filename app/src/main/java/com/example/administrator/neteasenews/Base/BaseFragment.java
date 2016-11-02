package com.example.administrator.neteasenews.Base;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {

   private View view;
    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view==null) {
            view = inflater.inflate(getLayoutId(), null);
        //黄油刀绑定
        ButterKnife.bind(this, view);
        initData();
        }
        return view;
    }

    protected abstract void initData();

    public abstract int getLayoutId();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (view!=null){
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent!=null){
                parent.removeView(view);
            }
        }
    }
}
