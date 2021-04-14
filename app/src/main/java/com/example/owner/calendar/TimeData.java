package com.example.owner.calendar;

import android.app.Application;
import android.util.Log;

import java.util.List;

public class TimeData extends Application {
    private static String a ="";
    private static String dc ;

    public static final String getTime() {
//        Log.v("****","*** : 獲取時間完成");
        return a;
    }

    public static void setTime(String a) {
        TimeData.a = a;
//        Log.v("****","*** : 設定時間完成");
    }

    public static String getdc(){
        return  dc;
    }
    public static void setdc(String dc){
        TimeData.dc = dc ;
    }
}
