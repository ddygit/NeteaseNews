package com.example.administrator.neteasenews.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/11/1.
 */

public class DatebaseHelper extends SQLiteOpenHelper{

    //DatabaseHelper作为一个访问SQLite的助手类，提供两个方面的功能，
    //第一，getReadableDatabase(),getWritableDatabase()可以获得SQLiteDatabse对象，通过该对象可以对数据库进行操作
    //第二，提供了onCreate()和onUpgrade()两个回调函数，允许我们在创建和升级数据库时，进行自己的操作
    public static final int Version=1;
    //在SQLiteOepnHelper的子类当中，必须有该构造函数
    public DatebaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }
    public DatebaseHelper(Context context,String name,int version)
    {
        this(context,name,null,version);
    }
    public DatebaseHelper(Context context,String name)
    {
        this(context, name, Version);
    }
    //该函数是在第一次得到SQLiteDatabse对象的时候，才会调用这个方法
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        System.out.println("create a datebase");
        //execSQL函数用于执行SQL语句
        db.execSQL("create table user(id int primary key,name varchar(20))");
    }
    //更新数据库的版本时执行这个方法
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        System.out.println("update a database");
    }

}
