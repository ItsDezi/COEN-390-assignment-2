package com.example.coen_390_prog_asn_2_v2.database.entity;
import androidx.annotation.ColorLong;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "profiles_table")
public class Profile {
    @PrimaryKey(autoGenerate = true)
    public int ProfileKey;
    @ColumnInfo(name = "first_name")
    public String first_name;
    @ColumnInfo(name = "last_name")
    public String last_name;
    @ColumnInfo(name = "gpa")
    public double gpa;

    public Profile(int profileKey, String first_name, String last_name, double gpa/*, int creation_year, int creation_month, int creation_day*/) {
        ProfileKey = profileKey;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gpa = gpa;
        /*this.creation_year = creation_year;
        this.creation_month = creation_month;
        this.creation_day = creation_day;*/
    }
    public Profile()
    {

    }

    @ColumnInfo(name = "creation_year")
    public int creation_year;
    @ColumnInfo(name = "creation_month")
    public int creation_month;
    @ColumnInfo(name = "creation_day")
    public int creation_day;
}
