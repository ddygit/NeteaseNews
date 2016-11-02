package com.example.administrator.neteasenews.fragment;


import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.administrator.neteasenews.Base.BaseFragment;
import com.example.administrator.neteasenews.R;
import com.example.administrator.neteasenews.activity.BrowerActivity;
import com.example.administrator.neteasenews.biz.Xhttp;

import butterknife.BindView;


public class NewsContentFragment extends BaseFragment {

    @BindView(R.id.webView1)
    WebView webView1;
    private String docid;

    public NewsContentFragment() {
        // Required empty public constructor
    }

    public static NewsContentFragment newInstance(String docid) {
        NewsContentFragment fragment = new NewsContentFragment();
        Bundle args = new Bundle();
        args.putString(BrowerActivity.KEY_DOCID, docid);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            docid = getArguments().getString(BrowerActivity.KEY_DOCID);
        }
    }

    @Override
    protected void initData() {
        //初始化数据
        //从网络获取json，解析，显示
        //webview的基本设置
        webView1.getSettings().setJavaScriptEnabled(true);
        webView1.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView1.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //重新设置图片大小，适配当前的屏幕
                imgReset();
            }
        });
        Xhttp.getNewsContentList(docid, new Xhttp.NewsContentListener() {
            @Override
            public void onFinish(String str) {
//                mWebView1.loadDataWithBaseURL(null,str,"text/html", "utf-8",null);
                webView1.loadDataWithBaseURL(null,str,"text/html", "utf-8",null);
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_news_content;
    }
    private void imgReset() {
        webView1.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName('img'); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "var img = objs[i];   " +
                "    img.style.maxWidth = '100%';   " +
                "}" +
                "})()");
    }

}
