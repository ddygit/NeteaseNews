package com.example.administrator.neteasenews.common;

/**
 * Created by Administrator on 2016/10/29.
 */

public class CommonUrls {

    private static final String BASE_URL="http://c.m.163.com/";
    /**
     * 网易新闻的分类获取链接http://c.m.163.com/nc/topicset/android/subscribe/manage/listspecial.html
     */
    public static final String NEWS_TYPE = BASE_URL+"nc/topicset/android/subscribe/manage/listspecial.html";
    public static String getUrl(String tid){
        return BASE_URL+"nc/article/list/"+tid+"/0-20.html";
//        http://c.m.163.com/ nc/article/list/T1370583240249/0-20.html
    }

    public static String getNewsContentUrl(String docId) {
        return  BASE_URL+"nc/article/"+docId+"/full.html";

    }


}
