package com.example.administrator.neteasenews.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.administrator.neteasenews.Base.LazyBaseFragment;
import com.example.administrator.neteasenews.R;
import com.example.administrator.neteasenews.adapter.NetEaseAdapter;
import com.example.administrator.neteasenews.biz.Xhttp;
import com.example.administrator.neteasenews.common.CommonUrls;
import com.example.administrator.neteasenews.entity.Netease;
import com.example.administrator.neteasenews.views.RecycleViewDivider;

import java.util.List;
import java.util.TimerTask;

import butterknife.BindView;

public class NewsListFragment extends LazyBaseFragment implements SwipeRefreshLayout.OnRefreshListener, NetEaseAdapter.OnItemClickListener {
    private static final String TID = "tid";
    private static final String TNAME = "tName";
    @BindView(R.id.recyclerView1)
    RecyclerView recyclerView1;
    @BindView(R.id.swipe1)
    SwipeRefreshLayout swipe1;

    private String tid, tName;
    private OnFragmentInteractionListener mListener;
    private RecyclerView.OnScrollListener lis = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (!swipe1.isRefreshing()) {
                int lastItemPosition = layoutManager.findLastVisibleItemPosition();
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastItemPosition == mNetEaseAdapter.getItemCount() - 1) {
                    //加载数据
                    mNetEaseAdapter.setCurrentState(NetEaseAdapter.FOOTER_PULLING);

                    //获取新数据，url

                    Xhttp.getNewsList(CommonUrls.getUrl(tid), tid, new Xhttp.OnSuccessListener() {
                        @Override
                        public void setNewsList(List<Netease> neteaseNews) {
                            mNetEaseAdapter.addDataList(neteaseNews);
                            mNetEaseAdapter.notifyDataSetChanged();
                            if (neteaseNews.size() == 0) {
                                mNetEaseAdapter.setCurrentState(NetEaseAdapter.FOOTER_PULL_NO_DATA);
                            } else {
                                mNetEaseAdapter.setCurrentState(NetEaseAdapter.FOOTER_PULL_FINISHED);
                            }
                        }
                    });
                }
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }
    };
    private NetEaseAdapter mNetEaseAdapter;
    private LinearLayoutManager layoutManager;
    private Xhttp.OnSuccessListener listener = new Xhttp.OnSuccessListener() {
        @Override
        public void setNewsList(List<Netease> neteaseNews) {
            mNetEaseAdapter = new NetEaseAdapter(neteaseNews);
            mNetEaseAdapter.setOnItemClickListener(NewsListFragment.this);
            recyclerView1.setAdapter(mNetEaseAdapter);
            //必须要设置一个布局管理器 //listview,gridview,瀑布流
            layoutManager = new LinearLayoutManager(getContext());
            recyclerView1.setLayoutManager(layoutManager);
            //   mRecyclerView1.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
            // mRecyclerView1.setLayoutManager(new GridLayoutManager(MainActivity.this, 2, GridLayoutManager.HORIZONTAL, false));
            //   mRecyclerView1.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            //分割线
            recyclerView1.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL));
        }
    };
    private Handler mhandler = new Handler();
    private ProgressDialog pd;

    public NewsListFragment() {

    }

    //    @Override
    //    protected void initData() {
    //        swipe1.setOnRefreshListener(this);
    //        recyclerView1.addOnScrollListener(lis);
    //        Xhttp.getNewsList(CommonUrls.getUrl(tid), tid, listener);
    //        // Xhttp.getNewsList(url, "T1370583240249", listener);
    //    }

    @Override
    protected boolean lazyLoad() {
        swipe1.setOnRefreshListener(this);
        recyclerView1.addOnScrollListener(lis);
        Xhttp.getNewsList(CommonUrls.getUrl(tid), tid, listener);
        return true;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_news_list;
    }

    //
    public static NewsListFragment newInstance(String tid, String tName) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle args = new Bundle();
        args.putString(TID, tid);
        args.putString(TNAME, tName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tid = getArguments().getString(TID);
            tName = getArguments().getString(TNAME);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onRefresh() {
        Runnable runable = new TimerTask() {
            public void run() {
                Netease netease = mNetEaseAdapter.getDataList().get(1);
                mNetEaseAdapter.addData(1, netease);
                mNetEaseAdapter.notifyItemInserted(1);
                Toast.makeText(getContext(), "加载数据完毕", Toast.LENGTH_SHORT).show();
                swipe1.setRefreshing(false);

            }
        };
        mhandler.postDelayed(runable, 2000);
    }

    @Override
    public void onClick(int position) {
        String docid = mNetEaseAdapter.getDataList().get(position).docid;
        onButtonPressed(docid);
    }

    public void onButtonPressed(String docid) {
        if (mListener != null) {
            mListener.onFragmentInteraction(docid);
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String docid);
    }
}
