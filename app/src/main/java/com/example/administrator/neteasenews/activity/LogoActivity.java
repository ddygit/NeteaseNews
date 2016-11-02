package com.example.administrator.neteasenews.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.neteasenews.R;
import com.example.administrator.neteasenews.biz.Xhttp;
import com.example.administrator.neteasenews.entity.NewsType;

public class LogoActivity extends AppCompatActivity implements Xhttp.NewsTypeListener {
    NewsType newsType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        Xhttp.getNewsTypeList(this);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, LogoActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void OnSuccess(NewsType newsType) {
        if (newsType != null) {
            this.newsType = newsType;
        }
    }

    @Override
    public void Finish() {
        Bundle bundle = new Bundle();
        MainActivity.start(this, newsType);
                finish();
    }
}
