package com.example.administrator.neteasenews.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.administrator.neteasenews.R;
import com.example.administrator.neteasenews.entity.NewsType;
import com.example.administrator.neteasenews.fragment.FavorFragment;
import com.example.administrator.neteasenews.fragment.HomeFragment;
import com.example.administrator.neteasenews.fragment.HotFragment;
import com.example.administrator.neteasenews.fragment.NewsListFragment;
import com.example.administrator.neteasenews.fragment.SettingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, NewsListFragment.OnFragmentInteractionListener {
    public static final String KEY_NEWSTYPE = "NewsType";
    HomeFragment homeFragment;
    FavorFragment favorFragment;
    HotFragment hotFragment;
    SettingFragment settingFragment;
    @BindView(R.id.radio1)
    RadioGroup radio1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        homeFragment = HomeFragment.newInstance(getIntent().getExtras());
        favorFragment = new FavorFragment();
        hotFragment = new HotFragment();
        settingFragment = new SettingFragment();
//        replace(R.id.fragment_fl, homeFragment);
        showFragment(homeFragment);
        radio1.setOnCheckedChangeListener(this);

    }

    public void replace(int containerViewId, Fragment fragment) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(containerViewId, fragment, fragment.getClass().getSimpleName());
        ft.commit();
    }

    public void showFragment(Fragment fragment) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        String simpleName = fragment.getClass().getSimpleName();
        if (fm.findFragmentByTag(simpleName)==null){
            ft.add(R.id.fragment_fl,fragment,simpleName);
        }
        ft.hide(homeFragment);
        ft.hide(hotFragment);
        ft.hide(favorFragment);
        ft.hide(settingFragment);
        ft.show(fragment);
        ft.commit();
    }


    public static void start(Context context, NewsType newsType) {
        Intent starter = new Intent(context, MainActivity.class);
        starter.putExtra(KEY_NEWSTYPE, newsType);
        context.startActivity(starter);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb1_main:
//                replace(R.id.fragment_fl, homeFragment);
                showFragment(homeFragment);
                break;

            case R.id.rb2_main:
//                replace(R.id.fragment_fl, hotFragment);
                showFragment(hotFragment);
                break;
            case R.id.rb3_main:
//                replace(R.id.fragment_fl, favorFragment);
                showFragment(favorFragment);
                break;
            case R.id.rb4_main:
//                replace(R.id.fragment_fl, settingFragment);
                showFragment(settingFragment);
                break;
        }
    }

    @Override
    public void onFragmentInteraction(String docid) {
        Toast.makeText(this, "docid" + docid, Toast.LENGTH_SHORT).show();
        BrowerActivity.start(this, docid);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_favor, menu);
//        return true;
//    }
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id=item.getItemId();
//        switch (id) {
//            case R.id.action_share:
//                //增加
//                Toast.makeText(this, "你好", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.action_favor:
//                //查找
//                return true;
//
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
