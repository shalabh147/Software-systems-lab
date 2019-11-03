package com.example.db;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@androidx.room.Dao
public interface NodeDao {

    @Query("Select * from app")
    List<Node> getAllNodes();

    @Query("Select * from app where name LIKE :Name")
    Node findByName(String Name);

    @Query("Select * from app where id LIKE :Id")
    Node findById(int Id);

    @Query("Select * from app where parent_id LIKE :Parent_id")
    List<Node> findbyParentId(int Parent_id);

    @Query("Select * from app where description LIKE :Description")
    Node findbyDescription(String Description);

    @Query("Select * from app where date LIKE :Date")
    Node findbyDate(String Date);

    @Query("Select COUNT(*) from app")
    int countPlayers();

    @Query("Update app set name = :Name, description = :Description, date = :Date where id LIKE :Id")
    void updateById(int Id, String Name, String Description, String Date);

    @Query("DELETE From app")
    void purge();

    @Insert
    void insertAll(Node... app);

    @Delete
    void delete(Node... app);
}

