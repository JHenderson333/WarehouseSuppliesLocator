package com.example.jhend.warehousesupplieslocator.database;

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


    @Insert
    void insertAll(Cell... Cells);

    @Delete
    void delete(Cell Cell);
}
