package com.example.administrator.neteasenews.biz;

import android.util.Log;

import com.example.administrator.neteasenews.common.CommonUrls;
import com.example.administrator.neteasenews.entity.Netease;
import com.example.administrator.neteasenews.entity.NewsContent;
import com.example.administrator.neteasenews.entity.NewsType;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/10/29.
 */

public class Xhttp {


    private static final String TAG = "xhttp";

    public static void getNewsList(String uri, final String tid, final OnSuccessListener listener) {
        RequestParams entity = new RequestParams(uri);
        Callback.CommonCallback<String> callback = new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ArrayList<Netease> neteaseNews = new ArrayList<>();
                Gson gson = new Gson();
                try {
                    JSONObject obj = new JSONObject(result);
                    JSONArray array = obj.getJSONArray(tid);
                    for (int i = 0; i < array.length(); i++) {
                        Netease netEase = gson.fromJson(array.get(i).toString(), Netease.class);
                        neteaseNews.add(netEase);
                    }
                    //使用接口
                    if (listener != null) {
                        listener.setNewsList(neteaseNews);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d(TAG, "onError: " + ex.toString());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.d(TAG, "onCancelled: ");
            }

            @Override
            public void onFinished() {
                Log.d(TAG, "onFinished: ");
            }
        };
        x.http().get(entity, callback);
    }


    public interface OnSuccessListener {
        void setNewsList(List<Netease> neteaseNews);
    }

    public static void getNewsTypeList(final NewsTypeListener listener) {

        RequestParams entity = new RequestParams(CommonUrls.NEWS_TYPE);
        Callback.CommonCallback<String> callback = new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d(TAG, "onSuccess:" + result);
                Gson gson = new Gson();
                NewsType type = gson.fromJson(result, NewsType.class);
                if (listener != null) {
                    listener.OnSuccess(type);
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d(TAG, "onError:");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.d(TAG, "onCancelled:");
            }

            @Override
            public void onFinished() {
                Log.d(TAG, "onFinished:");
                if (listener != null) {
                    listener.Finish();
                }
            }
        };
        x.http().get(entity, callback);
    }

    public interface NewsTypeListener {
        void OnSuccess(NewsType newsType);

        void Finish();
    }

    public static void getNewsContentList(final String dicId, final NewsContentListener listener) {
        RequestParams entity = new RequestParams(CommonUrls.getNewsContentUrl(dicId));
        Callback.CommonCallback<String> callback = new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                try {
                    JSONObject obj = new JSONObject(result);
                    String string = obj.getString(dicId);
                    NewsContent newsContent = gson.fromJson(string, NewsContent.class);
                    //从newsContent对象中把body和img集合重新整合一个让webview显示的string

                    String before = "<p><img src=\"";
                    String after = "\"/> </img></p>";
                    //重整字符串：
                    //1.添加标题；<p><h1>  </h1></p>
                    String title_b = "<p><h2>";
                    String title_a = "</h2></p>";

                    newsContent.body = title_b + newsContent.title + title_a + newsContent.body;
                    //添加作者：
                    for (NewsContent.Img img : newsContent.img) {
                        newsContent.body = newsContent.body.replace(img.ref, before + img.src + after);
                    }
                    if (listener != null) {
                        listener.onFinish(newsContent.body);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        };
        x.http().get(entity, callback);
    }

    private NewsContentListener listener;

    public interface NewsContentListener {
        void onFinish(String str);
    }
}
