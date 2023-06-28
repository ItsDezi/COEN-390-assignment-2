package com.example.coen_390_prog_asn_2_v2.database.entity;
import androidx.annotation.ColorLong;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Time;
import java.time.LocalDateTime;

@Entity(tableName = "access_table")
public class Access {

        @PrimaryKey(autoGenerate = true)
        public int accessID;
        @ColumnInfo(name = "studentID")
        public int studentID;
        @ColumnInfo(name = "accessType")
        public String accessType;
        /*@ColumnInfo(name = "dateTime")
        public LocalDateTime dateTime;*/

        public Access(int accessID, int studentID, String accessType, LocalDateTime dateTime) {
                this.accessID = accessID;
                this.studentID = studentID;
                this.accessType = accessType;
                //this.dateTime = dateTime;
        }
        public Access()
        {

        }
        /*@ColumnInfo(name = "creation_year")
        public int creation_year;
        @ColumnInfo(name = "creation_month")
        public int creation_month;
        @ColumnInfo(name = "creation_day")
        public int creation_day;*/

}
