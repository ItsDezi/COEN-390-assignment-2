package com.example.coen_390_prog_asn_2_v2.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "access_table")
public class Access {
    @PrimaryKey(autoGenerate = true)
    public int AccessID;
    @ColumnInfo(name = "profile_key")
    public int profile_key;
    @ColumnInfo(name = "access_type")
    public String access_type;
    @ColumnInfo(name = "year")
    public int year;
    @ColumnInfo(name = "month")
    public int month;
    @ColumnInfo(name = "day")
    public int day;
    @ColumnInfo(name = "hour")
    public int hour;
    @ColumnInfo(name = "minute")
    public int minute;
    @ColumnInfo(name = "second")
    public int second;

    public Access() {
    }

    public int getAccessID() {
        return AccessID;
    }

    public void setAccessID(int accessID) {
        AccessID = accessID;
    }

    public int getProfile_key() {
        return profile_key;
    }

    public void setProfile_key(int profile_key) {
        this.profile_key = profile_key;
    }

    public String getAccess_type() {
        return access_type;
    }

    public void setAccess_type(String access_type) {
        this.access_type = access_type;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public Access(/*int accessID, */int profile_key, String access_type, int year, int month, int day, int hour, int minute, int second) {
        //AccessID = accessID;
        this.profile_key = profile_key;
        this.access_type = access_type;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }
}
