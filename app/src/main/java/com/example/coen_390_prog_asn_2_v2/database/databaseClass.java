package com.example.coen_390_prog_asn_2_v2.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.coen_390_prog_asn_2_v2.database.dao.ProfileDao;
import com.example.coen_390_prog_asn_2_v2.database.entity.Profile;

@Database(entities = {Profile.class}, version = 1)
public abstract class databaseClass extends RoomDatabase
{
    private static volatile databaseClass instance;
    private static final String DB_NAME = "profileDatabase";
    protected databaseClass(){}
    private static databaseClass create(Context context) {
        return Room.databaseBuilder(context, databaseClass.class, DB_NAME).allowMainThreadQueries().build();//allow main thread queries is good for small uses only
    }
    public static synchronized databaseClass getInstance(Context context){
        if(instance == null)
        {
            instance = create(context);
        }
        return instance;
    }
    public abstract ProfileDao profileDao();
}
