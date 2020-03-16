package com.example.tema2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);
    @Query("SELECT * FROM user WHERE full_name LIKE :first LIMIT 1 ")
    User findByName(String first);

    @Insert
    void insertAll(User... users);
    @Delete
    void delete(User user);
    @Insert
    void insertTask(User user);
}