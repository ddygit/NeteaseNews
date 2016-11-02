package com.example.administrator.neteasenews.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/29.
 */
//序列化
public class NewsType implements Serializable{
public  ArrayList<Tlist> tList;

    public NewsType(ArrayList<Tlist> tList) {
        this.tList=tList;
    }




    public static class Tlist implements Serializable{
        public String tid,tname;

        public Tlist(String tid, String tName) {
            this.tname = tName;
            this.tid = tid;
        }

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }

        public String gettName() {
            return tname;
        }

        public void settName(String tName) {
            this.tname = tName;
        }
    }
}
