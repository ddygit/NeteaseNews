package com.example.administrator.neteasenews.adapter;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.neteasenews.R;
import com.example.administrator.neteasenews.entity.Netease;
import com.example.administrator.neteasenews.utils.XImageUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/24.
 */

public class NetEaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public NetEaseAdapter(List<Netease> dataList) {
        this.dataList = dataList;
    }

    public List<Netease> getDataList() {
        return dataList;
    }


    public void setDataList(List<Netease> dataList) {
        this.dataList = dataList;
    }

    public void addDataList(List<Netease> list) {
        if (list == null) {
            Log.d("addDataList", "addDataList: 集合不能为空");
            return;
        }
        dataList.addAll(list);
    }

    public void addData(Netease netease) {
        dataList.add(netease);
    }

    public void addData(int position, Netease netease) {
        dataList.add(position, netease);
    }

    private List<Netease> dataList;
    private static final int VIEW_VIEWPAGER = 153;
    private static final int VIEW_LONG_IMAGE = 168;
    private static final int VIEW_ONE_IMAGE = 160;
    private static final int VIEW_THREE_IMAGE = 429;
    private static final int VIEW_FOOTER = 552;

    /***
     * 根据视图类型决定视图的布局，使用哪个holder创建视图
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case VIEW_VIEWPAGER:
                itemView = View.inflate(parent.getContext(), R.layout.layout_item_vp, null);
                holder = new ViewPagerHolder(itemView);
                break;
            case VIEW_LONG_IMAGE:
                itemView = View.inflate(parent.getContext(), R.layout.layout_item_one_head, null);
                holder = new LongImageHolder(itemView);
                break;
            case VIEW_ONE_IMAGE:
                itemView = View.inflate(parent.getContext(), R.layout.layout_item_one_img, null);
                holder = new OneImageHolder(itemView);
                break;
            case VIEW_THREE_IMAGE:
                itemView = View.inflate(parent.getContext(), R.layout.layout_item_three_img, null);
                holder = new ThreeImageHolder(itemView);
                break;
            case VIEW_FOOTER:
                itemView = View.inflate(parent.getContext(), R.layout.footer, null);
                holder = new FooterHolder(itemView);
                break;
            default:
                break;

        }
        return holder;
    }

    /**
     * 通过位置确定当前的视图类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return dataList.get(position).ads == null ? VIEW_LONG_IMAGE : VIEW_VIEWPAGER;
        } else if (position < dataList.size()) {
            return dataList.get(position).imgextra == null ? VIEW_ONE_IMAGE : VIEW_THREE_IMAGE;
        } else {
            return VIEW_FOOTER;
        }

    }

    /**
     * 给每个holder设置对应的数据
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof OneImageHolder) {
            initOneImageView((OneImageHolder) holder, dataList.get(position));
        } else if (holder instanceof ViewPagerHolder) {
            initViewPagerView((ViewPagerHolder) holder, dataList.get(position));
        } else if (holder instanceof LongImageHolder) {
            initLongImageView((LongImageHolder) holder, dataList.get(position));
        } else if (holder instanceof ThreeImageHolder) {
            initThreeImageView((ThreeImageHolder) holder, dataList.get(position));
        } else if (holder instanceof FooterHolder) {
            initFooterView((FooterHolder) holder);
        }

        //点击事件传递给activity来处理
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onClick(position);
                }
            });
        }
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private OnItemClickListener onItemClickListener;

    //点击事件：
    public interface OnItemClickListener {
        void onClick(int position);
    }

    /**
     * 左边大图：
     *
     * @param h
     * @param netEase
     */
    private void initOneImageView(OneImageHolder h, Netease netEase) {
        XImageUtil.display(h.mImgLeft, netEase.imgsrc);
        h.mTvTitle.setText(netEase.title);
        h.mTvFollow.setText(netEase.replyCount + "");
    }

    //分状态去初始化：
    public static final int FOOTER_PULLING = 483;
    public static final int FOOTER_PULL_FINISHED = 306;
    public static final int FOOTER_PULL_NO_DATA = 147;
    private int currentState;

    /**
     * 设置footview的状态
     *
     * @param currentState
     */
    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    //footer上拉加载的设置
    private void initFooterView(FooterHolder holder) {
        switch (currentState) {
            case FOOTER_PULL_FINISHED:
                holder.mProgressBar1.setVisibility(View.INVISIBLE);
                break;
            case FOOTER_PULL_NO_DATA:
                holder.mTextView1.setText("没有更多数据了");
                holder.mProgressBar1.setVisibility(View.INVISIBLE);
                break;
            case FOOTER_PULLING:
                holder.mTextView1.setText("正在加载，请稍候...");
                holder.mProgressBar1.setVisibility(View.VISIBLE);
                break;
        }

    }

    /**
     * 三张横向图片横幅的设置
     *
     * @param holder
     * @param netEase
     */
    private void initThreeImageView(ThreeImageHolder holder, Netease netEase) {
        holder.mTvTitle.setText(netEase.title);
        holder.mTvFollow.setText(netEase.replyCount + "");
        XImageUtil.display(holder.mImg1, netEase.imgsrc);
        XImageUtil.display(holder.mImg2, netEase.imgextra.get(0).imgsrc);
        XImageUtil.display(holder.mImg3, netEase.imgextra.get(1).imgsrc);

    }

    /**
     * 第一行横幅设置
     *
     * @param holder
     * @param netEase
     */
    private void initLongImageView(LongImageHolder holder, Netease netEase) {
        XImageUtil.display(holder.mImgHead, netEase.imgsrc);
        holder.mTvTitle.setText(netEase.title);
    }

    /**
     * viewpager 行图片轮播viewpager实现
     *
     * @param holder
     * @param netEase
     */
    private void initViewPagerView(final ViewPagerHolder holder, Netease netEase) {
        ADVpager adapter = new ADVpager(netEase.ads);
        holder.mVpager.setAdapter(adapter);
        //添加上对应ad长度的点点图片
        if (holder.mLlLayout.getChildCount() == 0) {
            for (int i = 0; i < netEase.ads.size(); i++) {
                ImageView img = new ImageView(holder.mLlLayout.getContext());
                img.setImageResource(R.drawable.adware_style_default);
                holder.mLlLayout.addView(img);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) img.getLayoutParams();
                layoutParams.leftMargin = 5;
                layoutParams.rightMargin = 5;
            }
        }
        ((ImageView) (holder.mLlLayout.getChildAt(0))).setImageResource(R.drawable.adware_style_selected);
        holder.mVpager.setCurrentItem(Integer.MAX_VALUE / 2 - ((Integer.MAX_VALUE / 2) % netEase.ads.size()));
        //点的移动：
        holder.mVpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < holder.mLlLayout.getChildCount(); i++) {
                    ImageView img = (ImageView) holder.mLlLayout.getChildAt(i);
                    img.setImageResource(R.drawable.adware_style_default);
                }
                ImageView img = (ImageView) holder.mLlLayout.getChildAt(position % holder.mLlLayout.getChildCount());
                img.setImageResource(R.drawable.adware_style_selected);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size() + 1;
    }
    //5个holder ，对应5种视图：

    /**
     * 1.左边是大图，右边显示标题，和跟帖数
     */

    public static class OneImageHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_left)
        ImageView mImgLeft;
        @BindView(R.id.tv_title)
        TextView mTvTitle;
        @BindView(R.id.tv_follow)
        TextView mTvFollow;

        public OneImageHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 上方显示标题，三张并排的小图，组合成的横幅效果
     */
    public static class ThreeImageHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView mTvTitle;
        @BindView(R.id.img1)
        ImageView mImg1;
        @BindView(R.id.img2)
        ImageView mImg2;
        @BindView(R.id.img3)
        ImageView mImg3;
        @BindView(R.id.tv_follow)
        TextView mTvFollow;

        public ThreeImageHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * viewpager横向滑动的图片轮播，左右无限滑动,在列表第一行显示，如果有ads，则使用此视图
     */
    public static class ViewPagerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.vpager)
        ViewPager mVpager;
        @BindView(R.id.ll_layout)
        LinearLayout mLlLayout;

        public ViewPagerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 单张的横幅新闻，列表的第一行，没有ads的时候，使用此视图
     */
    public static class LongImageHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_head)
        ImageView mImgHead;
        @BindView(R.id.tv_title)
        TextView mTvTitle;

        public LongImageHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 4
     * 上拉加载更多：当前列表数据已经完全显示在屏幕上以后自动在列表下面显示，执行上拉则进行上拉加载更多的操作：
     * 有一个变化的视图 分多个状态-上拉加载更多。。/正在加载/已加载/没有更多数据/
     */

    public static class FooterHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.progressBar1)
        ProgressBar mProgressBar1;
        @BindView(R.id.textView1)
        TextView mTextView1;

        public FooterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

