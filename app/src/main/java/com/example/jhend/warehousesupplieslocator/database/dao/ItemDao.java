package com.example.jhend.warehousesupplieslocator.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.jhend.warehousesupplieslocator.model.Cell;
import com.example.jhend.warehousesupplieslocator.model.Item;

import java.util.List;

/**
 * Created by jhend on 1/2/2018.
 */

@Dao
public interface ItemDao {
    @Query("SELECT * FROM Item")
    List<Item> getAll();

    @Query("DELETE FROM Item")
    void deleteAll();

    @Query("SELECT * FROM Item WHERE name = :name")
    Item getItemByName(String name);

    @Insert
    void insertAll(Item... Items);

    @Delete
    void delete(Item Item);


}
