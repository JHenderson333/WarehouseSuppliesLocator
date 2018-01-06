package com.example.jhend.warehousesupplieslocator.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.jhend.warehousesupplieslocator.model.Cell;

import java.util.List;

/**
 * Created by jhend on 1/2/2018.
 */

@Dao
public interface CellDao {
    @Query("SELECT * FROM Cell")
    List<Cell> getAll();

    @Query("DELETE FROM Cell")
    void deleteAll();

    @Query("SELECT * FROM Cell WHERE name = :name")
    Cell getCellByName(String name);

    @Insert
    void insertAll(Cell... Cells);

    @Delete
    void delete(Cell Cell);


}
