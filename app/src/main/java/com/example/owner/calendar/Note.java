package com.example.owner.calendar;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "note_table")      //建造數據庫的表 ，使用@Entity註解   (tableName = "note_table") 設定資料表名稱
public class Note {

    @PrimaryKey(autoGenerate = true)         //   //自動產生編號
    private  int id ;

    private  String time ;

    private  String decription;

    //右鍵Generate->Constructor 產生結構
    public Note(String time, String decription) {
        this.time = time;
        this.decription = decription;
    }



    public void setId(int id) {
        this.id = id;
    }
    // 右鍵Generate->getter 快速產生方法
    public int getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public String getDecription() {
        return decription;
    }

}