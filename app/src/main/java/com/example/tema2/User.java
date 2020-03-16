package com.example.tema2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    private int uid;
    @ColumnInfo(name = "full_name")
    private String fullName;
    @ColumnInfo(name = "mark")
    private String mark;

    public User(){}

    public User(String name, String mark){
        this.fullName = name;
        this.mark = mark;
    }

    public int getUid() {
        return uid;
    }

    public String getFullName() {
        return fullName;
    }

    public String getMark() {
        return mark;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

}
