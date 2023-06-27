package com.example.coen_390_prog_asn_2_v2.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.coen_390_prog_asn_2_v2.database.entity.Profile;

import java.util.List;

@Dao
public interface ProfileDao {
    @Query("SELECT * FROM profiles_table")
    List<Profile> getAll();

    @Query("SELECT * FROM profiles_table WHERE ProfileKey=:ProfileKey")
    Profile findById(int ProfileKey);

    @Insert
    void insertAll(Profile... profiles);

    @Delete
    void delete(Profile profile);
}
