package com.example.coen_390_prog_asn_2_v2.database.dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.coen_390_prog_asn_2_v2.database.entity.Access;

import java.util.List;

@Dao
public interface AccessDao {
    @Query("SELECT * FROM access_table")
    List<Access> getAll();

    @Query("SELECT * FROM access_table WHERE accessID=:accessID")
    Access findById(int accessID);

    @Insert
    void insertAll(Access... accesses);

    @Delete
    void delete(Access access);
}
